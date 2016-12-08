package net.turnbig.qb.segment;

import net.turnbig.qb.segment.impl.single.AnySegment;
import net.turnbig.qb.segment.impl.single.EqualSegment;
import net.turnbig.qb.segment.impl.single.GreatOrEqualSegment;
import net.turnbig.qb.segment.impl.single.GreatThanSegment;
import net.turnbig.qb.segment.impl.single.InSegment;
import net.turnbig.qb.segment.impl.single.IsEmptySegment;
import net.turnbig.qb.segment.impl.single.IsNullSegment;
import net.turnbig.qb.segment.impl.single.LeftLikeSegment;
import net.turnbig.qb.segment.impl.single.LessOrEqualSegment;
import net.turnbig.qb.segment.impl.single.LessThanSegment;
import net.turnbig.qb.segment.impl.single.LikeSegment;
import net.turnbig.qb.segment.impl.single.NotEmptySegment;
import net.turnbig.qb.segment.impl.single.NotEqualSegment;
import net.turnbig.qb.segment.impl.single.NotInSegment;
import net.turnbig.qb.segment.impl.single.NotNullSegment;
import net.turnbig.qb.segment.impl.single.RightLikeSegment;
import net.turnbig.qb.segment.impl.single.SingleSqlSegment;

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
	ANY(AnySegment.class),
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
