package com.speed.run.engine;

import java.util.HashMap;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Entity implements Drawable, Comparable<Entity> {
	protected Vector2 pos;
	protected Animation animation;
	protected HashMap<String, Animation> animations;
	protected float stateTime;
	protected Vector2 animSize;
	protected boolean left;
	protected int depth;
	
	public Entity() {
		Init();
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int f) {
		this.depth = f;
	}

	private void Init() {
		pos = new Vector2();
		animSize = new Vector2();
		animations = new HashMap<String, Animation>();
		stateTime = 0;
		left = false;
	}
	
	public void setAnimation(String animName) {
		if (!animations.containsKey(animName)) return;
		animation = animations.get(animName);
		animSize.set(animation.getKeyFrame(0).getRegionWidth(), animation.getKeyFrame(0).getRegionHeight());
	}
	
	public void addAnimation(String animName, Animation a) {
		animations.put(animName, a);
		if (animation == null) setAnimation(animName);
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

	@Override
	public int compareTo(Entity o) {
		if (depth < o.depth) return -1;
		else if (depth > o.depth) return 1;
		return 0;
	}
}

