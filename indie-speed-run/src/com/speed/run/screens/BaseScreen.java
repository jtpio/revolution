package com.speed.run.screens;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.speed.run.IndieSpeedRun;

public class BaseScreen implements Screen {

	protected IndieSpeedRun game;
	protected SpriteBatch batch;
	protected OrthographicCamera camera;
	
	public BaseScreen(IndieSpeedRun game) {
		this.game = game;
		this.batch = game.getSpriteBatch();
		this.camera = new OrthographicCamera();
	}
	
	@Override
	public void render(float delta) {
		
	}

	@Override
	public void resize(int width, int height) {
		
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public void pause() {
		
	}

	@Override
	public void resume() {
		
	}

	@Override
	public void dispose() {
		
	}

}
