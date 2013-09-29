package com.speed.run.items;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Quad;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.speed.run.IndieSpeedRun;
import com.speed.run.engine.Entity;
import com.speed.run.managers.Assets;
import com.speed.run.tweens.BitMapFontAccessor;

public class BusSign extends Entity {

	protected BitmapFont font;
	protected String text;
	
	public BusSign() {
		super();
		init();
	}
	
	public void init() {
		animations.put("idle", Assets.getInstance().getAnimation("bussign"));
		animation = animations.get("idle");
		setAnimation("idle");
		font = Assets.getInstance().getFont("normal");
		text = "";
	}

	public void setWaitingTime(final int waitingTime) {
		final int newTime = waitingTime;
		Tween.to(font, BitMapFontAccessor.ALPHA, 0.5f)
			    .target(0f)
			    .ease(Quad.IN)
			    .setCallback(new TweenCallback() {
						
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						text = String.valueOf(newTime);
						Tween.to(font, BitMapFontAccessor.ALPHA, 0.5f)
					    .target(1.0f)
					    .ease(Quad.IN)
					    .start(IndieSpeedRun.tweenManager);
					}
				})
		.start(IndieSpeedRun.tweenManager);

	}

	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		font.draw(batch, text, pos.x - font.getBounds(text).width / 2, pos.y + 77.0f);
	}
	
	
	
}
