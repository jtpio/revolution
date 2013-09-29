package com.speed.run.managers;

import java.util.ArrayList;
import java.util.LinkedList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.speed.run.dialog.Sentence;
import com.speed.run.npc.Pair;

public class Dialogs {
	
	// singleton stuff
	private static Dialogs instance = null;
	
	public static Dialogs getInstance() {
		if (instance == null) instance = new Dialogs();
		return instance;
	}
	
	public static final String[] jsonFiles = {
		"data/dialogs/bros.json",
		"data/dialogs/homeless.json"
	};
	private ArrayList<Pair> pairs;
	
	private Dialogs() {
		pairs = new ArrayList<Pair>();
		for (String s: jsonFiles) loadDialogs(s);
	}
	
	public ArrayList<Pair> getPairs() {
		return pairs;
	}

	public void setPairs(ArrayList<Pair> pairs) {
		this.pairs = pairs;
	}

	public void loadDialogs(String jsonFile) {
		JsonReader jsonReader = new JsonReader();
		JsonValue root = jsonReader.parse(Gdx.files.internal(jsonFile));
		int n = root.getInt("n");
		LinkedList<Sentence> firstList = new LinkedList<Sentence>();
		LinkedList<Sentence> secondList = null;
		String firstName = root.getString("first");
		String secondName = null;
		if (n == 2) {
			secondList = new LinkedList<Sentence>();
			secondName = root.getString("second");
		}
		JsonValue dialogs = root.get("dialogs");
		for (int i = 0; i < dialogs.size; i++) {
			JsonValue v = dialogs.get(i);
			Sentence s = new Sentence(i * (Config.SENTENCE_DURATION + Config.PAUSE), Config.SENTENCE_DURATION, v.getString(1));
			
			if (v.getInt(0) == 0) firstList.add(s);
			else secondList.add(s);
		}
		
		pairs.add(new Pair(firstList, secondList, firstName, secondName));
	}
	
}
