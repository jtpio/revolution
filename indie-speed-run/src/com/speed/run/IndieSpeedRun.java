package com.speed.run;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.speed.run.screens.HomeScreen;
import com.speed.run.screens.SplashScreen;

public class IndieSpeedRun extends Game {
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 720;
	
	// screens
	private HomeScreen homeScreen;
	
	private SpriteBatch batch;
	
	public void createScreens() {
		if (homeScreen == null) homeScreen = new HomeScreen(this);
	}
	
	@Override
	public void create() {
		batch = new SpriteBatch();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	
	public SpriteBatch getSpriteBatch() {
		return batch;
	}
	
	public Screen getHomeScreen() {
		return homeScreen;
	}
}

