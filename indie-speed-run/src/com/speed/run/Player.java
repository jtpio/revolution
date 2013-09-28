package com.speed.run;

import com.speed.run.engine.Fixed;
import com.speed.run.engine.MoveableEntity;
import com.speed.run.managers.Config;

public class Player extends MoveableEntity {
	private static Player instance = null;
	private Player() {
		super();
		setFixed(Fixed.Y);
		setPosition(0, Config.NPC_Y_POS);
		moveTo(pos.x, pos.y);
	}
	
	public static Player getInstance() {
		if (instance == null) instance = new Player();
		return instance;
	}
	
}
