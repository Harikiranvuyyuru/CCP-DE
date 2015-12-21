###Problem statement:
You want to continuously ingest logs of your java/web application into HDFS.

###Solution:

Steps:
	1. Configure `log4j` properties to use Flume Log4jAppender to send data to Flume Agent. 
	2. Configure Flume agent to receive data and ingest that into HDFS.


