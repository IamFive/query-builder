/**
 * @(#)ValueType.java 2010-9-10
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

import java.util.Date;

/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public enum ValueType {

	S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(
			Boolean.class);

	private Class<?> clazz;

	ValueType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getValue() {
		return clazz;
	}
}
