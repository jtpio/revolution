package com.speed.run.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.speed.run.IndieSpeedRun;
import com.speed.run.managers.Assets;

public class HomeScreen extends BaseScreen {

	private Sprite sprite;
	private boolean fullscreen = false;
	
	public HomeScreen(IndieSpeedRun game) {
		super(game);
		sprite = Assets.getInstance().getSprite("potato");
		sprite.setPosition(0, 0);
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0.5f, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		if (Gdx.input.justTouched()) {
			if (fullscreen) {
				Gdx.graphics.setDisplayMode(480, 320, false);
				fullscreen = false;
			} else {
				DisplayMode desktopDisplayMode = Gdx.graphics
						.getDesktopDisplayMode();
				Gdx.graphics.setDisplayMode(desktopDisplayMode.width,
						desktopDisplayMode.height, true);
				fullscreen = true;
			}
		}

		batch.begin();
		sprite.draw(batch);
		batch.end();
		super.render(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		super.resize(width, height);
	}

	@Override
	public void show() {
		super.show();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
	
}
