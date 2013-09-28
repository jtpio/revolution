package com.speed.run.managers;

import java.util.LinkedList;

public class SentenceFactory {

	public static LinkedList<String> randomSentences() {
		LinkedList<String> res = new LinkedList<String>();
		
		String[] input = {"K5UDF Lb BW 6NpORP4g m29k38qX", 
		"5om4qIcWVCyx9xioO0b VnOzphC3as", 
		"Uhr rKi 59Ja 1G 0Y9BNyH v m1e",
		"EEn OR4tcuw86l2KHAJw2EK2BbLtJk",
		"9N caocU9IdVQQH Z0A3QHwD8nOpQ0"};		
		
		for (String s: input) {
			res.add(s);
		}
		
		return res;
	}
	
}
