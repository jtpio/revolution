package com.speed.run.npc;

import java.util.LinkedList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.speed.run.dialog.Sentence;
import com.speed.run.engine.Renderer;
import com.speed.run.engine.SpeechBubble;
import com.speed.run.managers.Config;

public class TalkingNpc extends Npc {

	protected SpeechBubble speechBubble;
	protected LinkedList<Sentence> sentences;
	protected int currentSentence;
	protected float time;
	
	public TalkingNpc(String name, LinkedList<Sentence> sentences) {
		super(name);
		this.sentences = sentences;
		init();
	}

	private void init() {
		speechBubble = new SpeechBubble();
		speechBubble.setDepth(depth);
		Renderer.getInstance().addEntity(speechBubble);
		time = 0;
	}
	
	@Override
	public void kill() {
		speechBubble = null;
		sentences = null;
		super.kill();
	}
	
	public LinkedList<Sentence> getSentences() {
		return sentences;
	}

	public void setSentences(LinkedList<Sentence> sentences) {
		this.sentences = sentences;
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
