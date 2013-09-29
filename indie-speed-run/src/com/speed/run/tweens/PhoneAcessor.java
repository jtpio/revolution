package com.speed.run.tweens;

import com.speed.run.items.Phone;

import aurelienribon.tweenengine.TweenAccessor;

public class PhoneAcessor implements TweenAccessor<Phone> {

	public static final int POSITION_XY = 3;

	@Override
	public int getValues(Phone target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case POSITION_XY:
			returnValues[0] = target.getPosition().x;
			returnValues[1] = target.getPosition().y;
			return 2;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(Phone target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case POSITION_XY:
			target.setPosition(newValues[0], newValues[1]);
			break;
		default:
			assert false;
			break;
		}

	}

}
