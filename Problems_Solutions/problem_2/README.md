###Problem Statement
Assume you have a table in Hive which is partitioned by day (or more likely, year, month and day), and you want to process only one day worth of data (current day or previous day), and you want to schedule that using Oozie.

###Solution
Unfortunatly, Hive does not provide a function/variable for current date. Which means you'd have to write a [UDF](/Hive/hive-udfs/) to return current date, and use that as the value for your WHERE clause parameters.

Note: In order to be able to use Hive's `datetime` function, your UDF has to return date/time of a specific format(yyyy-mm-dd).
