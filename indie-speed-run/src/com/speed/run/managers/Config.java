package com.speed.run.managers;

public class Config {

	// resolution
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 600;
	
	public static final float INIT_SPEED = 5;
	public static final float DIST_EPSI = 10;
	public static final float NPC_Y_POS = -120;
	
	// NPC
	public static final float SPAWN_INTERVAL = 1; // in seconds
	public static final float REMOVE_INTERVAL = 60; // in seconds
	public static final float SPAWN_NB = 1;
	public static final float MAX_NB = 5;
	public static final float X_SPAWN = 0;
	
	// bubbles
	public static final float BUBBLE_Y_OFFSET = 120;
	public static final float SENTENCE_DURATION = 3; // s
	public static final float MAX_SCALE = 2f;
	public static final float MAX_DISTANCE = (float) Math.pow(500, 1);
	public static final float DEFAULT_FONT_SCALE = 1.0f;
	public static final float MIN_FONT_SCALE = 0.3f;
}
