package com.woo.qb.segment.impl.single;

import com.woo.qb.segment.Settings;

/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public class NotEmptySegment extends SingleSqlSegment {

	/**
	 * @param fieldName
	 * @param paramValue
	 */
	public NotEmptySegment(String fieldName, Object paramValue, Settings settings) {
		super(fieldName, paramValue, settings);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.woo.qb.segment.SqlSegment#getCondition()
	 */
	@Override
	public String asSql() {
		return this.fieldName + " <> ''";
	}

	@Override
	public boolean isParamRequired() {
		return false;
	}

}
