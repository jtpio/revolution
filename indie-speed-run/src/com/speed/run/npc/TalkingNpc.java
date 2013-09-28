package com.speed.run.npc;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.speed.run.dialog.Sentence;
import com.speed.run.dialog.SentenceFactory;
import com.speed.run.engine.SpeechBubble;
import com.speed.run.managers.Assets;
import com.speed.run.managers.Config;

public class TalkingNpc extends Npc {

	protected SpeechBubble speechBubble;
	protected LinkedList<Sentence> sentences;
	protected int currentSentence;
	protected float time;
	
	public TalkingNpc() {
		super();
		init();
	}

	private void init() {
		animations.put("idleRight", Assets.getInstance().getAnimation("npc0"));
		setAnimation("idleRight");
		speechBubble = new SpeechBubble();
		sentences = SentenceFactory.randomSentences();
		currentSentence = 0;
		time = 0;
		speechBubble.setText(sentences.getFirst().getText());
	}
	
	@Override
	public void kill() {
		speechBubble = null;
		sentences = null;
		super.kill();
	}

	@Override
	public void update(float dt) {
		super.update(dt);
		time += dt;
		
		if (sentences.size() == 0) return;
		
		if (time > sentences.getFirst().getStartTime()) {
			speechBubble.setText(sentences.getFirst().getText());
		}
		
		if (time > sentences.getFirst().getStartTime() + sentences.getFirst().getDuration() + Config.PAUSE) {
			speechBubble.hide();
			sentences.removeFirst();
		}
		
		speechBubble.setPosition(pos.x, pos.y + Config.BUBBLE_Y_OFFSET);
		speechBubble.update(dt);
		
	}

	@Override
	public void draw(SpriteBatch batch) {
		super.draw(batch);
		speechBubble.draw(batch);
	}

}
