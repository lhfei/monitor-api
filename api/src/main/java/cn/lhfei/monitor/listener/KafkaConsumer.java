/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.monitor.listener;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cn.lhfei.monitor.conveter.KafkaMessageConvert;
import cn.lhfei.monitor.orm.domain.DmlOperationMessage;
import cn.lhfei.monitor.orm.domain.OpsLog;
import cn.lhfei.monitor.orm.mapper.OpsLogMapper;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * Created on Oct 08, 2019
 */
@Service
public class KafkaConsumer {
	
	private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumer.class);
	private static Gson gson = new Gson();
	private static final Integer BATCH_SIZE = 1000;
	private BlockingQueue<OpsLog> queue = new LinkedBlockingQueue<>(BATCH_SIZE + 100);
	
	private static volatile boolean running = false;
	private Thread thread = new Thread(new Runnable() {
		public void run() {
			process();
		}
	});
	
    @KafkaListener(topics = {"example"}, groupId = "test")
    public void consume(String message) {
    	try {
			LOG.debug(message);
			DmlOperationMessage log = KafkaMessageConvert.convert(message);
			
			// opsLogMapper.create(log);
			auditLog(log);
			
			this.messagingTemplate.convertAndSend("/topic/binlog", log);
			
		} catch (Exception e) {
			LOG.error("Consumed message has an error. {}", e.getMessage(), e);
		}
    }
    
	private void auditLog(OpsLog log) {
		queue.add(log);
		if (!running) {
			running = true;
			thread.start();
		}
	}

	private void process() {
		while (running) {
			if (queue.size() >= BATCH_SIZE) {
				try {
					opsLogMapper.batchInsert(queue);
					
					queue.clear();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
    
    @Autowired
	private OpsLogMapper opsLogMapper;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
}
