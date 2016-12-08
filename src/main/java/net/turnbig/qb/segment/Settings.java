/**
 * @(#)Settigns.java 2016年1月15日
 *
 * Copyright 2008-2016 by Woo Cupid.
 * All rights reserved.
 * 
 */
package net.turnbig.qb.segment;

/**
 * @author Woo Cupid
 * @date 2016年1月15日
 * @version $Revision$
 */
public class Settings {

	private static String DEFAULT_NAMED_QUERY_PREFIX = ":";
	private static String DEFAULT_NAMED_QUERY_SUFFIX = "";

	private String namedQueryPrefix = DEFAULT_NAMED_QUERY_PREFIX;
	private String namedQuerySuffix = DEFAULT_NAMED_QUERY_SUFFIX;

	private Boolean useNamedQuery = true;


	/**
	 * 
	 */
	public Settings() {
		super();
	}

	/**
	 * @param namedQueryPrefix
	 * @param namedQuerySuffix
	 */
	public Settings(String namedQueryPrefix, String namedQuerySuffix) {
		super();
		this.namedQueryPrefix = namedQueryPrefix;
		this.namedQuerySuffix = namedQuerySuffix;
	}

	/**
	 * @param namedQueryPrefix
	 * @param namedQuerySuffix
	 * @param useNamedQuery
	 */
	public Settings(String namedQueryPrefix, String namedQuerySuffix, Boolean useNamedQuery) {
		super();
		this.namedQueryPrefix = namedQueryPrefix;
		this.namedQuerySuffix = namedQuerySuffix;
		this.useNamedQuery = useNamedQuery;
	}

	public static Settings namedQuery() {
		return new Settings(DEFAULT_NAMED_QUERY_PREFIX, DEFAULT_NAMED_QUERY_SUFFIX, true);
	}

	/**
	 * @return
	 */
	public static Settings jdbc() {
		return new Settings("", "", false);
	}


	/**
	 * @return the namedQueryPrefix
	 */
	public String getNamedQueryPrefix() {
		return namedQueryPrefix;
	}

	/**
	 * @param namedQueryPrefix
	 *            the namedQueryPrefix to set
	 */
	public void setNamedQueryPrefix(String namedQueryPrefix) {
		this.namedQueryPrefix = namedQueryPrefix;
	}

	/**
	 * @return the namedQuerySuffix
	 */
	public String getNamedQuerySuffix() {
		return namedQuerySuffix;
	}

	/**
	 * @param namedQuerySuffix
	 *            the namedQuerySuffix to set
	 */
	public void setNamedQuerySuffix(String namedQuerySuffix) {
		this.namedQuerySuffix = namedQuerySuffix;
	}

	/**
	 * @return the useNamedQuery
	 */
	public Boolean getUseNamedQuery() {
		return useNamedQuery;
	}

	/**
	 * @param useNamedQuery
	 *            the useNamedQuery to set
	 */
	public void setUseNamedQuery(Boolean useNamedQuery) {
		this.useNamedQuery = useNamedQuery;
	}


}
