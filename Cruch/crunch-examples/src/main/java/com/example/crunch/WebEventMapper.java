package com.example.crunch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.crunch.MapFn;
@SuppressWarnings("serial")
public class WebEventMapper extends MapFn<String, WebEvent> {
	//LogRegex
	private final static String LOG_REGEX = "^(\\d+\\.\\d+\\.\\d+\\.\\d+)\\s(-|.*)\\s(-|.*)\\s\\[(.*)\\s\\-\\d{4}\\]\\s\"(\\w{3})\\s(.*)\\s(.*)\"\\s(\\d+)\\s(\\d+)\\s\"(-|.*)\"\\s\"(-|.*)$";
	//Parse logs using Regex and assign them to avro fields
	Pattern log_regex = Pattern.compile(WebEventMapper.LOG_REGEX);
	WebEvent we = new WebEvent();
	public WebEvent map(String s) {
		
		Matcher matcher = log_regex.matcher(s);
		
		if (matcher.find()){
			we.setClientIp(matcher.group(1));
			we.setClientId(matcher.group(2).equals("-") ? null : matcher.group(2));
			we.setUserId(matcher.group(3).equals("-") ? null : matcher.group(3));
			we.setTimeStamp(matcher.group(4));
			if (matcher.group(5).equals("GET")){
				we.setRequestType(Request.GET);
			}
			else if (matcher.group(5).equals("PUT")){
				we.setRequestType(Request.PUT);
			}
			we.setRequestPage(matcher.group(6));
			we.setHttpProtocol(matcher.group(7));
			we.setResponseCode(Long.parseLong(matcher.group(8)));
			we.setResponseSize(Long.parseLong(matcher.group(9)));
			we.setReferrer(matcher.group(10).equals("-") ? null : matcher.group(10));
			we.setAgentId(matcher.group(11).equals("-") ? null : matcher.group(11));
			
			return we;
		}
		else {
			return new WebEvent();
		}
	}	
}