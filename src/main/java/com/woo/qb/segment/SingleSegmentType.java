package com.woo.qb.segment;

import com.woo.qb.segment.impl.single.EqualSegment;
import com.woo.qb.segment.impl.single.GreatOrEqualSegment;
import com.woo.qb.segment.impl.single.GreatThanSegment;
import com.woo.qb.segment.impl.single.InSegment;
import com.woo.qb.segment.impl.single.IsEmptySegment;
import com.woo.qb.segment.impl.single.IsNullSegment;
import com.woo.qb.segment.impl.single.LeftLikeSegment;
import com.woo.qb.segment.impl.single.LessOrEqualSegment;
import com.woo.qb.segment.impl.single.LessThanSegment;
import com.woo.qb.segment.impl.single.LikeSegment;
import com.woo.qb.segment.impl.single.NotEmptySegment;
import com.woo.qb.segment.impl.single.NotEqualSegment;
import com.woo.qb.segment.impl.single.NotInSegment;
import com.woo.qb.segment.impl.single.NotNullSegment;
import com.woo.qb.segment.impl.single.RightLikeSegment;
import com.woo.qb.segment.impl.single.SingleSqlSegment;

/**
 * 条件类型对应
 * 
 * @author wuqb
 * @date 2010-9-10
 * @version $Revision$
 */
public enum SingleSegmentType {

	//@off
	EQ(EqualSegment.class), 
	NE(NotEqualSegment.class), 
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
	IN(InSegment.class),
	NIN(NotInSegment.class),
	;
	//@on

	private Class<? extends SingleSqlSegment> conditionType;

	SingleSegmentType(Class<? extends SingleSqlSegment> conditionType) {
		this.conditionType = conditionType;
	}

	public Class<? extends SingleSqlSegment> getValue() {
		return this.conditionType;
	}

}
