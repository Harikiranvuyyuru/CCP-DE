--Usage: hive -f transform.sql --hivevar fields --hivevar column=dt --hivevar pyfile=dateformatter.py --hivevar table=tbl

-- dateformatter.py:
--	#!/usr/bin/env python

--	from dateutil import parser
--	import sys

--	for s in sys.stdin:
--	    dt = parser.parse(s)
--	    print(dt.strftime("%D"))

--	------------
--	|dt 	    |
--	|-----------|
--	|1/11/67    |
--	|-----------|
--	|10-06-1998 |
--	-------------

SELECT ${hivevar:fields}, TRANSFORM(${hivevar:column})
USING 'python ${hivevar:pyfile}' FROM ${hivevar:table};

--	------------
--	|_c0	    |
--	|-----------|
--	|01/11/67   |
--	|-----------|
--	|10/06/98   |
--	-------------
