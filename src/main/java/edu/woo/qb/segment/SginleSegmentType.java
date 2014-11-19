package edu.woo.qb.segment;

import edu.woo.qb.segment.impl.single.*;

/**
 * 条件类型对应
 * 
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public enum SginleSegmentType {

	//@off
	EQ(EqualSegment.class), 
	ISNULL(IsNullSegment.class), 
	ISNOTNULL(NotNullSegment.class), 
	ISEMPTY(IsEmptySegment.class), 
	ISNOTEMPTY(NotEmptySegment.class), 
	LIKE(LikeSegment.class), 
	LLIKE(LeftLikeSegment.class), 
	RLIKE(RightLikeSegment.class),
	LE(LessOrEqualSegment.class),
	GE(GreatOrEqualSegment.class),
	LT(LessThanSegment.class),
	GT(GreatThanSegment.class),
	;
	//@on

	private Class<? extends SingleSqlSegment> conditionType;

	SginleSegmentType(Class<? extends SingleSqlSegment> conditionType) {
		this.conditionType = conditionType;
	}

	public Class<? extends SingleSqlSegment> getValue() {
		return this.conditionType;
	}

}
