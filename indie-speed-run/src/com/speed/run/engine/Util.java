package com.speed.run.engine;

public class Util {
	
	public static int randomSign() {
		return (int) Math.signum(Math.random() - 0.5f);
	}
	
	public static float random(float min, float max) {
		return (float)(Math.random() * (max-min) + min);
	}
}
