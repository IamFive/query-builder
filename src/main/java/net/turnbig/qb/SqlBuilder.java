/**
 * @(#)SqlBuilder.java 2016年1月20日
 *
 * Copyright 2008-2016 by Woo Cupid.
 * All rights reserved.
 * 
 */
package net.turnbig.qb;

import net.turnbig.qb.segment.Settings;

/**
 * @author Woo Cupid
 * @date 2016年1月20日
 * @version $Revision$
 */
public class SqlBuilder {

	Settings settings;

	public static SqlBuilder jdbc() {
		SqlBuilder sb = new SqlBuilder();
		sb.setSettings(Settings.jdbc());
		return sb;
	}

	public static SqlBuilder namedQuery() {
		SqlBuilder sb = new SqlBuilder();
		sb.setSettings(Settings.namedQuery());
		return sb;
	}


	/**
	 * @param settings
	 *            the settings to set
	 */
	public void setSettings(Settings settings) {
		this.settings = settings;
	}

}
