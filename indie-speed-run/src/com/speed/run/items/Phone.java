package com.speed.run.items;

import com.speed.run.managers.Assets;
import com.speed.run.managers.Config;

public class Phone extends Item {

	private static Phone instance = null;
	private Phone() {
		setPosition(Config.PHONE_X, Config.PHONE_Y_INIT);
		moveTo(pos.x, pos.y);
		speed = Config.PHONE_SPEED;
		animations.put("idle", Assets.getInstance().getAnimation("bubble"));
		setAnimation("idle");
	}
	
	public static Phone getInstance() {
		if (instance == null) instance = new Phone();
		return instance;
	}
	
	public void toggle(boolean enabled) {
		if (enabled) open();
		else close();
	}
	
	public void open() {
		moveTo(Config.PHONE_X, Config.PHONE_Y_END);
	}
	
	public void close() {
		moveTo(Config.PHONE_X, Config.PHONE_Y_INIT);
		System.out.println("New target for " + getClass() + ": " + target);
	}
}
