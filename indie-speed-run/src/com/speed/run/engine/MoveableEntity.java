package com.speed.run.engine;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Quad;

import com.badlogic.gdx.math.Vector2;
import com.speed.run.IndieSpeedRun;
import com.speed.run.items.Phone;
import com.speed.run.managers.Config;
import com.speed.run.tweens.MoveableEntityAccessor;
import com.speed.run.tweens.PhoneAcessor;

public class MoveableEntity extends Entity implements OnMoveComplete {

	protected Vector2 target;
	protected float speed;
	protected Vector2 delta;
	protected Fixed fixedAxis;
	
	public MoveableEntity() {
		super();
		init();
	}
	
	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
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
		
	/*	Tween.to(this, MoveableEntityAccessor.POSITION_XY, 1.0f)
	    .target(newX, newY)
	    .ease(Quad.INOUT)
	    .setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				setIdleAnimation();
			}
		})
	    .start(IndieSpeedRun.tweenManager);*/
		
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
		if (left && animations.containsKey("idleLeft")) setAnimation("idleLeft");
		else if (animations.containsKey("idleRight")) setAnimation("idleRight");
		else if (animations.containsKey("idle")) setAnimation("idle");
	}
}
