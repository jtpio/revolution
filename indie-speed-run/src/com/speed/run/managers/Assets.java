package com.speed.run.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public class Assets {

	private static final String TAG = "Assets";
	
	// singleton stuff
	private static Assets instance = null;
	private Assets() {}
	
	public static Assets getInstance() {
		if (instance == null) instance = new Assets();
		return instance;
	}
	
	HashMap<String, Sprite> sprites = new HashMap<String, Sprite>();
	HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	HashMap<String, Music> musics = new HashMap<String, Music>();
	
	public void loadAssets(AssetManager assetManager) {
		// sprite
		TextureAtlas atlas = assetManager.get("data/spritesheet.txt", TextureAtlas.class);
		Sprite potatoSprite = atlas.createSprite("potato");
		sprites.put("potato", potatoSprite);
		
		// sounds
		
		
		// musics
	}
	
	public Sprite getSprite(String spriteName) {
		if (sprites.containsKey(spriteName)) {
			return sprites.get(spriteName);
		} else {
			Gdx.app.error(TAG, "Sprite " + spriteName + " does not exist!");
			return null;
		}
	}
}
