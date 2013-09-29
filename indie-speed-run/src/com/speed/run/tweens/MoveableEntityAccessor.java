package com.speed.run.tweens;

import aurelienribon.tweenengine.TweenAccessor;

import com.speed.run.engine.MoveableEntity;

public class MoveableEntityAccessor implements TweenAccessor<MoveableEntity> {

	public static final int POSITION_X = 0;
	public static final int POSITION_XY = 1;

	@Override
	public int getValues(MoveableEntity target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case POSITION_X:
			returnValues[0] = target.getPosition().x;
			return 1;
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
	public void setValues(MoveableEntity target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case POSITION_X:
			target.setPosition(newValues[0], newValues[1]);
			break;
		case POSITION_XY:
			target.setPosition(newValues[0], newValues[1]);
		default:
			assert false;
			break;
		}

	}


}
