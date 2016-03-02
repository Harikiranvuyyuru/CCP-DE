--Usage: hive -f coalesce.sql --hivevar columns=col1 --hivevar coalesce=col2,col3,col4 --hivevar table=tbl

--	-------------------------------------------------
--	|  user 	| home 		| office 	| mobile 	|
--	|-----------|-----------|-----------|-----------|
--	|  John		| 012-34567	| 891-01112 | 9876543210|
--	|  James	| null		| 111-22222 | 9988776655|
--	|  Jenny 	| null		| null		| 9786756453|
-- 	-------------------------------------------------

SELECT ${hivevar:columns}, coalesce(${hivevar:coalesce})
FROM ${hivevar:table};

--	-------------------------
--	|  user 	| _c1 		|
--	|-----------|-----------|
--	|  John		| 012-34567	|
--	|  James	| 111-22222	|
--	|  Jenny 	| 9786756453|
-- 	-------------------------
