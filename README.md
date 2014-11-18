Query Builder
====

A simple tool to parse specify filter to sql.




- code sample
```
HashMap<String, String> map = new HashMap<String, String>();
map.put("q_[or:a1]startTime_eq_d", "2009-10-10");
map.put("q_[or:a1]endTime_eq_d", "2009-10-10");
map.put("q_[and:a2]amount_gt_i", "10");
map.put("q_[and:a2]amount_le_i", "20");
map.put("q_buyer_isnull", "");
// map.put("q_param6_like_b", 1);

QueryBuilder builder = new QueryBuilder().setQuery(map);
SqlSegment segment = builder.build();
String asSql = segment.asSql();
Map<String, Object> params = segment.getParams();
```

Output:

sql: 
```
(
	1=1 
	AND (buyer is null) 
	AND (endTime=:endTime_ewZ OR startTime=:startTime_dtu) 
	AND (amount <= ':amount_piK' AND amount > ':amount_USR')
)
```

Named Query Parameters:
```
{
	amount_USR=10, 
	startTime_NJQ=Sat Oct 10 00:00:00 CST 2009, 
	endTime_TRp=Sat Oct 10 00:00:00 CST 2009, 
	amount_piK=20
}
```