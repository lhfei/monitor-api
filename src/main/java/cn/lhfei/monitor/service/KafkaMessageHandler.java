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

package cn.lhfei.monitor.service;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.otter.canal.client.kafka.KafkaOffsetCanalConnector;
import com.alibaba.otter.canal.client.kafka.protocol.KafkaFlatMessage;
import com.alibaba.otter.canal.protocol.FlatMessage;
import com.google.gson.Gson;

import cn.lhfei.monitor.config.CanalConfig;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created on Oct 28, 2019
 */
@Service("kafkaMessageHandler")
public class KafkaMessageHandler {
	protected final static Logger LOG = LoggerFactory.getLogger(KafkaMessageHandler.class);

	private KafkaOffsetCanalConnector connector;
	private static volatile boolean running = false;
	private Thread thread = null;
	
	private static final Gson gson = new Gson();
	private ThreadLocal<KafkaOffsetCanalConnector> connectorThread = new ThreadLocal<KafkaOffsetCanalConnector>() {
		@Override
		protected KafkaOffsetCanalConnector initialValue() {
			KafkaOffsetCanalConnector connector = new KafkaOffsetCanalConnector(
					canalConfig.getKafka().getBootstrapServers(), canalConfig.getKafka().getTopic(),
					canalConfig.getKafka().getPartition(), canalConfig.getKafka().getGroupId(), true);
			
			LOG.info("## start the kafka consumer: {}-{}", canalConfig.getKafka().getTopic(),
					canalConfig.getKafka().getGroupId());
			return connector;
		}
	};

	private Thread.UncaughtExceptionHandler handler = new Thread.UncaughtExceptionHandler() {
		public void uncaughtException(Thread t, Throwable e) {
			LOG.error("parse events has an error", e);
		}
	};

	public void start() {
		connector = connectorThread.get();
		
		Assert.notNull(connector, "connector is null");
		thread = new Thread(new Runnable() {

			public void run() {
				process();
			}
		});
		thread.setUncaughtExceptionHandler(handler);
		thread.start();
		running = true;
	}

	public void stop() {
		if (!running) {
			return;
		}
		running = false;
		if (thread != null) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				// ignore
			}
		}
	}

	private void process() {
		while (!running) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}

		while (running) {
			try {
				connector.connect();
				connector.subscribe();
				int errorCount = 0;
				while (running) {
					try {
						if (errorCount > 2) {
							Thread.sleep((errorCount - 2) * 1000 * 30);
						}

						List<FlatMessage> messages = connector.getFlatList(100L, TimeUnit.MILLISECONDS);
						if (messages == null) {
							continue;
						}
						for (FlatMessage message : messages) {
							long batchId = message.getId();
							int size = message.getData().size();

							if (batchId == -1 || size == 0) {
								continue;
							}else {
								LOG.info(message.toString());
							}
						}

						connector.ack();
						
						errorCount = 0;
					} catch (Exception e) {
						errorCount++;
						LOG.error(e.getMessage(), e);
						if (errorCount == 3) {
							// TODO messageService.audit("error", e);
						}
					}
				}
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
		}

		connector.unsubscribe();
		connector.disconnect();
	}

	@Autowired
	private CanalConfig canalConfig;
}
