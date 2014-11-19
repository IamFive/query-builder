package edu.woo.qb.segment.impl.single;

import org.apache.commons.lang3.*;

import edu.woo.qb.segment.*;

/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public abstract class SingleSqlSegment extends SqlSegment {

	/** 属性名称 */
	protected String fieldName;

	/** Named query parameter Key */
	protected String paramKey;

	/** Named query parameter value */
	protected Object paramValue;

	/**
	 * @param fieldName
	 * @param paramValue
	 */
	public SingleSqlSegment(String fieldName, Object paramValue) {
		this.fieldName = fieldName;
		this.paramValue = paramValue;
		if (this.isParamRequired()) {
			this.paramKey = this.getRandomKey();
			this.addParam(this.paramKey, this.paramValue);
		}
	}

	/**
	 * generate a random named query key name
	 * 
	 * @return
	 */
	protected String getRandomKey() {
		return this.getFieldName().replace('.', '_') + "_" + RandomStringUtils.randomAlphabetic(3);
	}

	@Override
	public boolean isParamRequired() {
		return true;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public Object getParamValue() {
		return paramValue;
	}

	public void setParamValue(Object paramValue) {
		this.paramValue = paramValue;
	}

}
