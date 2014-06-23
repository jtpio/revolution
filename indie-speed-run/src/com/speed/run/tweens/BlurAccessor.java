package com.speed.run.tweens;

import aurelienribon.tweenengine.TweenAccessor;

import com.speed.run.screens.Blur;

public class BlurAccessor implements TweenAccessor<Blur> {

	public static final int BLUR_AMOUNT = 0;

	@Override
	public int getValues(Blur target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case BLUR_AMOUNT:
			returnValues[0] = target.getAmount();
			return 1;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(Blur target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case BLUR_AMOUNT:
			target.setAmount(newValues[0]);
			break;
		default:
			assert false;
			break;
		}

	}


}
