package com.speed.run.npc;

import java.util.LinkedList;

import com.speed.run.dialog.Sentence;

public class Pair {
	
	private String firstName;
	private String secondName;
	private LinkedList<Sentence> first;
	private LinkedList<Sentence> second;
	
	public Pair(LinkedList<Sentence> f, LinkedList<Sentence> s, String fn, String sn) {
		first = f;
		second = s;
		firstName = fn;
		secondName = sn;
	}

	public LinkedList<Sentence> getFirst() {
		return first;
	}

	public LinkedList<Sentence> getSecond() {
		return second;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getSecondName() {
		return secondName;
	}

}
