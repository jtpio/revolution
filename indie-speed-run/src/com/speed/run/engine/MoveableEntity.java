package com.speed.run.engine;

import com.badlogic.gdx.math.Vector2;
import com.speed.run.managers.Config;

public class MoveableEntity extends Entity implements OnMoveComplete {

	protected Vector2 target;
	protected float speed;
	protected Vector2 delta;
	protected Fixed fixedAxis;
	
	public MoveableEntity() {
		super();
		init();
	}
	
	private void init() {
		target = new Vector2();
		delta = new Vector2();
		speed = Config.INIT_SPEED;
	}
	
	public void moveTo(float x, float y) {
		float newX = (fixedAxis == Fixed.X || fixedAxis == Fixed.XY) ? pos.x:x;
		float newY = (fixedAxis == Fixed.Y || fixedAxis == Fixed.XY) ? pos.y:y;
		target.set(newX, newY);
	}
	
	public void setFixed(Fixed fixedAxis) {
		this.fixedAxis = fixedAxis;
	}
	
	public void update(float dt) {
		super.update(dt);
		if (pos.dst2(target) > Config.DIST_EPSI) {			
			delta.set(target.x - pos.x, target.y - pos.y).nor();
			pos.add(delta.x * speed, delta.y * speed);
		} else {
			setIdleAnimation();
		}
	}

	@Override
	public void setIdleAnimation() {
		if (left) setAnimation("idleLeft");
		else setAnimation("idleRight");
	}
}
