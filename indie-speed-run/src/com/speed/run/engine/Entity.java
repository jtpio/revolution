package com.speed.run.engine;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Entity implements Drawable {
	protected Vector2 pos;
	protected Animation animation;
	protected float stateTime;
	protected Vector2 animSize;
	
	public Entity() {
		Init();
	}
	
	public Entity(Animation anim) {
		Init();
		setAnimation(anim);
	}
	
	private void Init() {
		pos = new Vector2();
		animSize = new Vector2();
		stateTime = 0;
	}
	
	public void setAnimation(Animation anim) {
		animation = anim;
		animSize.set(animation.getKeyFrame(0).getRegionWidth(), animation.getKeyFrame(0).getRegionHeight());
	}
	
	public void scale(float factor) {
		animSize.x = factor * animation.getKeyFrame(0).getRegionWidth();
		animSize.y = factor * animation.getKeyFrame(0).getRegionHeight();
	}
	
	public void update(float dt) {
		stateTime += dt;
	}
	
	@Override
	public void draw(SpriteBatch batch) {
		TextureRegion tr = animation.getKeyFrame(stateTime);
		batch.draw(tr, pos.x - animSize.x / 2, pos.y - animSize.y / 2, animSize.x, animSize.y);
	}

	public Vector2 getPosition() {
		return pos;
	}

	public void setPosition(Vector2 pos) {
		this.pos = pos;
	}

	public void setPosition(float x, float y) {
		pos.set(x, y);
	}
}

