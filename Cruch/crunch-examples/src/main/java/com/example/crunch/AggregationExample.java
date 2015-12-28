package com.example.crunch;

import org.apache.crunch.PTable;
import org.apache.crunch.Pipeline;
import org.apache.crunch.PipelineResult;
import org.apache.crunch.fn.Aggregators;
import org.apache.crunch.impl.mem.MemPipeline;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

public class AggregationExample extends Configured implements Tool {
	
	public static void main(String[] args) throws Exception {
		ToolRunner.run(new Configuration(), new AggregationExample(), args);
	}
	
	public int run(String[] args) throws Exception {
		
		//Table of generic type
		PTable<String, Integer> pt1 = MemPipeline.tableOf("x", 1, "y", 5, "z", 7, "x", 10, "y", 15);
		/* Grouping by key and combining values
		 * 
		 * Note : .groupByKey returns PGroupedTable, but .combineValues returns PTable
		 * 
		 * Warning : built-in aggregators only work on generic datatypes.
		 */
		
		PTable<String, Integer> aggtable = pt1.groupByKey().combineValues(Aggregators.SUM_INTS());
		//Print Table data
		System.out.println(aggtable.asMap().toString());
		//Execute
		Pipeline mp = MemPipeline.getInstance();
		PipelineResult result = mp.done();
		//Return Exit code
		return result.succeeded() ? 0 : 1 ;
	}

}
