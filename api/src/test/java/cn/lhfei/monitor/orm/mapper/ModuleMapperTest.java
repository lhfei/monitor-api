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

import cn.lhfei.monitor.orm.domain.ModuleEntity;
import cn.lhfei.monitor.orm.domain.StatisticsEntity;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created on Oct 09, 2019
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleMapperTest {
	private static final Logger LOG = LoggerFactory.getLogger(ModuleMapperTest.class);
	private Gson gson = new Gson();

	@Autowired
	private ModuleMapper moduleMapper;

	@Test
	public void getSystems() {
		ModuleEntity moduleEntity = new ModuleEntity();
//		moduleEntity.setSchemaName("dwh_cms");
		
		List<ModuleEntity> results = moduleMapper.getSystems(moduleEntity);
		
		results.forEach(new Consumer<ModuleEntity>() {

			@Override
			public void accept(ModuleEntity entity) {
				LOG.info(gson.toJson(entity));
			}
			
		});
	}
	
	@Test
	public void getModules() {
		ModuleEntity moduleEntity = new ModuleEntity();
		moduleEntity.setSchemaName("dwh_pms");
		
		List<ModuleEntity> results = moduleMapper.getModules(moduleEntity);
		
		results.forEach(new Consumer<ModuleEntity>() {

			@Override
			public void accept(ModuleEntity entity) {
				LOG.info(gson.toJson(entity));
			}
			
		});
	}
	
	@Test
	public void getStatistics() {
		ModuleEntity moduleEntity = new ModuleEntity();
		moduleEntity.setSchemaName("dwh_pms");
		
		List<StatisticsEntity> results = moduleMapper.getStatistics(moduleEntity);
		
		results.forEach(new Consumer<StatisticsEntity>() {

			@Override
			public void accept(StatisticsEntity entity) {
				LOG.info(gson.toJson(entity));
			}
			
		});
	}
}
