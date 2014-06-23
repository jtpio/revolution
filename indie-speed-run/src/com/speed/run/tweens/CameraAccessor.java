package com.speed.run.tweens;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.Camera;

public class CameraAccessor implements TweenAccessor<Camera> {

	public static final int POSITION_Y = 0;

	@Override
	public int getValues(Camera target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case POSITION_Y:
			returnValues[0] = target.position.y;
			return 1;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(Camera target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case POSITION_Y:
			target.position.set(target.position.x, newValues[0], target.position.z);
			break;
		default:
			assert false;
			break;
		}

	}


}
