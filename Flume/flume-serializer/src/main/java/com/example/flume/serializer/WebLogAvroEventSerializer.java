package com.example.flume.serializer;

import java.io.File;
import java.io.OutputStream;
import java.io.IOException;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.example.flume.event.Request;
import com.example.flume.event.WebLogEvent;

import org.apache.flume.Event;
import org.apache.flume.Context;
import org.apache.flume.serialization.AbstractAvroEventSerializer;
import org.apache.flume.serialization.EventSerializer;
import org.apache.avro.Schema;

import org.apache.commons.codec.Charsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebLogAvroEventSerializer extends AbstractAvroEventSerializer<WebLogEvent> {

	private static final Logger logger = LoggerFactory.getLogger(WebLogAvroEventSerializer.class);
	/*
	*	Note: schemaFile have to be some in the local filesystem
	*/

	private static final File schemaFile = new File("/usr/lib/flume-ng/resources/weblog_event.avsc");
	private final OutputStream out;
	private final Schema schema;
	private final String regex_log;
	
	public WebLogAvroEventSerializer(OutputStream out) throws IOException {
		this.out = out;
		this.schema = new Schema.Parser().parse(schemaFile);
		this.regex_log = "^(.*)\\s(-|.*)\\s(-|.*)\\s\\[(.*)\\s\\-\\d{4}\\]\\s\"(\\w{3})\\s(.*)\\s(.*)\"\\s(\\d+)\\s(\\d+)\\s\"(-|.*)\"\\s\"(-|.*)$";
	}

	@Override
	public OutputStream getOutputStream(){
		return out;
	}

	@Override
	public Schema getSchema(){
		return schema;
	}

	public String getRegexLog(){
		return regex_log;
	}
	@Override
	protected WebLogEvent convert(Event event){
		WebLogEvent wle = new WebLogEvent();

		String le = new String(event.getBody(), Charsets.UTF_8);
		Pattern p = Pattern.compile(regex_log);
		Matcher m = p.matcher(le);

		if (m.find()){
			wle.setClientIp(m.group(1));
			wle.setClientId(m.group(2).equals("-") ? null : m.group(2));
			wle.setUserId(m.group(3).equals("-") ? null : m.group(3)) ;
			wle.setTimeStamp(m.group(4));
			//http request type
			if (m.group(5).equals("GET")){
				wle.setRequestType(Request.GET);
			}
			else if (m.group(5).equals("POST")) {
				wle.setRequestType(Request.POST);
			}
			else { 
				logger.warn("Unknown Request Type : " + m.group(5)); 
			}
			wle.setRequestPage(m.group(6));
			wle.setHttpProtocol(m.group(7));
			wle.setResponseCode(Integer.parseInt(m.group(8)));
			wle.setResponseSize(Integer.parseInt(m.group(9)));
			wle.setReferrer(m.group(10).equals("-") ? null : m.group(10));
			wle.setUserAgent(m.group(11).equals("-") ? null : m.group(11));
		}
		else {
			logger.error("Unknown log format.");
		}

		return wle;
	}

	public static class Builder implements EventSerializer.Builder {
		public EventSerializer build(Context context, OutputStream out){
			WebLogAvroEventSerializer wle_writer = null;
			try {
				wle_writer = new WebLogAvroEventSerializer(out);
				wle_writer.configure(context);
			}
			catch (IOException e) {
				logger.error("Unable to parse schema file. Error follows : ", e);
			}
			return wle_writer;
		}
	}
}
