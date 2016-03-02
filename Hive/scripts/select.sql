-- Usage: hive -f select.sql --hivevar fields=col1,col2 --hivevar table=tbl --hivevar n=10
SELECT ${hivevar:fields} 
FROM ${hivevar:table}
LIMIT ${hivevar:n};
