package edu.woo.qb.segment;

import java.util.*;

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

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

	public void addParam(String key, Object value) {
		params.put(key, value);
	}

	@Override
	public String toString() {
		return "SqlSegment [Sql = " + asSql() + ", params = " + params + "]";
	}

}
