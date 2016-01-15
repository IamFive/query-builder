package edu.woo.qb.segment.impl.single;

import java.text.MessageFormat;

import org.apache.commons.lang3.RandomStringUtils;

import edu.woo.qb.segment.Settings;
import edu.woo.qb.segment.SqlSegment;

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
	public SingleSqlSegment(String fieldName, Object paramValue, Settings settings) {
		this.fieldName = fieldName;
		this.paramValue = paramValue;
		this.settings = settings;
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
		return this.getFieldName().replace('.', '_') + "_" + RandomStringUtils.randomNumeric(3);
	}

	@Override
	public boolean isParamRequired() {
		return true;
	}

	protected String buildSql(String op) {
		String format = "{0} {1} {2}{3}{4}";
		String sql = MessageFormat.format(format, this.fieldName, op, this.getSettings().getNamedQueryPrefix(),
				this.paramKey, this.getSettings().getNamedQuerySuffix());
		return sql;
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
