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
 * @created on Oct 09, 2019
 */
public enum StatisticalPeriodEnum {
	
	SECOND(-2, "Second"),
	
	MINUTELY(-1, "Minutely"),
	
	Hourly(0, "Hourly"),

	DAILY(1, "Daily"),

	WEEKLY(7, "Weekly"),

	MONTHLY(30, "Monthly"),

	QUARTERLY(90, "Quarterly"),
	
	HALF_A_YEAR(180, "Half a Year"),

	YEARLY(365, "Yearly");

	StatisticalPeriodEnum(Integer period, String label) {
		this.period = period;
		this.label = label;
	}

	public Integer getPeriod() {
		return period;
	}

	public String getLabel() {
		return label;
	}

	public static StatisticalPeriodEnum checkType(String label) {
		if (null == label || label.length() == 0) {
			return null;
		}
		for (StatisticalPeriodEnum period : StatisticalPeriodEnum.values()) {
			if (period.getLabel().equals(label)) {
				return period;
			}
		}
		return null;
	}

	private Integer period;
	private String label;
}
