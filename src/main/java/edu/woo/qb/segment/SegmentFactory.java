package edu.woo.qb.segment;

import java.lang.reflect.Constructor;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.woo.qb.Asserts;
import edu.woo.qb.ParseException;
import edu.woo.qb.segment.impl.single.SingleSqlSegment;

/**
 * 
 * Single SQL Segment Factory.
 * 
 * - build single SQL segment from a specify string
 * 
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public class SegmentFactory {

	private static final Logger logger = LoggerFactory.getLogger(SegmentFactory.class);

	static {
		DateTimeConverter dtConverter = new DateConverter();
		dtConverter.setPatterns(getDateFormats());
		ConvertUtils.register(dtConverter, Date.class);
	}

	private static String[] getDateFormats() {
		String ls = "yyyy-MM-dd HH:mm:ss";
		String ss = "yyyy-MM-dd";
		String rfc3399 = "yyyy-MM-dd'T'HH:mm:ss";
		return new String[] { ls, ss, rfc3399 };
	}

	/**
	 * parse a spec SQL string as a SingleSqlSegment
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static SingleSqlSegment parse(final String key, final String value, Settings settings) {

		String fieldName;
		SginleSegmentType segmentType;
		ValueType valueType;

		try {
			String[] split = StringUtils.split(key, "_");
			Asserts.length(split, 4, "To parsed key: " + key);

			fieldName = split[1];
			segmentType = Enum.valueOf(SginleSegmentType.class, split[2].toUpperCase());
			valueType = Enum.valueOf(ValueType.class, split[3].toUpperCase());
		} catch (Exception e) {
			logger.error("Can't parse " + key, e);
			throw new ParseException("Can't parse {0} as a known segment.", key);
		}

		return build(fieldName, segmentType, valueType, value, settings);
	}

	public static SingleSqlSegment build(String fieldName, SginleSegmentType segmentType, ValueType valueType,
			String value, Settings settings) {
		try {
			Object paramValue = value;
			if (!ValueType.S.equals(valueType)) {
				paramValue = StringUtils.isNotBlank(value) ? ConvertUtils.convert(value, valueType.getValue()) : null;
			}
			Constructor<?> constructor = segmentType.getValue().getConstructor(String.class, Object.class,
					Settings.class);
			return (SingleSqlSegment) constructor.newInstance(fieldName, paramValue, settings);
		} catch (Exception e) {
			logger.error("Can't build segment.", e);
			throw new ParseException("Can't build segment.");
		}
	}

}
