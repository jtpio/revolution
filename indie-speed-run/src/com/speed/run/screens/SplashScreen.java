package com.speed.run.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.speed.run.IndieSpeedRun;
import com.speed.run.managers.Assets;

public class SplashScreen extends BaseScreen {
	
	private AssetManager assetManager = new AssetManager();

	public SplashScreen(IndieSpeedRun game) {
		super(game);
		assetManager.load(Assets.Path.SPRITESHEET, TextureAtlas.class);
		assetManager.load(Assets.Path.SPRITESHEET2, TextureAtlas.class);
		assetManager.load(Assets.Path.SPRITESHEET3, TextureAtlas.class);
		assetManager.load(Assets.Path.FONT_NORMAL, BitmapFont.class);
		assetManager.load(Assets.Path.FONT_BUBBLE, BitmapFont.class);
		assetManager.load(Assets.Path.MUSIC_THEME, Music.class);
		assetManager.load(Assets.Path.MUSIC_WALKMAN, Music.class);
		assetManager.load(Assets.Path.MUSIC_THEME_UPBEAT, Music.class);
		assetManager.load(Assets.Path.MUSIC_GAMEOVER, Music.class);
		assetManager.load(Assets.Path.SOUND_PHONE, Sound.class);
		assetManager.load(Assets.Path.SOUND_BUS, Sound.class);
		assetManager.load(Assets.Path.SOUND_ITEM_CLICK, Sound.class);
	}

	@Override
	public void render(float delta) {
		
		if (assetManager.update()) {
			Assets.getInstance().loadAssets(assetManager);
			game.createScreens();
			game.setScreen(game.getBusStopScreen());
		}
		
		super.render(delta);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
		super.dispose();
	}

	
	
}

