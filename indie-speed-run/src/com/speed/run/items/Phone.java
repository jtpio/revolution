package com.speed.run.items;

import com.speed.run.managers.Assets;
import com.speed.run.managers.Config;

public class Phone extends Item {

	private static Phone instance = null;
	private Phone() {
		super();
		setPosition(Config.PHONE_X, Config.PHONE_Y_INIT);
		animations.put("switchedOff", Assets.getInstance().getAnimation("phone"));
		animations.put("idle", Assets.getInstance().getAnimation("phoneDisplay"));
		setAnimation("switchedOff");
		setDepth(190);
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
		setAnimation("idle");
	}
	
	public void close() {
		setAnimation("switchedOff");
	}
}
