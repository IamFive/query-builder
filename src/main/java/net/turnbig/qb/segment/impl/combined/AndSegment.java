package net.turnbig.qb.segment.impl.combined;

/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public class AndSegment extends CombinedSqlSegment {

	/**
	 * 
	 */
	public AndSegment() {
		super();
	}

	/**
	 * @param pretty
	 */
	public AndSegment(boolean pretty) {
		super(pretty);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.woo.qb.test.QueryCondition#getCondition()
	 */
	@Override
	public String asSql() {
		return this.join("(", ")", "and");
	}

}
