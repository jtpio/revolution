package com.speed.run.engine;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.speed.run.Player;
import com.speed.run.managers.Assets;
import com.speed.run.managers.Config;

public class SpeechBubble extends Entity {
	
	protected BitmapFont font;
	protected String text;
	protected String displayedText;
	protected float padding = 0.8f;
	protected float time = 0;
	protected static String far = "...";
	protected float factor;
	
	protected boolean display;
	
	public SpeechBubble() {
		super();
		init();
	}

	public void init() {
		animations.put("bubble", Assets.getInstance().getAnimation("bubble"));
		animation = animations.get("bubble");
		font = Assets.getInstance().getFont("bubble");
		display = false;
		factor = 0;
	}
	
	public void setText(String newText) {
		text = newText;
		display = true;
	}
	
	public void hide() {
		time = 0;
		displayedText = "";
		display = false;
	}

	public void update(float dt) {
		time += dt;
		int lastIndex = (int) Math.min((text.length() * (time / Config.SENTENCE_DURATION)), text.length());
		displayedText = text.substring(0, lastIndex);
		float dist = Player.getInstance().pos.dst(pos);
		float scaleFactor = (-Math.signum(dist) * Config.MAX_SCALE / Config.MAX_DISTANCE) * dist + Config.MAX_SCALE;
		scaleFactor = MathUtils.clamp(scaleFactor, Config.MIN_FONT_SCALE, Config.DEFAULT_FONT_SCALE);
		scale(scaleFactor);
	}
	
	@Override
	public void scale(float f) {
		factor = f;
		if (factor <= Config.MIN_FONT_SCALE) displayedText = far;
		super.scale(factor);
	}

	@Override
	public void draw(SpriteBatch batch) {
		if (!display) return;
		super.draw(batch);
		font.setScale(factor);
		font.drawWrapped(batch, displayedText, pos.x - (animSize.x * padding) / 2, pos.y
				+ (animSize.y * padding) / 2, animSize.x * padding, (displayedText.equals(far))? HAlignment.CENTER: HAlignment.LEFT);
		font.setScale(Config.DEFAULT_FONT_SCALE);
	}
}