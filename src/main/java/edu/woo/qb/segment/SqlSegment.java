/**
 * @(#)QueryCondition.java 2010-9-10
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

import java.util.*;

/**
 * 
 * @author Woo Cupid
 * @date 2014年11月14日
 * @version $Revision$
 */
public abstract class SqlSegment {

	/**
	 * generate sql for the edu.woo.qb.segment
	 * 
	 * @return
	 */
	public abstract String asSql();

	/**
	 * whether required named query parameter for this edu.woo.qb.segment
	 * 
	 * @return
	 */
	public abstract boolean isParamRequired();

	/**
	 * named query parameter map
	 */
	public Map<String, Object> params = new HashMap<String, Object>();

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void addParam(String key, Object value) {
		params.put(key, value);
	}

	@Override
	public String toString() {
		return "SqlSegment [Sql = " + asSql() + ", params = " + params + "]";
	}

}
