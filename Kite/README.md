# Kite SDK

### Kite CLI Usage

```
kite-dataset [options] [command] [command options]

  Options:

    --version
	Print Kite version and exit
    -v, --verbose, --debug
	Print extra debugging information

  Commands:

    help
		Retrieves details on the functions of other commands
    create
		Create an empty dataset
    copy
		Copy records from one Dataset to another
    transform
		Transform records from one Dataset and store them in another
    update
		Update the metadata descriptor for dataset
    delete
		Delete a view or a dataset and its metadata
    schema
		Show the schema for a Dataset
    info
		Print all metadata for a Dataset
    show
		Print the first n records in a Dataset
    obj-schema
		Build a schema from a java class
    inputformat-import
		Import records into a Dataset using an existing InputFormat
    csv-schema
		Build a schema from a CSV data sample
    csv-import
		Copy CSV records into a Dataset
    json-schema
		Build a schema from a JSON data sample
    json-import
		Copy JSON records into a Dataset
    partition-config
		Builds a partition strategy for a schema
    mapping-config
		Builds a partition strategy for a schema
    log4j-config
		Build a log4j config to log events to a dataset
    flume-config
		Build a Flume config to log events to a dataset
    tar-import
		Import files in tarball into a Dataset

  Examples:

    # print information for create
    kite-dataset help create

  See kite-dataset help <command> for more information on a specific command.
```

### Kite properties

`kite.writer.cache-size` : controls the number of files kept open by an HDFS or Hive dataset writer.
The default cache size is 10.

```shell
    kite-dataset create users --schema user.avsc --set kite.writer.cache-size=20
    kite-dataset update dataset:hdfs:/user/me/datasets/annual_earnings --set kite.writer.cache-size=12
```
`parquet.block.size` : The default block size is 128MB.

The amount of data kept in memory for each file could be up to the Parquet block size in bytes. That means that the 
upper bound for a writer's memory consumption is `parquet.block.size` multiplied by the `kite.writer.cache-size`. 
It is important that this number doesn't exceed a reasonable portion of the heap memory allocated to the process, or 
else the write could fail with an `OutOfMemoryException`.

###Partition Strategy

Partition strategies are defined in JSON format. The strategy must be a list of objects—name/value pairs—each of 
which defines a field in the partition strategy. All field definitions require at least two attributes:

    source – a source field on the entity, such as “created_at”
    type – the type of partition derived from the source data, such as “year”

The available types are:
    Type        |  Source               |    Produces  |  Requirements|
    ------------|-----------------------|--------------|--------------|
    year        |  a timestamp          |    year, like 2014  |  must be a long |
    month       |  a timestamp          |    month, 1-12   |  must be a long |
    day         |  a timestamp          |    day of the month, 1-31 |  must be a long |
    hour        |  a timestamp          |    hour in the day, 0-23 |  must be a long |
    minute      |  a timestamp          |    minute in the hour, 0-59 |  must be a long |
    identity    |  any string or number |    the source value, unchanged   |  must be a string or numeric |
    hash        |  any object  |    int hash of the value, 0-B | requires B, buckets integer attribute |