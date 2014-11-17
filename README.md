Query Builder
====

A simple tool to parse specify filter to sql.




- sample
```
HashMap<String, String> map = new HashMap<String, String>();
map.put("q_[or:a1]startTime_eq_d", "2009-10-10");
map.put("q_[or:a1]endTime_eq_d", "2009-10-10");
map.put("q_[and:a2]amount_gt_i", "10");
map.put("q_[and:a2]amount_le_i", "20");
map.put("q_buyer_isnull", "");
// map.put("q_param6_like_b", 1);

QueryBuilder builder = new QueryBuilder().setQuery(map);
```

output will be:
sql: 
```
(
	1=1 
	AND 
		(buyer is null) 
	AND 
		(endTime=:endTime_iPB OR startTime=:startTime_mRd) 
	AND 
		(amount <= '%:amount_MyX' AND amount > '%:amount_SzZ'))
```

Named Query Parameters:
```
{startTime_RYu=Sat Oct 10 00:00:00 CST 2009, amount_SzZ=10, endTime_iHp=Sat Oct 10 00:00:00 CST 2009, amount_MyX=20}
```