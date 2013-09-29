package com.speed.run.tweens;

import aurelienribon.tweenengine.TweenAccessor;

import com.speed.run.engine.SpeechBubble;

public class SpeechBubbleAccessor implements TweenAccessor<SpeechBubble> {

	public static final int SCALE = 0;

	@Override
	public int getValues(SpeechBubble target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case SCALE:
			returnValues[0] = target.getFactor();
			return 1;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(SpeechBubble target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case SCALE:
			target.setFactor(newValues[0]);
			break;
		default:
			assert false;
			break;
		}

	}

}
