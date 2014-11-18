Query Builder
====
A simple tool to parse specify filter to sql.




Basic Example
=======
```
	HashMap<String, String> map = new HashMap<String, String>();
	map.put("q_startTime_eq_d", "2009-10-10");
	map.put("q_endTime_eq_d", "2009-10-10");
	map.put("q_buyer_isnull", "");
	map.put("q_amount_gt_i", "20");
	map.put("q_name_LIKE_s", "woo");
	// map.put("q_param6_like_b", 1);

	SqlSegment segment = new QueryBuilder().build(map);

	String asSql = segment.asSql();
	Map<String, Object> params = segment.getParams();
```

Sql:
```
(
	1=1 
	AND (
		amount > ':amount_aZJ' 
		AND endTime=:endTime_JFn 
		AND buyer is null 
		AND name like '%:name_FJy%' 
		AND startTime=:startTime_Rkz
	)
)
```

Named-Query Parameters:
```
{
	name_FJy=woo, 
	endTime_dVZ=Sat Oct 10 00:00:00 CST 2009, 
	amount_aZJ=20, 
	startTime_zYR=Sat Oct 10 00:00:00 CST 2009
}
```

Example with combined groups (and & or)
=======

```
HashMap<String, String> map = new HashMap<String, String>();
map.put("q_[or:a1]startTime_eq_d", "2009-10-10");
map.put("q_[or:a1]endTime_eq_d", "2009-10-10");
map.put("q_[and:a2]amount_gt_i", "10");
map.put("q_[and:a2]amount_le_i", "20");
map.put("q_buyer_isnull", "");
// map.put("q_param6_like_b", 1);

SqlSegment segment = new QueryBuilder().build(map);
String sql = segment.asSql();
Map<String, Object> params = segment.getParams();
```


sql: 
```
(
	1=1 
	AND (buyer is null) 
	AND (endTime=:endTime_ewZ OR startTime=:startTime_dtu) 
	AND (amount <= ':amount_piK' AND amount > ':amount_USR')
)
```

Named-Query Parameters:
```
{
	amount_USR=10, 
	startTime_NJQ=Sat Oct 10 00:00:00 CST 2009, 
	endTime_TRp=Sat Oct 10 00:00:00 CST 2009, 
	amount_piK=20
}
```