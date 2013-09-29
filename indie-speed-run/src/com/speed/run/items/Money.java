package com.speed.run.items;

import com.speed.run.engine.MoveableEntity;
import com.speed.run.managers.Assets;

public class Money extends MoveableEntity {
	
	public Money() {
		super();
		animations.put("idle", Assets.getInstance().getAnimation("cashIcon"));
		setAnimation("idle");
	}
	
	
}
