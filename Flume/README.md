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
###Flume SpoolingDir Source
```
	a1.channels = ch-1
	a1.sources = src-1
	a1.sources.src-1.type = spooldir
	a1.sources.src-1.channels = ch-1
	a1.sources.src-1.spoolDir = /var/log/apache/flumeSpool
	a1.sources.src-1.fileHeader = true
```

###Flume HDFS Sink
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

###Standalone Flume tuning

#### Batch Size
	- When configured by client code using the flume-core-sdk , to send events to flume avro source.
		The flume client sdk has an appendBatch method. This will take a list of events and send them to the source as a batch. This is the size of the number of events to be passed to the source at one time.
	- When set as a parameter on HDFS sink (or other sinks which support BatchSize parameter)
		This is the number of events written to file before it is flushed to HDFS
#### Channel Capacity
	- This is the maximum capacity number of events of the channel.
#### Channel Transaction Capacity
	- This is the max number of events stored in the channel per transaction.

