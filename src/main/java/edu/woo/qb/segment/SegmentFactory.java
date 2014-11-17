package edu.woo.qb.segment;

import java.lang.reflect.*;

import org.apache.commons.beanutils.*;
import org.apache.commons.lang3.*;
import org.slf4j.*;

import edu.woo.qb.*;
import edu.woo.qb.segment.impl.single.*;

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

	/**
	 * parse a spec SQL string as a SingleSqlSegment
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public static SingleSqlSegment parse(final String key, final String value) {

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

		return build(fieldName, segmentType, valueType, value);
	}

	public static SingleSqlSegment build(String fieldName, SginleSegmentType segmentType, ValueType valueType,
			String value) {
		try {
			Object paramValue = value;
			if (!ValueType.S.equals(valueType)) {
				paramValue = StringUtils.isNotBlank(value) ? ConvertUtils.convert(value, valueType.getValue()) : null;
			}
			Constructor<?> constructor = segmentType.getValue().getConstructor(String.class, Object.class);
			return (SingleSqlSegment) constructor.newInstance(fieldName, paramValue);
		} catch (Exception e) {
			logger.error("Can't build segment.", e);
			throw new ParseException("Can't build segment.");
		}
	}
}
