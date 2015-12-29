package com.example.crunch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;
import org.apache.crunch.FilterFn;
import org.apache.crunch.MapFn;
import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.Pair;
import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.io.From;
import org.apache.crunch.io.To;
import org.apache.crunch.lib.PTables;
import org.apache.crunch.lib.Sort;
import org.apache.crunch.lib.Sort.ColumnOrder;
import org.apache.crunch.lib.Sort.Order;
import org.apache.crunch.types.avro.Avros;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AvroExamples extends Configured implements Tool {

	//private final static File SCHEMA_FILE = new File("src/test/resources/web_event.avsc");
	private final static String LOG_REGEX = "^(\\d+\\.\\d+\\.\\d+\\.\\d+)\\s(-|.*)\\s(-|.*)\\s\\[(.*)\\s\\-\\d{4}\\]\\s\"(\\w{3})\\s(.*)\\s(.*)\"\\s(\\d+)\\s(\\d+)\\s\"(-|.*)\"\\s\"(-|.*)$";;
	
	public static void main(String[] args) throws Exception {
		
		if (args.length != 2){
			System.err.println("This program expects two arguments.");
			System.out.println();
			System.out.println("Usage : com.example.cruch.AvroExamples <input> <output> ");
		}
		
		ToolRunner.run(new Configuration(), new AvroExamples(), args);
	}
	@SuppressWarnings("serial")
	public int run(String[] args) throws Exception {
		
		//Instantiate a MapReduce pipeline
		Pipeline pipeline = new MRPipeline(AvroExamples.class, "AvroExamples", getConf());
		//Read log from file
		PCollection<String> logs = pipeline.read(From.textFile(args[0]));
		//Convert text to avro events
		PCollection<WebEvent> events = logs.parallelDo(new WebEventMapper(), Avros.records(WebEvent.class));
		//Filter out the succeessful requests
		PCollection<WebEvent> succeeded = events.filter(new FilterFn<WebEvent>(){
			public boolean accept(WebEvent event) {
				return event.getResponseCode().equals(200L);
			}
		});
		//Convert WebEvent to Pair<Page, ClientIP>
		PCollection<Pair<String, String>> pageClient = succeeded.parallelDo(new PageClientExtractor(), Avros.pairs(Avros.strings(), Avros.strings()));
		//Get the pages into a collection
		PCollection<String> pages = pageClient.parallelDo(new DoFn<Pair<String, String>, String>(){
			
			public void process(Pair<String, String> pageClient, Emitter<String> emitter){
				String page = pageClient.first();
				emitter.emit(page);
			}
		},Avros.strings());
		//Count Pages
		PTable<String, Long> pageCount = pages.count();
		//Sort table by Descending order of count
		PTable<String, Long> sortedPageCount = PTables.asPTable(Sort.sortPairs(pageCount, ColumnOrder.by(1, Order.DESCENDING)));
		//Write output back as avrofile
		sortedPageCount.write(To.avroFile(args[1]));
		
		//Execute
		PipelineResult result = pipeline.done();
		//Return exit code
		return result.succeeded() ? 0 : 1 ;
	}
	@SuppressWarnings("serial")
	public static class WebEventMapper extends MapFn<String, WebEvent> {
		//Parse logs using Regex and assign them to avro fields
		Pattern log_regex = Pattern.compile(AvroExamples.LOG_REGEX);
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
	//Code for extracting pages
	@SuppressWarnings("serial")
	public static class PageClientExtractor extends DoFn<WebEvent, Pair<String, String>> {
		
		public void process(WebEvent event, Emitter<Pair<String, String>> emitter){
			String page = event.getRequestPage().toString();
			String client = event.getClientIp().toString();
			
			emitter.emit(Pair.of(page, client));
		}
	}
}
