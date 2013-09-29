package com.speed.run.tweens;

import aurelienribon.tweenengine.TweenAccessor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class BitMapFontAccessor implements TweenAccessor<BitmapFont> {

	public static final int ALPHA = 3;

	@Override
	public int getValues(BitmapFont target, int tweenType, float[] returnValues) {
		switch (tweenType) {
		case ALPHA:
			returnValues[0] = target.getColor().a;
			return 1;
		default:
			assert false;
			return -1;
		}
	}

	@Override
	public void setValues(BitmapFont target, int tweenType, float[] newValues) {
		switch (tweenType) {
		case ALPHA:
			Color c  = target.getColor();
			target.setColor(c.r, c.g, c.b, newValues[0]);
			break;
		default:
			assert false;
			break;
		}

	}


}
