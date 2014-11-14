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

	private Map<String, CombinedSqlSegment> filters = new HashMap<String, CombinedSqlSegment>();

	public QueryBuilder setQuery(Map<String, Object> queryMap) {
		for (String key : queryMap.keySet()) {
			if (key.startsWith(prefix)) {

				// key example: prefix_[and:a1]paramName_eq_d
				String[] split = StringUtils.split(key, "_");
				String fieldStr = split[1];
				ConditionType conditionType = Enum.valueOf(ConditionType.class, split[2].toUpperCase());
				ValueType propertyType = Enum.valueOf(ValueType.class, split[3].toUpperCase());

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
				SqlSegment segment = ConditionFactory.build(fieldStr, conditionType, propertyType, queryMap.get(key));

				// add segment to its group
				combinedSegment.addSegment(segment);
			}
		}

		return this;
	}

	/**
	 * 返回sql
	 * 
	 * @return
	 */
	public SqlSegment build() {
		AndSegment andCondition = new AndSegment();
		andCondition.addSegment(new OneEqOneSegment());

		for (String key : filters.keySet()) {
			CombinedSqlSegment condition = filters.get(key);
			andCondition.addSegment(condition);
		}

		return andCondition;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getGroupPrefix() {
		return groupPrefix;
	}

	public void setGroupPrefix(String groupPrefix) {
		this.groupPrefix = groupPrefix;
	}

	public String getGroupSuffix() {
		return groupSuffix;
	}

	public void setGroupSuffix(String groupSuffix) {
		this.groupSuffix = groupSuffix;
	}

	public Map<String, CombinedSqlSegment> getFilters() {
		return filters;
	}

	public void setFilters(Map<String, CombinedSqlSegment> filters) {
		this.filters = filters;
	}

	public static void main(String[] args) {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("q_[or:a1]param1_eq_d", "2009-10-10");
		map.put("q_[or:a1]param2_eq_d", "2009-10-10");
		map.put("q_[and:a2]param1_eq_d", "2009-10-10");
		map.put("q_[and:a2]param2_eq_d", "2009-10-10");
		map.put("q_param2_isnull_i", "");
		// map.put("q_param6_like_b", 1);

		QueryBuilder builder = new QueryBuilder().setQuery(map);
		System.out.println(builder.build());
	}

}
