#Flume

###Flume Client

###Flume Embedded Client

###Flume Sources
####Avro source
Listens on Avro port and receives events from external Avro client streams. When paired with the built-in Avro Sink on another (previous hop) Flume agent, it can create tiered collection topologies.
```
	a1.sources = r1
	a1.channels = c1
	a1.sources.r1.type = avro
	a1.sources.r1.channels = c1
	a1.sources.r1.bind = 0.0.0.0
	a1.sources.r1.port = 4141
```
####Thrift source
Listens on Thrift port and receives events from external Thrift client streams. When paired with the built-in ThriftSink on another (previous hop) Flume agent, it can create tiered collection topologies.
```
	a1.sources = r1
	a1.channels = c1
	a1.sources.r1.type = thrift
	a1.sources.r1.channels = c1
	a1.sources.r1.bind = 0.0.0.0
	a1.sources.r1.port = 4141
```
####Exec source
Exec source runs a given Unix command on start-up and expects that process to continuously produce data on standard out (stderr is simply discarded, unless property logStdErr is set to true). If the process exits for any reason, the source also exits and will produce no further data. This means configurations such as `cat [named pipe]` or `tail -F [file]` are going to produce the desired results where as date will probably not - the former two commands produce streams of data where as the latter produces a single event and exits.

```
	a1.sources = r1
	a1.channels = c1
	a1.sources.r1.type = exec
	a1.sources.r1.command = tail -F /var/log/secure
	a1.sources.r1.channels = c1
```

###Flume SpoolingDir Source
This source lets you ingest data by placing files to be ingested into a “spooling” directory on disk. This source will watch the specified directory for new files, and will parse events out of new files as they appear. The event parsing logic is pluggable. After a given file has been fully read into the channel, it isrenamed to indicate completion (or optionally deleted).

Unlike the Exec source, this source is reliable and will not miss data, even if Flume is restarted or killed. In exchange for this reliability, only immutable,uniquely-named files must be dropped into the spooling directory. Flume tries to detect these problem conditions and will fail loudly if they are violated:
	1. If a file is written to after being placed into the spooling directory, Flume will print an error to its log file and stop processing.
	2. If a file name is reused at a later time, Flume will print an error to its log file and stop processing.

To avoid the above issues, it may be useful to add a unique identifier (such as a timestamp) to log file names when they are moved into the spooling directory.

Despite the reliability guarantees of this source, there are still cases in which events may be duplicated if certain downstream failures occur. This is consistent with the guarantees offered by other Flume components.

```
	a1.channels = ch-1
	a1.sources = src-1
	a1.sources.src-1.type = spooldir
	a1.sources.src-1.channels = ch-1
	a1.sources.src-1.spoolDir = /var/log/apache/flumeSpool
	a1.sources.src-1.fileHeader = true
```
####Twitter 1% firehose Source (experimental)
Experimental source that connects via Streaming API to the 1% sample twitter firehose, continously downloads tweets, converts them to Avro format and sends Avro events to a downstream Flume sink. Requires the consumer and access
tokens and secrets of a Twitter developer account.

```
	a1.sources = r1
	a1.channels = c1
	a1.sources.r1.type = org.apache.flume.source.twitter.TwitterSource
	a1.sources.r1.channels = c1
	a1.sources.r1.consumerKey = YOUR_TWITTER_CONSUMER_KEY
	a1.sources.r1.consumerSecret = YOUR_TWITTER_CONSUMER_SECRET
	a1.sources.r1.accessToken = YOUR_TWITTER_ACCESS_TOKEN
	a1.sources.r1.accessTokenSecret = YOUR_TWITTER_ACCESS_TOKEN_SECRET
	a1.sources.r1.maxBatchSize = 10
	a1.sources.r1.maxBatchDurationMillis = 200
```

####Kafka Source
Kafka Source is an Apache Kafka consumer that reads messages from a Kafka topic. If you have multiple Kafka sources running, you can configure them with the same Consumer Group so each will read a unique set of partitions for the topic.

```
	tier1.sources.source1.type = org.apache.flume.source.kafka.KafkaSource
	tier1.sources.source1.channels = channel1
	tier1.sources.source1.zookeeperConnect = localhost:2181
	tier1.sources.source1.topic = test1
	tier1.sources.source1.groupId = flume
	tier1.sources.source1.kafka.consumer.timeout.ms = 100
```

###Sinks

####HDFS Sink
his sink writes events into the Hadoop Distributed File System (HDFS). It currently supports creating text and sequence files. It supports compression in both file types. The files can be rolled (close current file and create a new one) periodically based on the elapsed time or size of data or number of events. It also buckets/partitions data by attributes like timestamp or machine where the event originated. The HDFS directory path may contain formatting escape sequences that will replaced by the HDFS sink to generate a directory/file name to store the events. Using this sink requires hadoop to be installed so that Flume can use the Hadoop jars to communicate with the HDFS cluster. Note that a version of Hadoop that supports the sync() call is required.

