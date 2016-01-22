/**
 * @(#)Asserts.java 2014年11月17日
 *
 * Copyright 2008-2014 by Woo Cupid.
 * All rights reserved.
 * 
 */
package com.woo.qb;

import org.apache.commons.lang3.*;

/**
 * Copied from http-core
 * 
 * @author Woo Cupid
 * @date 2014年11月17日
 * @version $Revision$
 */
public class Asserts {

	public static void check(final boolean expression, final String message) {
		if (!expression) {
			throw new IllegalStateException(message);
		}
	}

	public static void check(final boolean expression, final String message, final Object... args) {
		if (!expression) {
			throw new IllegalStateException(String.format(message, args));
		}
	}

	public static void notNull(final Object object, final String name) {
		if (object == null) {
			throw new IllegalStateException(name + " is null");
		}
	}

	public static void notEmpty(final CharSequence s, final String name) {
		if (StringUtils.isEmpty(s)) {
			throw new IllegalStateException(name + " is empty");
		}
	}

	public static void notBlank(final CharSequence s, final String name) {
		if (StringUtils.isBlank(s)) {
			throw new IllegalStateException(name + " is blank");
		}
	}

	/**
	 * @param split
	 * @param length
	 */
	public static void length(String[] split, int length, final String name) {
		if ((split == null) || (split.length != length)) {
			throw new IllegalStateException(name + " is illegal");
		}
	}
}
