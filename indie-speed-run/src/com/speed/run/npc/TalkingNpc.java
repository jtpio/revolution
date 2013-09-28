package com.speed.run.npc;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.speed.run.engine.SpeechBubble;
import com.speed.run.managers.Assets;
import com.speed.run.managers.Config;
import com.speed.run.managers.SentenceFactory;

public class TalkingNpc extends Npc {

	protected SpeechBubble speechBubble;
	protected LinkedList<String> sentences;
	
	public TalkingNpc() {
		super();
		init();
	}

	public TalkingNpc(Animation anim) {
		super(anim);
		init();
	}
	
	private void init() {
		speechBubble = new SpeechBubble(Assets.getInstance().getAnimation("bubble"));
		sentences = SentenceFactory.randomSentences();
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		speechBubble.setPosition(pos.x, pos.y + Config.BUBBLE_Y_OFFSET);
	}

	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		speechBubble.draw(batch);
	}
	
	
}
