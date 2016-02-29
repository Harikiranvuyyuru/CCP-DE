from pyspark import SparkConf
from pyspark import SparkContext
from pyspark.sql import *

conf = SparkConf().setMaster('yarn-client').setAppName('FormatConverter')
sc = SparkContext(conf=conf)
sql = SQLContext(sc)

data = sc.textFile('data').map(lambda line: line.split('\t'))
fields = "code description total_emp salary"
schemaFields = [StructField(field_name, StringType(), True) for field_name in fields.split()]
schema = StructType(schemaFields)
sdata = sql.applySchema(data, schema)

sdata.saveAsParquetFile('data-parquet')
