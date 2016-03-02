--Usage: hive -f movingavg.sql --hivevar fields=col1,col2 --hivevar avg=col3 --hivevar window=2 --hivevar table=tbl
--Note: window of n means 2*n + 1. e.g. 2*2 + 1 = 5
 
SELECT ${hivevar:fields}, avg(${hivevar:avg}) 
OVER (ROWS BETWEEN ${hivevar:window} PRECEDING AND ${hivevar:window} FOLLOWING)
FROM ${hivevar:table};