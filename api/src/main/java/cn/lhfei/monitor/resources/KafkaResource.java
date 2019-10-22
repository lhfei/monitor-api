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

package cn.lhfei.monitor.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsOptions;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cn.lhfei.monitor.conveter.KafkaMessageConvert;
import cn.lhfei.monitor.model.RestResponseModel;
import cn.lhfei.monitor.orm.domain.ModuleEntity;
import cn.lhfei.monitor.orm.domain.OpsSeries;
import cn.lhfei.monitor.orm.domain.StatisticsEntity;
import cn.lhfei.monitor.orm.mapper.ModuleMapper;
import cn.lhfei.monitor.orm.mapper.OpsLogMapper;
import io.swagger.annotations.ApiOperation;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * Created on Sep 26, 2019
 */
@RestController
@RequestMapping("/api/v1")
public class KafkaResource extends BasicResources {
	@Autowired
    private KafkaProperties kafkaProperties;
	@Autowired
	private OpsLogMapper opsLogMapper;
	@Autowired
	private ModuleMapper moduleMapper;
    
	@RequestMapping(value = "/topics", method = { RequestMethod.GET })
    @ApiOperation(value = "List All Topics")
	public RestResponseModel getTopics(@RequestParam(required = false) boolean showInternalTopics)
			throws InterruptedException, ExecutionException {
    	Properties properties = new Properties();
	    properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getConsumer().getBootstrapServers());

	    AdminClient adminClient = AdminClient.create(properties);

	    ListTopicsOptions listTopicsOptions = new ListTopicsOptions();
	    listTopicsOptions.listInternal(true);

	    ListTopicsResult rs = adminClient.listTopics();
	    if(showInternalTopics) {
	    	return RestResponseModel.success("", rs.names().get());
	    }else {
	    	List<String> topics = new ArrayList<>();
	    	rs.names().get().forEach(topic -> {
	    		if(!topic.startsWith("_")) {
	    			topics.add(topic);
	    		}
	    	});
	    	
	    	return RestResponseModel.success("", topics);
	    }
    }
	
	@RequestMapping(value = "/systems", method = { RequestMethod.GET })
    @ApiOperation(value = "Get All Systems。")
	public List<ModuleEntity> getSystems()
			throws InterruptedException, ExecutionException {
		ModuleEntity moduleEntity = new ModuleEntity();
    	List<ModuleEntity> result = new ArrayList<>();
    	try {
			result = moduleMapper.getSystems(moduleEntity);
		} catch (Exception e) {
			LOG.info("Get Series has an error. {}", e.getMessage(), e);
		}
    	
    	return result;
    }
	
	@RequestMapping(value = "/modules", method = { RequestMethod.GET })
    @ApiOperation(value = "Get All Systems。")
	public List<ModuleEntity> getModules(@RequestParam String schemaName)
			throws InterruptedException, ExecutionException {
		ModuleEntity moduleEntity = new ModuleEntity();
		moduleEntity.setSchemaName(schemaName);
		
    	List<ModuleEntity> result = new ArrayList<>();
    	try {
			result = moduleMapper.getModules(moduleEntity);
		} catch (Exception e) {
			LOG.info("Get Series has an error. {}", e.getMessage(), e);
		}
    	
    	return result;
    }
	
	@RequestMapping(value = "/statistics", method = { RequestMethod.GET })
	@ApiOperation(value = "Get All Systems。")
	public List<StatisticsEntity> getStatistics(@RequestParam String schemaName)
			throws InterruptedException, ExecutionException {
		ModuleEntity moduleEntity = new ModuleEntity();
		moduleEntity.setSchemaName(schemaName);
		
		List<StatisticsEntity> result = new ArrayList<>();
		try {
			result = moduleMapper.getStatistics(moduleEntity);
		} catch (Exception e) {
			LOG.info("Get Series has an error. {}", e.getMessage(), e);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/words", method = { RequestMethod.GET })
	@ApiOperation(value = "Get All Systems。")
	public List<StatisticsEntity> getWords(@RequestParam String schemaName)
			throws InterruptedException, ExecutionException {
		ModuleEntity moduleEntity = new ModuleEntity();
		moduleEntity.setSchemaName(schemaName);
		
		List<StatisticsEntity> result = new ArrayList<>();
		try {
			result = moduleMapper.getWords(moduleEntity);
		} catch (Exception e) {
			LOG.info("Get Series has an error. {}", e.getMessage(), e);
		}
		
		return result;
	}
	
	@RequestMapping(value = "/series", method = { RequestMethod.GET })
    @ApiOperation(value = "Get the latest CRUD operation statistics。")
	public List<OpsSeries> getSeries(@RequestParam(required = false) boolean showInternalTopics)
			throws InterruptedException, ExecutionException {
    	OpsSeries opsSeries = KafkaMessageConvert.opsSeriesThreadLocal.get();
    	List<OpsSeries> result = new ArrayList<>();
    	try {
			result = opsLogMapper.getSeries(opsSeries);
		} catch (Exception e) {
			LOG.info("Get Series has an error. {}", e.getMessage(), e);
		}
    	
    	return result;
    }
	
}
