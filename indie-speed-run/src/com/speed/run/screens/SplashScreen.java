package com.speed.run.screens;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.speed.run.IndieSpeedRun;
import com.speed.run.managers.Assets;

public class SplashScreen extends BaseScreen {
	
	private AssetManager assetManager = new AssetManager();

	public SplashScreen(IndieSpeedRun game) {
		super(game);
		assetManager.load("data/spritesheet.txt", TextureAtlas.class);
	}

	@Override
	public void render(float delta) {
		
		if (assetManager.update()) {
			Assets.getInstance().loadAssets(assetManager);
			game.createScreens();
			game.setScreen(game.getHomeScreen());
		}
		
		super.render(delta);
	}

	@Override
	public void dispose() {
		assetManager.dispose();
		super.dispose();
	}

	
	
}
