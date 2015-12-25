package com.example.crunch;


import org.apache.crunch.PCollection;
import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
//import org.apache.crunch.impl.mem.MemPipeline;
import org.apache.crunch.impl.mr.MRPipeline;
import org.apache.crunch.io.To;
import org.apache.crunch.types.writable.Writables;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class WordCount extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		
		if (args.length != 2) {
			System.err.println("This program expects 2 arguments."
					+ "\n\tUsage: WordCount <input> <output>");
			System.exit(2);
		}
		ToolRunner.run(new Configuration(), new WordCount(), args);
	}
	
	public int run(String[] args) throws Exception {
		Pipeline wcpipeline = new MRPipeline(WordCount.class, getConf());
		PCollection<String> lines = wcpipeline.readTextFile(args[0]);
		
		PCollection<String> words = lines.parallelDo(new Tokenizer(), Writables.strings());
		
		words.count().write(To.textFile(args[1]));
		
		PipelineResult result = wcpipeline.done();
		
		return result.succeeded() ? 0 : 1 ;
	}
}
