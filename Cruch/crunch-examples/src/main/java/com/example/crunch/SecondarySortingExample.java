package com.example.crunch;

import org.apache.crunch.PCollection;
import org.apache.crunch.PTable;
import org.apache.crunch.Pair;
import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.io.To;
import org.apache.crunch.lib.Sort;
import org.apache.crunch.lib.Sort.ColumnOrder;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class SecondarySortingExample extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		if (args.length != 2){
			System.err.println("Usage: Class <input> <output>");
			System.exit(2);
		}
		ToolRunner.run(new Configuration(), new SecondarySortingExample(), args);
	}
	
	public int run(String[] args) throws Exception{
		
		Pipeline pipeline = new MRPipeline(SecondarySortingExample.class);
		//Read input
		PCollection<String> lines = pipeline.readTextFile(args[0]);
		//Split each line and count them
		PTable<String, Long> wordcount = lines.parallelDo(new Tokenizer(), Writables.strings()).count();
		//Sort
		PCollection<Pair<String, Long>> sorted = Sort.sortPairs(wordcount, ColumnOrder.by(1, Sort.Order.DESCENDING));
		//Write the output
		sorted.write(To.textFile(args[0]));
		//Kick off execution
		PipelineResult result = pipeline.done();
		return result.succeeded() ? 0 : 1 ;
		
	}
}
