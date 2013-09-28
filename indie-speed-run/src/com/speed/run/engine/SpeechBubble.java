package com.speed.run.engine;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.speed.run.managers.Assets;

public class SpeechBubble extends Entity {
	
	protected BitmapFont font;
	protected String text;
	protected TextBounds bounds;
	protected boolean display;
	protected float padding = 0.8f;
	
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
		changeText("I'm a stupid NPC. Where am I? What am I doing?");
	}
	
	public void changeText(String newText) {
		text = newText;
		bounds = font.getBounds(text);
	}

	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		font.drawWrapped(batch, text, pos.x - (animSize.x * padding) / 2, pos.y
				+ (animSize.y * padding) / 2, animSize.x * padding);
	}
}
