package com.speed.run;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.speed.run.engine.Fixed;
import com.speed.run.engine.MoveableEntity;
import com.speed.run.managers.Assets;
import com.speed.run.managers.Config;

public class Player extends MoveableEntity {
	private static Player instance = null;
	
	public static Player getInstance() {
		if (instance == null) instance = new Player();
		return instance;
	}
	
	private Player() {
		super();
		animations.put("idleLeft", Assets.getInstance().getAnimation("playerIdleLeft"));
		animations.put("idleRight", Assets.getInstance().getAnimation("playerIdleRight"));
		animations.put("playerWalkLeft", Assets.getInstance().getAnimation("playerWalkLeft"));
		animations.put("playerWalkRight", Assets.getInstance().getAnimation("playerWalkRight"));
		setFixed(Fixed.Y);
		setPosition(0, Config.NPC_Y_POS);
		moveTo(pos.x, pos.y);
	}
	
	@Override
	public void moveTo(float x, float y) {
		left = x < pos.x;
		if (left) setAnimation("playerWalkLeft");
		else setAnimation("playerWalkRight");
		super.moveTo(x, y);
	}

	@Override
	public void update(float dt) {
		super.update(dt);
	}
	
	public void setMusicMode(boolean mode) {
		
	}
}
