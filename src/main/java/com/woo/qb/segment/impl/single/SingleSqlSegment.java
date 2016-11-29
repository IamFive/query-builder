package com.woo.qb.segment.impl.single;

import java.text.MessageFormat;

import org.apache.commons.lang3.RandomStringUtils;

import com.woo.qb.segment.Settings;
import com.woo.qb.segment.SqlSegment;

/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public abstract class SingleSqlSegment extends SqlSegment {

	/**
	 * sql segment format
	 */
	protected static final String SEGMENT = "{0} {1} {2}{3}{4}";

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
		Boolean useNamedQuery = this.settings.getUseNamedQuery();
		String prefix = useNamedQuery ? this.getSettings().getNamedQueryPrefix() : "";
		String suffix = useNamedQuery ? this.getSettings().getNamedQuerySuffix() : "";
		String key = useNamedQuery ? this.paramKey : "?";
		String sql = MessageFormat.format(getSqlFormatter(), this.fieldName, op, prefix, key, suffix);
		return sql;
	}

	/**
	 * @return
	 */
	public String getSqlFormatter() {
		return SEGMENT;
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
