package com.woo.qb.segment;

import java.util.*;

/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public enum ValueType {

	S(String.class), I(Integer.class), L(Long.class), N(Double.class), D(Date.class), B(Boolean.class);

	private Class<?> clazz;

	ValueType(Class<?> clazz) {
		this.clazz = clazz;
	}

	public Class<?> getValue() {
		return clazz;
	}
}
