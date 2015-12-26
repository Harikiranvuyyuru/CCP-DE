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
		//Create an in-memory PCollection<String>
		PCollection<String> pc = MemPipeline.typedCollectionOf(Avros.strings(), "String", "", "String after empty");
		//Filter pc which returns a new PCollection<String>
		PCollection<String> purged = pc.filter(new PurgeEmpty());
		//Some output
		String str_before = pc.toString();
		String str_after = purged.toString();
		System.out.println(str_before);
		System.out.println(str_after);
		//Get an instance of MemPipeline
		Pipeline mp = MemPipeline.getInstance();
		//Kick off execution
		PipelineResult mr = mp.done();
		//Return exit code
		return mr.succeeded() ? 0: 1;
	}
	
	//Custom implementation of FilterFn<String>
	@SuppressWarnings("serial")
	public static class PurgeEmpty extends FilterFn<String> {
		//Accept items only if not empty.
		public boolean accept(String word){
			    return !(word.isEmpty());
			}
		}
	}
