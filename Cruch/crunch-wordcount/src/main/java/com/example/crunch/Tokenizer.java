package com.example.crunch;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;

@SuppressWarnings("serial")
public class Tokenizer extends DoFn<String, String>{
	
	@Override
	public void process(String line, Emitter<String> emitter){
		
		for (String word: line.split("\\s+")){
			
			emitter.emit(word);
		}
	}

}
