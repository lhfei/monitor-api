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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import cn.lhfei.monitor.conveter.KafkaMessageConvert;
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

    @KafkaListener(topics = "example", groupId = "test")
    public void consume(String message) {
    	try {
			LOG.info(message);
			OpsLog log = KafkaMessageConvert.convert(message);
			opsLogMapper.create(log);
			
			/*List<OpsSeries> results = opsLogMapper.getSeries(log);
			LOG.info("== {}", gson.toJson(results));*/
			
			this.messagingTemplate.convertAndSend("/topic/binlog", message);
			
		} catch (Exception e) {
			LOG.error("Consumed message has an error. {}", e.getMessage(), e);
		}
    }
    
    
    @Autowired
	private OpsLogMapper opsLogMapper;
    
    @Autowired
    private SimpMessagingTemplate messagingTemplate;
    
}
