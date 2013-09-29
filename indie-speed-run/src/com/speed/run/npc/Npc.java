package com.speed.run.npc;

import com.speed.run.engine.MoveableEntity;
import com.speed.run.managers.Assets;

public class Npc extends MoveableEntity {

	public Npc() {
		super();
		animations.put("idleRight", Assets.getInstance().getAnimation("npc0"));
		setAnimation("idleRight");
	}

	public void kill() {
		
	}
}
