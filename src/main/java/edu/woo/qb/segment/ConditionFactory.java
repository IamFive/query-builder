/**
 * @(#)AndQueryCondition.java 2010-9-10
 * 
 * Copyright 2000-2010 by UFida Corporation.
 *
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * UFida Corporation ("Confidential Information").  You shall not 
 * disclose such Confidential Information and shall use it only in 
 * accordance with the terms of the license agreement you entered 
 * into with UFida.
 */
package edu.woo.qb.segment;

import java.lang.reflect.*;

import org.apache.commons.lang3.*;
import org.slf4j.*;

import edu.woo.qb.*;
import edu.woo.qb.convertor.*;
import edu.woo.qb.segment.impl.single.*;

/**
 * 查询条件工厂
 * 
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public class ConditionFactory {

	private static final Logger logger = LoggerFactory.getLogger(ConditionFactory.class);

	/**
	 * parse a spec SQL string as a SingleSqlSegment
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static SingleSqlSegment parse(final String key, final Object value) {

		String propertyName;
		ConditionType conditionType;
		ValueType valueType;

		try {
			String[] split = StringUtils.split(key, "_");
			propertyName = split[1];
			conditionType = Enum.valueOf(ConditionType.class, split[2].toUpperCase());
			valueType = Enum.valueOf(ValueType.class, split[3].toUpperCase());
		} catch (Exception e) {
			logger.error("Can't parse " + key, e);
			throw new ParseException("Can't parse {0} as a known condition.", key);
		}

		return build(propertyName, conditionType, valueType, value);
	}

	public static SingleSqlSegment build(String propertyName, ConditionType conditionType, ValueType valueType,
			Object value) {
		try {
			Object propertyValue = StringUtils.isNotBlank(value.toString()) ? BeanMapper.map(value,
					valueType.getValue()) : null;
			Constructor<?> constructor = conditionType.getValue().getConstructor(String.class, Object.class);
			return (SingleSqlSegment) constructor.newInstance(propertyName, propertyValue);
		} catch (Exception e) {
			logger.error("Can't build condition.", e);
			throw new ParseException("Can't build condition.");
		}
	}
}
