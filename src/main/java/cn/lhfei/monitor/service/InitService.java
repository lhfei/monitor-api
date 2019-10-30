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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created on Oct 28, 2019
 */
@Service
public class InitService implements ApplicationRunner {
	private static final Logger LOG = LoggerFactory.getLogger(InitService.class);

	@Override
	public void run(ApplicationArguments args) throws Exception {
		LOG.info("==== Canal client started ...");
		kafkaMessageHandler.start();
	}

	@Autowired
	private KafkaMessageHandler kafkaMessageHandler;
}
