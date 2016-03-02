--Usage: hive -f topn.sql --hivevar select=col1,col2 --hivevar table=tbl --hivevar sortby=col3 --hivevar n=5

SELECT ${hivevar:select}
FROM ${hivevar:table}
SORT BY ${hivevar:sortby} DESC
LIMIT ${hivevar:n};