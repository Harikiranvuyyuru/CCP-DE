package com.example.crunch;

import org.apache.crunch.DoFn;
import org.apache.crunch.Emitter;

@SuppressWarnings("serial")
public class Tokenizer extends DoFn<String, String>{
	//Reads strings and emits strings	
	@Override
	public void process(String line, Emitter<String> emitter){
		//Split lines into words
		for (String word: line.split("\\s+")){
			//emit words
			emitter.emit(word);
		}
	}

}
