--Usage: hive -f rank.sql --hivevar fields=occupation,year,salary \
							--hivevar part=year \
							--hivevar order=salary \
							--hivevar table=employment

SELECT  rank() OVER
(PARTITION BY ${hivevar:part} ORDER BY ${hivevar:order}),
${hivevar:fields}
FROM ${hivevar:table};