package edu.woo.qb.segment.impl.combined;

import java.util.*;

import org.apache.commons.lang3.*;

import edu.woo.qb.segment.*;

/**
 * 组合型查询条件
 * 
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public abstract class CombinedSqlSegment extends SqlSegment {

	protected List<SqlSegment> segments = new ArrayList<SqlSegment>();;

	public String join(String prefix, String suffix, String sep) {
		StringBuilder builder = new StringBuilder("(");

		List<String> list = new ArrayList<String>();
		for (SqlSegment segment : segments) {
			String asSql = segment.asSql();
			list.add(asSql);
		}

		String join = StringUtils.join(list, " " + sep + " ");
		builder.append(join);
		return builder.append(")").toString();
	}

	public void addSegment(SqlSegment segment) {
		segments.add(segment);
		if (segment.isParamRequired()) {
			this.getParams().putAll(segment.getParams());
		}
	}

	public void addSegments(List<SqlSegment> segments) {
		for (SqlSegment segment : segments) {
			this.addSegment(segment);
		}
	}

	public List<SqlSegment> getSegments() {
		return segments;
	}

	public void setSegments(List<SqlSegment> segments) {
		this.segments = segments;
	}

	@Override
	public boolean isParamRequired() {
		for (SqlSegment segment : segments) {
			if (segment.isParamRequired()) {
				return true;
			}
		}
		return false;
	}

}
