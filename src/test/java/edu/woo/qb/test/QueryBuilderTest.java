package edu.woo.qb.test;

import java.util.HashMap;

import org.junit.Test;

import edu.woo.qb.QueryBuilder;
import edu.woo.qb.segment.SqlSegment;

public class QueryBuilderTest {

	@Test
	public void testCombined() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("q_[or:a1]startTime_eq_d", "2009-10-10");
		map.put("q_[or:a1]endTime_eq_d", "2009-10-10 12:11:11");
		map.put("q_[and:a2]amount_gt_i", "10");
		map.put("q_[and:a2]amount_le_i", "20");
		map.put("q_buyer_isnull", "");
		// map.put("q_param6_like_b", 1);

		SqlSegment segment = QueryBuilder.jdbc().pretty(true).build(map);
		System.out.println(segment);
	}

	@Test
	public void testSingle() {

		HashMap<String, String> map = new HashMap<String, String>();
		map.put("s_startTime_eq_d", "2009-10-10");
		map.put("s_endTime_eq_d", "2009-10-10");
		map.put("s_buyer_isnull", "");
		map.put("s_amount_gt_i", "20");
		map.put("s_name_LIKE_s", "woo");
		// map.put("q_param6_like_b", 1);

		SqlSegment segment = QueryBuilder.mybatis().prefix("s_").pretty(true).build(map);

		System.out.println(segment);
	}
}
