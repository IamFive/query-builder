package edu.woo.qb.segment.impl.single;

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
	public NotEmptySegment(String propertyName, Object propertyValue) {
		super(propertyName, propertyValue);
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
