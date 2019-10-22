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

package cn.lhfei.monitor.tools;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.otter.canal.protocol.FlatMessage;
import com.google.gson.Gson;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created on Oct 09, 2019
 */

public class MessageConverter {
	private static final Logger LOG = LoggerFactory.getLogger(MessageConverter.class);
	private static Gson gson = new Gson();

	static final String SAMPLE_MSG_UPDTE = "{\"data\":[{\"id\":\"8\",\"name\":\"HefeiLi1\",\"password\":\"012345\",\"birthday\":\"1983-10-11\"}],\"database\":\"kafka_conn\",\"es\":1570585895000,\"id\":6,\"isDdl\":false,\"mysqlType\":{\"id\":\"int(11)\",\"name\":\"varchar(100)\",\"password\":\"varchar(100)\",\"birthday\":\"date\"},\"old\":[{\"password\":\"2345678\"}],\"pkNames\":[\"id\"],\"sql\":\"\",\"sqlType\":{\"id\":4,\"name\":12,\"password\":12,\"birthday\":91},\"table\":\"student\",\"ts\":1570585895760,\"type\":\"UPDATE\"}";
	static final String SAMPLE_MSG_INSERT = "{\"data\":[{\"id\":\"4\",\"name\":\"HefeiLi\",\"password\":\"123456\",\"birthday\":\"1983-10-10\"}],\"database\":\"kafka_conn\",\"es\":1570526565000,\"id\":4,\"isDdl\":false,\"mysqlType\":{\"id\":\"int(11)\",\"name\":\"varchar(100)\",\"password\":\"varchar(100)\",\"birthday\":\"date\"},\"old\":null,\"pkNames\":[\"id\"],\"sql\":\"\",\"sqlType\":{\"id\":4,\"name\":12,\"password\":12,\"birthday\":91},\"table\":\"student\",\"ts\":1570526565527,\"type\":\"INSERT\"}\r\n";
	static final String SAMPLE_MSG_DELETE = "{\"data\":[{\"id\":\"4\",\"name\":\"HefeiLi\",\"password\":\"123456\",\"birthday\":\"1983-10-10\"}],\"database\":\"kafka_conn\",\"es\":1570526633000,\"id\":5,\"isDdl\":false,\"mysqlType\":{\"id\":\"int(11)\",\"name\":\"varchar(100)\",\"password\":\"varchar(100)\",\"birthday\":\"date\"},\"old\":null,\"pkNames\":[\"id\"],\"sql\":\"\",\"sqlType\":{\"id\":4,\"name\":12,\"password\":12,\"birthday\":91},\"table\":\"student\",\"ts\":1570526633680,\"type\":\"DELETE\"}";


	@Test
	public void convert() {
		FlatMessage msg = gson.fromJson(SAMPLE_MSG_UPDTE, FlatMessage.class);
		
		LOG.info("DB: {}, TB: {}, type: {}, ts: {}", msg.getDatabase(), msg.getTable(), msg.getType(), msg.getTs());
	}
	
	@Test
	public void format() {
		Long ts = 1570608630587L;
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		
		Date date = new Date(ts);
		
		LOG.info(sf.format(date));
	}
	
}
