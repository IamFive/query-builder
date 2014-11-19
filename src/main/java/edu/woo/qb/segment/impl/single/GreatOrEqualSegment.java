package edu.woo.qb.segment.impl.single;

/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public class GreatOrEqualSegment extends SingleSqlSegment {

	/**
	 * @param fieldName
	 * @param paramValue
	 */
	public GreatOrEqualSegment(String propertyName, Object propertyValue) {
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
		builder.append(this.fieldName).append(" >= ':").append(this.getParamKey()).append("'");
		return builder.toString();
	}

}
