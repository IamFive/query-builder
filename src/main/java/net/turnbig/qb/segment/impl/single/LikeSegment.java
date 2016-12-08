package net.turnbig.qb.segment.impl.single;

import net.turnbig.qb.segment.Settings;

/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public class LikeSegment extends SingleSqlSegment {

	/**
	 * @param fieldName
	 * @param paramValue
	 */
	public LikeSegment(String fieldName, Object paramValue, Settings settings) {
		super(fieldName, "%" + paramValue + "%", settings);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.woo.qb.segment.QueryCondition#getCondition()
	 */
	@Override
	public String asSql() {
		return buildSql("like");
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
