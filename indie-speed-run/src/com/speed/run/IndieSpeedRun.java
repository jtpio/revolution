package com.speed.run;

import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.speed.run.engine.MoveableEntity;
import com.speed.run.items.Phone;
import com.speed.run.screens.BaseScreen;
import com.speed.run.screens.Blur;
import com.speed.run.screens.BusStopScreen;
import com.speed.run.screens.GameOverScreen;
import com.speed.run.screens.HomeScreen;
import com.speed.run.screens.InBusScreen;
import com.speed.run.screens.SplashScreen;
import com.speed.run.tweens.BaseScreenAccessor;
import com.speed.run.tweens.BitMapFontAccessor;
import com.speed.run.tweens.BlurAccessor;
import com.speed.run.tweens.CameraAccessor;
import com.speed.run.tweens.MoveableEntityAccessor;
import com.speed.run.tweens.PhoneAcessor;

public class IndieSpeedRun extends Game {
	
	// screens
	private HomeScreen homeScreen;
	private BusStopScreen busStopScreen;
	private InBusScreen inBusScreen;
	private GameOverScreen gameOverScreen;
	
	private SpriteBatch batch;
	public static TweenManager tweenManager = new TweenManager();;
	
	public static boolean win = false;
	public static String msg = "";
	
	public SpriteBatch getBatch() {
		return batch;
	}

	public void createScreens() {
		if (homeScreen == null) homeScreen = new HomeScreen(this);
		if (busStopScreen == null) busStopScreen = new BusStopScreen(this);
		if (inBusScreen == null) inBusScreen = new InBusScreen(this);
		if (gameOverScreen == null) gameOverScreen = new GameOverScreen(this);
	}
	
	public GameOverScreen getGameOverScreen() {
		return gameOverScreen;
	}

	public void setGameOverScreen(GameOverScreen gameOverScreen) {
		this.gameOverScreen = gameOverScreen;
	}

	@Override
	public void create() {
		// TWEENS
		Tween.registerAccessor(Phone.class, new PhoneAcessor());
		Tween.registerAccessor(MoveableEntity.class, new MoveableEntityAccessor());
		Tween.registerAccessor(BaseScreen.class, new BaseScreenAccessor());
		Tween.registerAccessor(BitmapFont.class, new BitMapFontAccessor());
		Tween.registerAccessor(Camera.class, new CameraAccessor());
		Tween.registerAccessor(Blur.class, new BlurAccessor());
		
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
	
	public Screen getBusStopScreen() {
		return busStopScreen;
	}

	public InBusScreen getInBusScreen() {
		return inBusScreen;
	}
	
	
}

