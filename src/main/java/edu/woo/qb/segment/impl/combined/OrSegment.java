package edu.woo.qb.segment.impl.combined;

/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public class OrSegment extends CombinedSqlSegment {

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.woo.qb.test.QueryCondition#getCondition()
	 */
	@Override
	public String asSql() {
		return this.join("(", ")", "or");
	}
}
