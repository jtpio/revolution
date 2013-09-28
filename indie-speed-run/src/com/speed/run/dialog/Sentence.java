package com.speed.run.dialog;

public class Sentence {
	
	protected float startTime;
	protected float duration;
	protected String text;
	
	public Sentence() {
		super();
		init(0, 0, "");
	}

	public Sentence(float startTime, float sentenceDuration, String text) {
		super();
		init(startTime, sentenceDuration, text);
	}
	
	public void init(float startTime, float duration, String text) {
		this.startTime = startTime;
		this.duration = duration;
		this.text = text;
	}

	public float getStartTime() {
		return startTime;
	}

	public void setStartTime(float startTime) {
		this.startTime = startTime;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
