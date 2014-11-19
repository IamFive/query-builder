package edu.woo.qb.segment.impl.single;

/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public class EqualSegment extends SingleSqlSegment {

	/**
	 * @param fieldName
	 * @param paramValue
	 */
	public EqualSegment(String propertyName, Object propertyValue) {
		super(propertyName, propertyValue);
		this.paramKey = this.getRandomKey();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see edu.woo.qb.segment.QueryCondition#getCondition()
	 */
	@Override
	public String asSql() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.fieldName).append("=:").append(this.paramKey);
		return builder.toString();
	}

	@Override
	public boolean isParamRequired() {
		return true;
	}

}
