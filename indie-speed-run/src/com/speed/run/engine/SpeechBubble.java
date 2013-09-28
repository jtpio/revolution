package com.speed.run.engine;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.HAlignment;
import com.badlogic.gdx.math.MathUtils;
import com.speed.run.Player;
import com.speed.run.managers.Assets;
import com.speed.run.managers.Config;

public class SpeechBubble extends Entity {
	
	protected BitmapFont font;
	protected String text;
	protected String displayedText;
	protected boolean display;
	protected float padding = 0.8f;
	protected float time = 0;
	protected static String far = "...";
	protected float factor;
	
	public SpeechBubble() {
		super();
		init();
	}

	public SpeechBubble(Animation anim) {
		super(anim);
		init();
	}

	public void init() {
		animation = Assets.getInstance().getAnimation("bubble");
		font = Assets.getInstance().getFont("bubble");
		display = true;
		setText("I'm a stupid NPC. Where am I? What am I doing?");
		factor = 0;
	}
	
	public void setText(String newText) {
		text = newText;
		time = 0;
		displayedText = "";
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
		super.draw(batch);
		font.setScale(factor);
		font.drawWrapped(batch, displayedText, pos.x - (animSize.x * padding) / 2, pos.y
				+ (animSize.y * padding) / 2, animSize.x * padding, (displayedText.equals(far))? HAlignment.CENTER: HAlignment.LEFT);
		font.setScale(Config.DEFAULT_FONT_SCALE);
	}
}