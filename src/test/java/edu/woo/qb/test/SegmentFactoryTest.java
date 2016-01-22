package edu.woo.qb.test;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.woo.qb.segment.SegmentFactory;
import com.woo.qb.segment.impl.combined.CombinedSqlSegment;
import com.woo.qb.segment.impl.single.SingleSqlSegment;

public class SegmentFactoryTest {

	private static final Logger logger = LoggerFactory.getLogger(SegmentFactoryTest.class);

	@Test
	public void testCombined() {
		SegmentFactory $ = SegmentFactory.namedQuery();

		CombinedSqlSegment and = $.and($.eq("name", "woo"), $.eq("status", true),
				$.or($.ge("age", 15), $.le("age", 9)));
		logger.info("sql segment is -> {}", and.asSql());
		logger.info("sql param is -> {}", and.getKeyedParams());

	}

	@Test
	public void testSingle() {
		SegmentFactory $ = SegmentFactory.jdbc();
		SingleSqlSegment eq = $.eq("name", "woo");

		Assert.assertEquals(eq.asSql(), "name = ?");
		Assert.assertEquals(eq.getListParams().get(0), "woo");

		logger.info("sql segment is -> {}", eq.asSql());
		logger.info("sql param is -> {}", eq.getKeyedParams());
	}

}
