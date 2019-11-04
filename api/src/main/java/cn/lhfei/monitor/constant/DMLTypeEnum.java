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

package cn.lhfei.monitor.constant;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created on Oct 30, 2019
 */

public enum DMLTypeEnum {

	INSERT("INSERT", "1"),
	
	/**
	 * has two status: BEFORE('3') and AFTER('4')
	 */
	UPDATE("UPDATE", "3"),
	
	DELETE("DELETE", "2");
	
	DMLTypeEnum(String type,  String code) {
		this.type = type;
		this.code = code;
	}
	
	public static final String DML_FLAG_FILED = "EXT_flag";
	
	public String getType() {
		return type;
	}
	public String getCode() {
		return code;
	}
	
	public static DMLTypeEnum checkType(String type) {
		if (null == type || type.length() == 0) {
			return null;
		}
		for (DMLTypeEnum dml : DMLTypeEnum.values()) {
			if (dml.getType().equals(type)) {
				return dml;
			}
		}
		return null;
	}
	
	// TODO: improve the UPDATE action tag
	public static DMLTypeEnum checkCode(String code) {
		if (null == code || code.length() == 0) {
			return DMLTypeEnum.UPDATE;
		}
		for (DMLTypeEnum dml : DMLTypeEnum.values()) {
			if (dml.getCode().equals(code)) {
				return dml;
			}
		}
		
		return DMLTypeEnum.UPDATE;
	}

	private String type;
	private String code;
}
