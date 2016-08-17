package com.woo.qb.segment.impl.single;

import com.woo.qb.segment.Settings;

/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public class AnySegment extends SingleSqlSegment {

	/**
	 * @param fieldName
	 * @param paramValue
	 */
	public AnySegment(String fieldName, Object paramValue, Settings settings) {
		super(fieldName, paramValue, settings);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.woo.qb.segment.QueryCondition#getCondition()
	 */
	@Override
	public String asSql() {
		return buildSql("=");
	}

	public String getSqlFormatter() {
		return "{2}{3}{4} {1} any({0})";
	}

	@Override
	public boolean isParamRequired() {
		return true;
	}

}
