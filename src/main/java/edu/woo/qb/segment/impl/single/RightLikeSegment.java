package edu.woo.qb.segment.impl.single;

import edu.woo.qb.segment.Settings;

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
	public RightLikeSegment(String fieldName, Object paramValue, Settings settings) {
		super(fieldName, paramValue + "%", settings);
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
