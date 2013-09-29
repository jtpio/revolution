package com.speed.run.tweens;

import aurelienribon.tweenengine.TweenAccessor;

import com.speed.run.screens.BaseScreen;

public class BaseScreenAccessor implements TweenAccessor<BaseScreen> {

	public static final int ALPHA = 0;

	@Override
	public int getValues(BaseScreen target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case ALPHA:
			returnValues[0] = target.getAlpha();
			return 1;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(BaseScreen target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case ALPHA:
			target.setAlpha(newValues[0]);
			break;
		default:
			assert false;
			break;
		}

	}


}
