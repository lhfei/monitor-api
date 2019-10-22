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
 * @created on Oct 11, 2019
 */

public class ModuleEntity extends AbstractEntity {

	private static final long serialVersionUID = 4774443223275876798L;

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

	public String getFirstDir() {
		return firstDir;
	}

	public void setFirstDir(String firstDir) {
		this.firstDir = firstDir;
	}

	public String getSecondDir() {
		return secondDir;
	}

	public void setSecondDir(String secondDir) {
		this.secondDir = secondDir;
	}

	public String getThirdDir() {
		return thirdDir;
	}

	public void setThirdDir(String thirdDir) {
		this.thirdDir = thirdDir;
	}

	public Double getDirSize() {
		return dirSize;
	}

	public void setDirSize(Double dirSize) {
		this.dirSize = dirSize;
	}

	public Long getRows() {
		return rows;
	}

	public void setRows(Long rows) {
		this.rows = rows;
	}

	private String schemaName;
	private String schemaNameZh;
	private String firstDir;
	private String secondDir;
	private String thirdDir;
	private Double dirSize;
	private Long rows;
}
