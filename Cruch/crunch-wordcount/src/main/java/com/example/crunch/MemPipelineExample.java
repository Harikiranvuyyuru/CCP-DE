package com.example.crunch;


import org.apache.crunch.FilterFn;
import org.apache.crunch.PCollection;
import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.impl.mem.MemPipeline;
import org.apache.crunch.types.avro.Avros;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class MemPipelineExample extends Configured implements Tool {

	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new MemPipelineExample(), args);
	}
	
	
	public int run(String[] args) {
		
		PCollection<String> pc = MemPipeline.typedCollectionOf(Avros.strings(), "String", "", "String after empty");
		PCollection<String> purged = pc.filter(new Purge());
		String str_before = pc.toString();
		String str_after = purged.toString();
		System.out.println(str_before);
		System.out.println(str_after);
		Pipeline mp = MemPipeline.getInstance();
		PipelineResult mr = mp.done();
		
		return mr.succeeded() ? 0: 1;
	}
	
	@SuppressWarnings("serial")
	public static class Purge extends FilterFn<String> {
		
		
		public boolean accept(String word){
			    return !(word.isEmpty());
			}
		}
	}
