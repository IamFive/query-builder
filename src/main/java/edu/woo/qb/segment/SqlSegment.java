package edu.woo.qb.segment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
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
	protected Map<String, Object> params = new LinkedHashMap<String, Object>();
	protected Settings settings = Settings.namedQuery();

	public Map<String, Object> getParams() {
		return params;
	}

	public Map<String, Object> getKeyedParams() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.putAll(params);
		return map;
	}

	public List<Object> getListParams() {
		List<Object> dest = new ArrayList<Object>();
		dest.addAll(params.values());
		return dest;
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
