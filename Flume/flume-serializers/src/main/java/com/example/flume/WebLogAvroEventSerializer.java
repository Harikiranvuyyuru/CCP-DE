package com.example.flume;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.common.base.Charsets;
import org.apache.avro.Schema;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.serialization.AbstractAvroEventSerializer;
import org.apache.flume.serialization.EventSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WebLogAvroEventSerializer extends AbstractAvroEventSerializer<WebLogEvent>{
	//Logging
	private static final Logger logger = LoggerFactory.getLogger(WebLogAvroEventSerializer.class);
	
	private static final File schemaFile = new File("src/main/resources/WebLogEvent.avsc");
	
	private final OutputStream out;
	private final Schema schema;
	
	public WebLogAvroEventSerializer(OutputStream out) throws IOException{
		this.out = out;
		this.schema = new Schema.Parser().parse(schemaFile);
		
	}
	@Override
	public OutputStream getOutputStream() {
		return out;
	}
	@Override
	public Schema getSchema() {
		return schema;
	}

	protected WebLogEvent convert(Event event){
		
		WebLogEvent wle = new WebLogEvent();
		
		String le = new String(event.getBody(), Charsets.UTF_8);
		//95.22.50.11 - - [09/Sep/2013:16:36:44 -0700] "GET /test.php HTTP/1.1" 200 1832 "-" "Mozilla/5.0 (X11; Linux x86_64; rv:6.0a1) Gecko/20110421 Firefox/6.0a1"
		String regex_log = "^(\\d+\\.\\d+\\.\\d+\\.\\d+)\\s(-|.*)\\s(-|.*)\\s\\[(.*)\\s\\-\\d{4}\\]\\s\"(\\w{3})\\s(.*)\\s(.*)\"\\s(\\d+)\\s(\\d+)\\s\"(-|.*)\"\\s\"(-|.*)$";
		Pattern p = Pattern.compile(regex_log);
		
		Matcher m = p.matcher(le);
		
		if (m.find()){
			wle.setClientIp(m.group(1));
			wle.setClientId((m.group(2) == "-" ? null : m.group(2)));
			wle.setUserId((m.group(3) == "-") ? null : m.group(3));
			wle.setTimeStamp(m.group(4));
			if (m.group(5) == "GET"){
				wle.setRequestType(WebLogEvent.request.GET);
			}
			else if (m.group(5) == "PUT"){
				wle.setRequestType(WebLogEvent.request.PUT);
			}
			else {
				logger.warn("Log Event %s Request type is not in [\"GET\",\"PUT\"]", le);
			}
			wle.setRequestPage(m.group(6));
			wle.setHttpProtocol(m.group(7));
			wle.setResponseCode(Integer.parseInt(m.group(8)));
			wle.setResponseSize(Integer.parseInt(m.group(9)));
			wle.setReferrer((m.group(10) == "-") ? null : m.group(10));
			wle.setUserAgent((m.group(11) == "-") ? null : m.group(11));
		}
			return wle;
		}

		public static class Builder implements EventSerializer.Builder {
			
			public EventSerializer build(Context context, OutputStream out){
				WebLogAvroEventSerializer writer =  null;
				try{
					writer = new WebLogAvroEventSerializer(out);
					writer.configure(context);
					}
				catch (IOException e){
					logger.error("Unable to parse schema file, Error follows: ", e);
				}
				
				return writer;
			}
		}
		
}
	
	
