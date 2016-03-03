--Usage: hive -f histogram.sql --hivevar column=age --hivevar bin=10 --hivevar table=users

--Note: Above example is to calculate age distribution of users over 10 bins. 
--		Remember, Data needs to be sorted by age for histogram function.

SELECT histogram_numeric(${hivevar:column}, ${hivevar:bin})
FROM ${hivevar:table};