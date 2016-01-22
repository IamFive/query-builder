package com.woo.qb.segment;

import java.lang.reflect.Constructor;
import java.util.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.beanutils.converters.DateTimeConverter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.woo.qb.Asserts;
import com.woo.qb.ParseException;
import com.woo.qb.segment.impl.combined.AndSegment;
import com.woo.qb.segment.impl.combined.CombinedSqlSegment;
import com.woo.qb.segment.impl.combined.OrSegment;
import com.woo.qb.segment.impl.single.SingleSqlSegment;

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

	private Settings settings = Settings.jdbc();

	public static SegmentFactory jdbc() {
		SegmentFactory sb = new SegmentFactory();
		sb.settings = Settings.jdbc();
		return sb;
	}

	public static SegmentFactory namedQuery() {
		SegmentFactory sb = new SegmentFactory();
		sb.settings = Settings.namedQuery();
		return sb;
	}

	public CombinedSqlSegment and(SqlSegment... segments) {
		return new AndSegment().addSegments(segments);
	}

	public CombinedSqlSegment or(SqlSegment... segments) {
		return new OrSegment().addSegments(segments);
	}

	public SingleSqlSegment eq(String fieldName, Object value) {
		return SegmentFactory.build(SingleSegmentType.EQ, fieldName, value, settings);
	}

	public SingleSqlSegment ne(String fieldName, Object value) {
		return SegmentFactory.build(SingleSegmentType.NE, fieldName, value, settings);
	}

	public SingleSqlSegment like(String fieldName, Object value) {
		return SegmentFactory.build(SingleSegmentType.LIKE, fieldName, value, settings);
	}

	public SingleSqlSegment llike(String fieldName, Object value) {
		return SegmentFactory.build(SingleSegmentType.LLIKE, fieldName, value, settings);
	}

	public SingleSqlSegment rlike(String fieldName, Object value) {
		return SegmentFactory.build(SingleSegmentType.RLIKE, fieldName, value, settings);
	}

	public SingleSqlSegment isNull(String fieldName) {
		return SegmentFactory.build(SingleSegmentType.ISNULL, fieldName, null, settings);
	}

	public SingleSqlSegment isNotNull(String fieldName) {
		return SegmentFactory.build(SingleSegmentType.ISNOTNULL, fieldName, null, settings);
	}

	public SingleSqlSegment isEmpty(String fieldName) {
		return SegmentFactory.build(SingleSegmentType.ISEMPTY, fieldName, null, settings);

	}

	public SingleSqlSegment isNotEmpty(String fieldName) {
		return SegmentFactory.build(SingleSegmentType.ISNOTEMPTY, fieldName, null, settings);
	}

	public SingleSqlSegment le(String fieldName, Object value) {
		return SegmentFactory.build(SingleSegmentType.LE, fieldName, value, settings);
	}

	public SingleSqlSegment ge(String fieldName, Object value) {
		return SegmentFactory.build(SingleSegmentType.GE, fieldName, value, settings);
	}

	public SingleSqlSegment lt(String fieldName, Object value) {
		return SegmentFactory.build(SingleSegmentType.LT, fieldName, value, settings);
	}

	public SingleSqlSegment gt(String fieldName, Object value) {
		return SegmentFactory.build(SingleSegmentType.GT, fieldName, value, settings);
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
		SingleSegmentType segmentType;
		ValueType valueType;

		try {
			String[] split = StringUtils.split(key, "_");
			Asserts.length(split, 4, "To parsed key: " + key);

			fieldName = split[1];
			segmentType = Enum.valueOf(SingleSegmentType.class, split[2].toUpperCase());
			valueType = Enum.valueOf(ValueType.class, split[3].toUpperCase());
		} catch (Exception e) {
			logger.error("Can't parse " + key, e);
			throw new ParseException("Can't parse {0} as a known segment.", key);
		}

		return build(fieldName, segmentType, valueType, value, settings);
	}

	public static SingleSqlSegment build(String fieldName, SingleSegmentType segmentType, ValueType valueType,
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

	public static SingleSqlSegment build(SingleSegmentType segmentType, String fieldName, Object value,
			Settings settings) {
		try {
			Constructor<?> constructor = segmentType.getValue().getConstructor(String.class, Object.class,
					Settings.class);
			return (SingleSqlSegment) constructor.newInstance(fieldName, value, settings);
		} catch (Exception e) {
			logger.error("Can't build segment.", e);
			throw new ParseException("Can't build segment.");
		}
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

}
