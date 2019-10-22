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

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created on Oct 13, 2019
 */

public class StatisticsEntity extends AbstractEntity {

	private static final long serialVersionUID = -3378276731477126693L;

	public String getSchemaName() {
		return schemaName;
	}

	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}

	public String getSchemaNameZh() {
		return schemaNameZh;
	}

	public void setSchemaNameZh(String schemaNameZh) {
		this.schemaNameZh = schemaNameZh;
	}

	public String getRate() {
		return rate;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	private String schemaName;
	private String schemaNameZh;
	private String rate;
	private Integer total;

}
