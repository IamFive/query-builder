package edu.woo.qb.test;

import java.util.HashMap;

import org.junit.Test;

import ch.qos.logback.classic.Logger;
import edu.woo.qb.QueryBuilder;

public class QueryBuilderTest {
	

	@Test
	public void test() {

		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("q_[or:a1]startTime_eq_d", "2009-10-10");
		map.put("q_[or:a1]endTime_eq_d", "2009-10-10");
		map.put("q_[and:a2]amount_gt_d", "10");
		map.put("q_[and:a2]amount_le_d", "20");
		map.put("q_buyer_isnull", "");
		// map.put("q_param6_like_b", 1);

		QueryBuilder builder = new QueryBuilder().setQuery(map);
		System.out.println(builder.build());
	}

}
