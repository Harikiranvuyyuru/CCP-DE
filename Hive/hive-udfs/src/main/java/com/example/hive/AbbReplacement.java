package com.example.hive;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

public class AbbReplacement extends UDF {
	
	public static Text evaluate(Text s){
		
		Text out = new Text();
		
		Map<String, String> abb_ext = new HashMap<String, String>();
		
		abb_ext.put("Ft", "Fort");
		abb_ext.put("Pk", "Park");
		abb_ext.put("Mtn", "Mountain");
		
		String in = s.toString();
		
		for(String abb: abb_ext.keySet()){
			Pattern p = Pattern.compile(abb);
			Matcher m = p.matcher(in);
			if (m.find()){
				String str_out = in.replaceAll(abb, abb_ext.get(abb));
				out.set(str_out);
				return out;
			}
			
	}
		return s;
	}	
}
