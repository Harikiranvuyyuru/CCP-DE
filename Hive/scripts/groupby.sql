--Usage: hive -f groupby.sql --hivevar columns=col1,col2 --hivevar aggr=sum(col3) --hivevar table=tbl

SELECT ${hivevar:columns}, ${hivevar:aggr}
FROM ${hivevar:table}
GROUP BY ${hivevar:columns}; 