package net.turnbig.qb;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.turnbig.qb.segment.SegmentFactory;
import net.turnbig.qb.segment.Settings;
import net.turnbig.qb.segment.SingleSegmentType;
import net.turnbig.qb.segment.SqlSegment;
import net.turnbig.qb.segment.ValueType;
import net.turnbig.qb.segment.impl.combined.AndSegment;
import net.turnbig.qb.segment.impl.combined.CombinedSqlSegment;
import net.turnbig.qb.segment.impl.combined.OrSegment;

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

	private boolean pretty = false;

	private Settings settings = Settings.jdbc();

	public QueryBuilder() {
	}

	public static QueryBuilder jdbc() {
		QueryBuilder qb = new QueryBuilder();
		qb.setSettings(Settings.jdbc());
		return qb;
	}

	public static QueryBuilder namedQuery() {
		QueryBuilder qb = new QueryBuilder();
		qb.setSettings(Settings.namedQuery());
		return qb;
	}

	public static QueryBuilder mybatis() {
		QueryBuilder qb = new QueryBuilder();
		qb.setSettings(new Settings("#{", "}", true));
		return qb;
	}

	/**
	 * combine all SQL segment groups
	 * 
	 * @return
	 */
	public SqlSegment build(Map<String, String> queryMap) {
		Map<String, CombinedSqlSegment> filters = parseQueryMap(queryMap);
		// combine all SQL segment groups
		AndSegment andCondition = new AndSegment(this.pretty);
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
				SingleSegmentType conditionType = Enum.valueOf(SingleSegmentType.class, split[2].toUpperCase());
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
						combinedSegment = new AndSegment(this.pretty);
						filters.put(DEFAULT_COMBINED, combinedSegment);
					}
				}

				// build segment
				SqlSegment segment = SegmentFactory.build(fieldStr, conditionType, propertyType, queryMap.get(key),
						settings);
				// add segment to its group
				combinedSegment.addSegment(segment);
			}
		}

		return filters;
	}

	public String getPrefix() {
		return prefix;
	}

	public QueryBuilder prefix(String prefix) {
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

	/**
	 * @param namedQueryPrefix
	 *            the namedQueryPrefix to set
	 */
	public QueryBuilder namedQueryPrefix(String namedQueryPrefix) {
		this.settings.setNamedQueryPrefix(namedQueryPrefix);
		return this;
	}


	/**
	 * @param namedQuerySuffix
	 *            the namedQuerySuffix to set
	 */
	public QueryBuilder namedQuerySuffix(String namedQuerySuffix) {
		this.settings.setNamedQuerySuffix(namedQuerySuffix);
		return this;
	}

	/**
	 * @param pretty
	 *            the pretty to set
	 * @return
	 */
	public QueryBuilder pretty(boolean pretty) {
		this.pretty = pretty;
		return this;
	}

}
