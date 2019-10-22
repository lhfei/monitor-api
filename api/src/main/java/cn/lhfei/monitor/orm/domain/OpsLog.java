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

package cn.lhfei.monitor.orm.domain;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 *         Created on Oct 09, 2019
 */
public class OpsLog extends AbstractEntity {
	private static final long serialVersionUID = -5928846007974910984L;

	public OpsLog() {
		this.auditTime = new Timestamp(new Date().getTime());
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstanceIp() {
		return instanceIp;
	}

	public void setInstanceIp(String instanceIp) {
		this.instanceIp = instanceIp;
	}

	public int getServerId() {
		return serverId;
	}

	public void setServerId(int serverId) {
		this.serverId = serverId;
	}

	public String getDbName() {
		return dbName;
	}

	public void setDbName(String dbName) {
		this.dbName = dbName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getOpsTime() {
		return opsTime;
	}

	public void setOpsTime(Date opsTime) {
		this.opsTime = opsTime;
	}

	public Long getOpsTs() {
		return opsTs;
	}

	public Integer getEffectSize() {
		return effectSize;
	}

	public void setEffectSize(Integer effectSize) {
		this.effectSize = effectSize;
	}

	public void setOpsTs(Long opsTs) {
		this.opsTs = opsTs;
	}

	public Timestamp getAuditTime() {
		return auditTime;
	}

	private Long id;
	private int serverId;
	private String instanceIp;
	private String dbName;
	private String tableName;
	private String type;
	private Date opsTime;
	private Long opsTs;
	private Integer effectSize;
	private Timestamp auditTime;

}
