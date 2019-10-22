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

package cn.lhfei.monitor.orm.mapper;

import java.util.List;
import java.util.function.Consumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import cn.lhfei.monitor.constant.StatisticalPeriodEnum;
import cn.lhfei.monitor.orm.domain.OpsLog;
import cn.lhfei.monitor.orm.domain.OpsSeries;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created on Oct 09, 2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OpsLogMapperTest {
	private static final Logger LOG = LoggerFactory.getLogger(OpsLogMapperTest.class);
	private Gson gson = new Gson();

	@Autowired
	private OpsLogMapper opsLogMapper;

	private OpsLog getOpsLogInstance() {
		OpsLog log = new OpsLog();
		log.setInstanceIp("10.216.217.28");
		log.setServerId(30071);
		log.setDbName("kafka_conn");

		return log;
	}
	
	private OpsSeries getSeriesInstance() {
		OpsSeries series = new OpsSeries();
		series.setInstanceIp("10.216.217.28");
		series.setServerId(30071);
		series.setDbName("kafka_conn");

		return series;
	}

	@Test
	public void find() throws Exception {
		OpsLog log = getOpsLogInstance();

		List<OpsLog> list = opsLogMapper.find(log);
	}
	
	@Test
	public void getSeries() throws Exception {
		OpsSeries series = getSeriesInstance();
		
		for (StatisticalPeriodEnum period : StatisticalPeriodEnum.values()) {
			LOG.info("==== [{}] ==== \n", period.getLabel());
			series.setPeriod(period.getPeriod());
			List<OpsSeries> list = opsLogMapper.getSeries(series);

			list.forEach(new Consumer<OpsSeries>() {
				@Override
				public void accept(OpsSeries t) {
					LOG.info("==> {}", gson.toJson(t));
				}

			});
		}
	}
}
