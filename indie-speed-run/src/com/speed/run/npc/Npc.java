package com.speed.run.npc;

import com.speed.run.engine.MoveableEntity;
import com.speed.run.managers.Assets;

public class Npc extends MoveableEntity {

	protected String name;
	
	public Npc(String name) {
		super();
		animations.put("walkRight", Assets.getInstance().getAnimation(name+"WalkRight"));
		animations.put("idleRight", Assets.getInstance().getAnimation(name+"IdleRight"));
		setAnimation("idleRight");
	}

	public void kill() {
		
	}

	@Override
	public void moveTo(float x, float y) {
		left = x < pos.x;
		setAnimation("walkRight");
		super.moveTo(x, y);
	}
	
	
}