```
	a1.channels = c1
	a1.sinks = k1
	a1.sinks.k1.type = hdfs
	a1.sinks.k1.channel = c1
	a1.sinks.k1.hdfs.path = /flume/events/%y-%m-%d/%H%M/%S
	a1.sinks.k1.hdfs.filePrefix = events-
	a1.sinks.k1.hdfs.round = true
	a1.sinks.k1.hdfs.roundValue = 10
	a1.sinks.k1.hdfs.roundUnit = minute
```
####Hive Sink
This sink streams events containing delimited text or JSON data directly into a Hive table or partition. Events are written using Hive transactions. As soon as a set of events are committed to Hive, they become immediately visible to Hive queries. Partitions to which flume will stream to can either be pre-created or, optionally, Flume can create them if they are missing. Fields from incoming event data are mapped to corresponding columns in the Hive table. This
sink is provided as a preview feature and not recommended for use in production.

```
	a1.channels = c1
	a1.channels.c1.type = memory
	a1.sinks = k1
	a1.sinks.k1.type = hive
	a1.sinks.k1.channel = c1
	a1.sinks.k1.hive.metastore = thrift://127.0.0.1:9083
	a1.sinks.k1.hive.database = logsdb
	a1.sinks.k1.hive.table = weblogs
	a1.sinks.k1.hive.partition = asia,%{country},%y-%m-%d-%H-%M
	a1.sinks.k1.useLocalTimeStamp = false
	a1.sinks.k1.round = true
	a1.sinks.k1.roundValue = 10
	a1.sinks.k1.roundUnit = minute
	a1.sinks.k1.serializer = DELIMITED
	a1.sinks.k1.serializer.delimiter = "\t"
	a1.sinks.k1.serializer.serdeSeparator = '\t'
	a1.sinks.k1.serializer.fieldnames =id,,msg
```

####Logger Sink
Logs event at INFO level. Typically useful for testing/debugging purpose.

```
	a1.channels = c1
	a1.sinks = k1
	a1.sinks.k1.type = logger
	a1.sinks.k1.channel = c1
```
####Avro Sink
This sink forms one half of Flume’s tiered collection support. Flume events sent to this sink are turned into Avro events and sent to the configured hostname / port pair. The events are taken from the configured Channel in batches of the configured batch size.

```
	a1.channels = c1
	a1.sinks = k1
	a1.sinks.k1.type = avro
	a1.sinks.k1.channel = c1
	a1.sinks.k1.hostname = 10.10.10.10
	a1.sinks.k1.port = 4545
```

####Thrift Sink
This sink forms one half of Flume’s tiered collection support. Flume events sent to this sink are turned into Thrift events and sent to the configured hostname / port pair. The events are taken from the configured Channel in batches of the configured batch size.

```
	a1.channels = c1
	a1.sinks = k1
	a1.sinks.k1.type = thrift
	a1.sinks.k1.channel = c1
	a1.sinks.k1.hostname = 10.10.10.10
	a1.sinks.k1.port = 4545
```

####File Roll Sink
Stores events on the local filesystem. 

```
	a1.channels = c1
	a1.sinks = k1
	a1.sinks.k1.type = file_roll
	a1.sinks.k1.channel = c1
	a1.sinks.k1.sink.directory = /var/log/flume
```

####Kite Dataset Sink
Experimental sink that writes events to a Kite Dataset. This sink will deserialize the body of each incoming event and store the resulting record in a Kite Dataset. It determines target Dataset by loading a dataset by URI.
The only supported serialization is avro, and the record schema must be passed in the event headers, using either flume.avro.schema.literal with the JSON schema representation or flume.avro.schema.url with a URL where the schema may be found ( hdfs:/... URIs are supported). This is compatible with the Log4jAppender flume client and the spooling directory source’s Avro deserializer using deserializer.schemaType = LITERAL.

###Standalone Flume tuning

#### Batch Size
	- When configured by client code using the flume-core-sdk , to send events to flume avro source.
		The flume client sdk has an appendBatch method. This will take a list of events and send them to the source as a batch. 
		This is the size of the number of events to be passed to the source at one time.
	- When set as a parameter on HDFS sink (or other sinks which support BatchSize parameter)
		This is the number of events written to file before it is flushed to HDFS
#### Channel Capacity
	- This is the maximum capacity number of events of the channel.
#### Channel Transaction Capacity
	- This is the max number of events stored in the channel per transaction.

