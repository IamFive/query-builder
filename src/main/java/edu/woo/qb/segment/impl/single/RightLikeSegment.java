/**
 * @(#)EqualSegment.java 2010-9-10
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
public class RightLikeSegment extends SingleSqlSegment {

	/**
	 * @param fieldName
	 * @param paramValue
	 */
	public RightLikeSegment(String propertyName, Object propertyValue) {
		super(propertyName, propertyValue);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.woo.qb.segment.QueryCondition#getCondition()
	 */
	@Override
	public String asSql() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.fieldName).append(" like ':").append(this.getParamKey()).append("%'");
		return builder.toString();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.woo.qb.segment.SqlSegment#isNeedNamedParam(boolean)
	 */
	@Override
	public boolean isParamRequired() {
		return true;
	}
}
