/**
 * @(#)IsNullSegment.java 2010-9-10
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
package edu.woo.qb.segment.impl.single;


/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public class NotNullSegment extends SingleSqlSegment {

	/**
	 * @param fieldName
	 * @param paramValue
	 */
	public NotNullSegment(String propertyName, Object propertyValue) {
		super(propertyName, propertyValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.woo.qb.segment.SqlSegment#getCondition()
	 */
	@Override
	public String asSql() {
		return this.fieldName + " is not null";
	}

	@Override
	public boolean isParamRequired() {
		return false;
	}
}
