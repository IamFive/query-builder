/**
 * @(#)ConditionType.java 2010-9-10
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
