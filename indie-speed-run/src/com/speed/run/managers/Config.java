package com.speed.run.managers;


public class Config {

	// resolution
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 600;
	
	public static final float INIT_SPEED = 2.5f;
	public static final float MAX_SPEED = 5.5f;
	public static final float DIST_EPSI = 10;
	public static final float NPC_Y_POS = -120;
	
	// animations
	public static final float WALKING_SPEED = 0.05f;
	public static final float PHONE_BLINK = 1.0f;
	
	// NPC
	public static final float FIRST_SPAWN = 3.0f; // in seconds
	public static final float SPAWN_INTERVAL = 15.0f; // in seconds
	public static final float SPAWN_NB = 1;
	public static final float MAX_NB = 8; // per level
	public static final float MAX_RANDOM_GUYS = 6;
	public static final float X_SPAWN = 1000;
	public static final float DISTANCE_DIALOG = 50;
	public static final float DEPTH_INCR = 12;
	public static final int START_DEPTH = 127;
	public static final int NB_LAYERS = 3;
	public static final int DELAY_TALK = 10; // s
	
	// bubbles
	public static final float BUBBLE_Y_OFFSET = 120;
	public static final float SENTENCE_DURATION = 1.5f; // s
	public static final float MAX_SCALE = 4f;
	public static final float MAX_DISTANCE = 200;
	public static final float DEFAULT_FONT_SCALE = 1.0f;
	public static final float MIN_FONT_SCALE = 0.3f;
	public static final float PAUSE = 1.0f;
	
	// inventory
	public static final int ITEM_PADDING = 10;
	public static final int ICON_SIZE = 64;
	
	// phone
	public static final float PHONE_SPEED = 10.0f;
	public static final float PHONE_X = -450.0f;
	public static final float PHONE_Y_INIT = -500.0f;
	public static final float PHONE_Y_END = -150.0f;
	
	// Bus
	public static final float MIN_TIME_BUS_COMES = 80.0f; // 60s
	public static final float MAX_TIME_BUS_COMES = 120.0f; // 120s
	public static final float WAITING_TIME_STOP = 5; // s
	public static final float INIT_POS_X_BUS = 1500;
	public static final float END_POS_X_BUS = -1500;
	public static final float POS_Y_BUS = -150;
	public static final float BUS_TRIP_LANDING = 7.5f;
	public static final float BUS_TRIP_LEAVING = 3.5f;
	
	// Panel
	public static final float PANEL_X = -250;
	public static final float PANEL_Y = -55;
	
	public static final int BUS_NBR = 3;
	
	// Sound
	public static final float HALF_VOLUME = 0.5f;
	
	public static final float CAMERA_MOVING = 10.0f;
}
