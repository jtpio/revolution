package com.speed.run.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	
	HashMap<String, Animation> animations = new HashMap<String, Animation>();
	HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	HashMap<String, Music> musics = new HashMap<String, Music>();
	HashMap<String, BitmapFont> fonts = new HashMap<String, BitmapFont>();
	
	public void loadAssets(AssetManager assetManager) {
		// fonts
		fonts.put("normal", assetManager.get("data/font/silkscreen.fnt", BitmapFont.class));
		fonts.put("bubble", assetManager.get("data/font/silkBubble.fnt", BitmapFont.class));
		// sprites
		TextureAtlas atlas = assetManager.get("data/spritesheet.txt", TextureAtlas.class);

		// player
		animations.put("player", new Animation(0.5f, atlas.findRegions("player")));
		animations.put("npc0", new Animation(0.5f, atlas.findRegions("npc0")));
		animations.put("bubble", new Animation(0.5f, atlas.findRegions("bubble")));
		// background
		animations.put("bg", new Animation(0.5f, atlas.findRegions("bg")));
		
		// sounds
		
		
		// musics
	}
	
	public Animation getAnimation(String animName) {
		if (animations.containsKey(animName)) {
			return animations.get(animName);
		} else {
			Gdx.app.error(TAG, "Animation " + animName + " does not exist!");
			return null;
		}
	}
	
	public BitmapFont getFont(String fontName) {
		if (fonts.containsKey(fontName)) {
			return fonts.get(fontName);
		} else {
			Gdx.app.error(TAG, "Font " + fontName + " does not exist!");
			return null;
		}
	}
	
	public static class Path {
		public static final String SPRITESHEET = "data/spritesheet.txt";
		public static final String FONT_NORMAL = "data/font/silkscreen.fnt";
		public static final String FONT_BUBBLE = "data/font/silkBubble.fnt";
	}
}
