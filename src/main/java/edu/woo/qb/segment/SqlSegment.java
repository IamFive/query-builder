package edu.woo.qb.segment;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Woo Cupid
 * @date 2014年11月14日
 * @version $Revision$
 */
public abstract class SqlSegment {

	/**
	 * generate sql for the edu.woo.qb.segment
	 * 
	 * @return
	 */
	public abstract String asSql();

	/**
	 * whether required named query parameter for this edu.woo.qb.segment
	 * 
	 * @return
	 */
	public abstract boolean isParamRequired();

	/**
	 * named query parameter map
	 */
	public Map<String, Object> params = new HashMap<String, Object>();
	public Settings settings;

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void addParam(String key, Object value) {
		params.put(key, value);
	}

	/**
	 * @return the settings
	 */
	public Settings getSettings() {
		return settings;
	}

	/**
	 * @param settings
	 *            the settings to set
	 */
	public void setSettings(Settings settings) {
		this.settings = settings;
	}

	@Override
	public String toString() {
		return "SqlSegment [Sql = " + asSql() + ", params = " + params + "]";
	}

}
