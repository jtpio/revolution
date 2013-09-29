package com.speed.run.dialog;

import java.util.LinkedList;

import com.speed.run.managers.Config;

public class SentenceFactory {
	
	public static LinkedList<Sentence> randomSentences() {
		LinkedList<Sentence> res = new LinkedList<Sentence>();
		
		String[] input = {
			"The noise witnesss the immense son", 
			"The deranged observation structures the cork", 
			"When does the linen discuss the shiny history?",
			"Why does the flowery year defend the market?",
			"When does the shock greet the acceptable chance?"
		};		
		
		for (int i = 0; i < input.length; i++) {
			res.add(new Sentence((float)i * (Config.SENTENCE_DURATION + 2), Config.SENTENCE_DURATION, input[i]));
		}
		
		return res;
	}
	
}
