package edu.woo.qb.convertor;

import org.apache.commons.beanutils.converters.*;

public class DateConvertor extends DateTimeConverter {

	public static String[] format = { "yyyy-MM-dd", "" };

	@Override
	protected Class getDefaultType() {
		return null;
	}

}
