/**
 * @(#)QueryBuilder.java 2010-9-10
 * 
 * Copyright 2000-2010 by UFida Corporation.
 *
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * UFida Corporation ("Confidential Information").  You shall not 
 * disclose such Confidential Information and shall use it only in 
 * accordance with the terms of the license agreement you entered 
 * into with UFida.
 */
package edu.woo.qb;

import java.util.*;

import org.apache.commons.lang3.*;

import edu.woo.qb.segment.*;
import edu.woo.qb.segment.impl.combined.*;
import edu.woo.qb.segment.impl.single.*;

/**
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public class QueryBuilder {

	private static final String COMBINE_AND = "and";
	private static final String COMBINE_OR = "or";

	private static String DEFAULT_PREFIX = "q_";
	private static String DEFAULT_GROUP_PREFIX = "[";
	private static String DEFAULT_GROUP_SUFFIX = "]";

	private static String DEFAULT_COMBINED = "default";

	private String groupPrefix = DEFAULT_GROUP_PREFIX;
	private String groupSuffix = DEFAULT_GROUP_SUFFIX;
	private String prefix = DEFAULT_PREFIX;

	/**
	 * combine all SQL segment groups
	 * 
	 * @return
	 */
	public SqlSegment build(Map<String, String> queryMap) {
		Map<String, CombinedSqlSegment> filters = parseQueryMap(queryMap);
		// combine all SQL segment groups
		AndSegment andCondition = new AndSegment();
		andCondition.addSegment(new OneEqOneSegment());
		for (String key : filters.keySet()) {
			CombinedSqlSegment condition = filters.get(key);
			andCondition.addSegment(condition);
		}
		return andCondition;
	}

	/**
	 * 
	 * parse and group query strings
	 * 
	 * @param queryMap
	 * @return
	 */
	private Map<String, CombinedSqlSegment> parseQueryMap(Map<String, String> queryMap) {

		Map<String, CombinedSqlSegment> filters = new HashMap<String, CombinedSqlSegment>();
		for (String key : queryMap.keySet()) {
			if (key.startsWith(prefix)) {
				// key example: prefix_[and:a1]paramName_eq_d
				String[] split = StringUtils.split(key, "_");
				String fieldStr = split[1];
				SginleSegmentType conditionType = Enum.valueOf(SginleSegmentType.class, split[2].toUpperCase());
				ValueType propertyType = split.length == 4 ? Enum.valueOf(ValueType.class, split[3].toUpperCase())
						: null;

				CombinedSqlSegment combinedSegment;
				String combineStr = StringUtils.substringBetween(fieldStr, groupPrefix, groupSuffix);
				// if combine data is defined.
				if (StringUtils.isNotEmpty(combineStr)) {
					fieldStr = StringUtils.substringAfter(fieldStr, groupSuffix);
					String[] combine = combineStr.split(":");
					combinedSegment = filters.get(combine[1]);
					if (combinedSegment == null) {
						if (combine[0].toLowerCase().equals(COMBINE_OR)) {
							combinedSegment = new OrSegment();
						} else if (combine[0].toLowerCase().equals(COMBINE_AND)) {
							combinedSegment = new AndSegment();
						}
						filters.put(combine[1], combinedSegment);
					}
				} else {
					combinedSegment = filters.get(DEFAULT_COMBINED);
					if (combinedSegment == null) {
						combinedSegment = new AndSegment();
						filters.put(DEFAULT_COMBINED, combinedSegment);
					}
				}

				// build segment
				SqlSegment segment = SegmentFactory.build(fieldStr, conditionType, propertyType, queryMap.get(key));
				// add segment to its group
				combinedSegment.addSegment(segment);
			}
		}

		return filters;
	}

	public String getPrefix() {
		return prefix;
	}

	public QueryBuilder setPrefix(String prefix) {
		this.prefix = prefix;
		return this;
	}

	public String getGroupPrefix() {
		return groupPrefix;
	}

	public QueryBuilder setGroupPrefix(String groupPrefix) {
		this.groupPrefix = groupPrefix;
		return this;
	}

	public String getGroupSuffix() {
		return groupSuffix;
	}

	public QueryBuilder setGroupSuffix(String groupSuffix) {
		this.groupSuffix = groupSuffix;
		return this;
	}

}
