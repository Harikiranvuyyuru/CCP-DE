#Sqoop

###Importing data into HDFS.

```shell
	sqoop import \
	--connect jdbc:mysql://mysql.example.com/database \
	--table table_name \
	--target-dir /path/to/target
```
####Importing all tables.
```shell
	sqoop import-all-tables \
	--connect jdbc:mysql://mysql.example.com/sqoop \
	--username sqoop \
	--password sqoop
```
####Importing only a subset of data.
```shell
	sqoop import \
	--connect jdbc:mysql://mysql.example.com/sqoop \
	--username sqoop \
	--password sqoop \
	--table cities \
	--where "country = 'USA'"
```
###Importing data into HIVE Table.

####If target table already exists in Hive.
```shell
	sqoop import \
	--connect jdbc:mysql://mysql_server/database \
	--table table_name \
	--hive-import
```
####If target table does not exist in Hive.
```shell
	sqoop import \
	--connect jdbc:mysql://mysql_server/database \
	--table table_name \
	--hive-import \
	--create-hive-table
```
####Import into a partition.
```shell
	sqoop import \
	--connect jdbc:mysql://mysql.example.com/database \
	--username sqoop \
	--password sqoop \
	--table cities \
	--hive-import \
	--hive-partition-key day \
	--hive-partition-value "2013-05-22"
```
####Dropping delimiters, drops `\n`, `\t` and `\01` from all string like fields.
```shell
	sqoop import \
	--connect jdbc:mysql://mysql.example.com/sqoop \
	--username sqoop \
	--password sqoop \
	--table cities \
	--hive-import \
	--hive-drop-import-delims
```
###Importing data into HCATALOG Table.

####If target table does not exist in HCatalog.
```shell
	sqoop import \
	--connect jdbc:mysql://mysql.example.com/prod_db \
	--table products \
	--hcatalog-table products \
	--create-hcatalog-table
```
####If target table exists in HCatalog.
```shell
	sqoop import \
	--connect jdbc:mysql://mysql.example.com/prod_db \
	--table products \
	--hcatalog-table products
```

###More options

####Tuning Parallelism.
```shell
	-m, --num-mappers <n>
```

####Direct dump for faster data transfer.
```shell
	--direct
```

####Incremental import.
```shell
	--incremental <append/lastmodified>
	--check-column <col>
	--last-value <col_val>
```

####Compress data.
```shell
	--compress
	--compression-codec org.apache.hadoop.io.compress.BZip2Codec
```

####Supported file formats
```shell
	--as-avrodatafile
	--as-sequencefile
	--as-parquetfile
```

####Merge.
```shell
	sqoop merge -\
	-new-dir new_datadir \
	--old-dir old_datadir \
	--target output_dir \
	import_program.jar import_class
```