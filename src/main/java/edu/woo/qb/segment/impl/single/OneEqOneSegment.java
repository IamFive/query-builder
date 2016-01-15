package edu.woo.qb.segment.impl.single;

import edu.woo.qb.segment.SqlSegment;

/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public class OneEqOneSegment extends SqlSegment {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.woo.qb.segment.SqlSegment#getCondition()
	 */
	@Override
	public String asSql() {
		return "1=1";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.woo.qb.segment.SqlSegment#isNeedNamedParam(boolean)
	 */
	@Override
	public boolean isParamRequired() {
		return false;
	}

}
