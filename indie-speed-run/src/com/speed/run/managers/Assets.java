package com.speed.run.managers;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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
	HashMap<String, Animation> animations = new HashMap<String, Animation>();
	HashMap<String, Sound> sounds = new HashMap<String, Sound>();
	HashMap<String, Music> musics = new HashMap<String, Music>();
	HashMap<String, BitmapFont> fonts = new HashMap<String, BitmapFont>();
	
	public void loadAssets(AssetManager assetManager) {
		// fonts
		fonts.put("normal", assetManager.get(Path.FONT_NORMAL, BitmapFont.class));
		fonts.put("bubble", assetManager.get(Path.FONT_BUBBLE, BitmapFont.class));

		// sprites
		TextureAtlas atlas = assetManager.get(Path.SPRITESHEET, TextureAtlas.class);
		
		// icons
		Sprite icon;
		// phone
		icon = atlas.createSprite("phoneIcon");
		icon.setOrigin(icon.getWidth()/2, icon.getHeight()/2);
		sprites.put("phoneIcon", icon);
		
		// ipod
		icon = atlas.createSprite("mp3Icon");
		icon.setOrigin(icon.getWidth()/2, icon.getHeight()/2);
		sprites.put("ipodIcon", icon);
		
		// cigarettes
		icon = atlas.createSprite("cigarettesIcon");
		icon.setOrigin(icon.getWidth()/2, icon.getHeight()/2);
		sprites.put("cigarettesIcon", icon);
		
		// cash
		icon = atlas.createSprite("cashIcon");
		icon.setOrigin(icon.getWidth()/2, icon.getHeight()/2);
		sprites.put("cashIcon", icon);

		// player
		animations.put("playerIdleLeft", new Animation(0.2f, atlas.findRegions("playerIdleLeft")));
		animations.put("playerIdleRight", new Animation(0.2f, atlas.findRegions("playerIdleRight")));
		animations.put("playerWalkLeft", new Animation(Config.WALKING_SPEED, atlas.findRegions("playerWalkLeft"), Animation.LOOP));
		animations.put("playerWalkRight", new Animation(Config.WALKING_SPEED, atlas.findRegions("playerWalkRight"), Animation.LOOP));
		animations.put("npc0", new Animation(0.5f, atlas.findRegions("npc0")));
		animations.put("bubble", new Animation(0.5f, atlas.findRegions("bubble")));
		
		// phone
		animations.put("phone", new Animation(0.2f, atlas.findRegions("phone"), Animation.LOOP));
		animations.put("phoneDisplay", new Animation(Config.PHONE_BLINK, atlas.findRegions("phoneDisplay"), Animation.LOOP));
		
		// background
		animations.put("bg", new Animation(0.5f, atlas.findRegions("bg")));
		
		// sounds
		
		
		// musics
		Music mainTheme = assetManager.get(Path.MUSIC_THEME, Music.class);
		mainTheme.setLooping(true);
		musics.put("mainTheme", mainTheme);
	}
	
	public Animation getAnimation(String animName) {
		if (animations.containsKey(animName)) {
			return animations.get(animName);
		} else {
			Gdx.app.error(TAG, "Animation " + animName + " does not exist!");
			return null;
		}
	}
	
	public Sprite getSprites(String spriteName) {
		if (sprites.containsKey(spriteName)) {
			return sprites.get(spriteName);
		} else {
			Gdx.app.error(TAG, "Sprite " + spriteName + " does not exist!");
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
	
	public Music getMusic(String musicName) {
		if (musics.containsKey(musicName)) {
			return musics.get(musicName);
		} else {
			Gdx.app.error(TAG, "Music " + musicName + " does not exist!");
			return null;
		}
	}
	
	public static class Path {
		public static final String SPRITESHEET = "data/spritesheet.txt";
		public static final String FONT_NORMAL = "data/font/silkscreen.fnt";
		public static final String FONT_BUBBLE = "data/font/silkBubble.fnt";
		public static final String MUSIC_THEME = "data/music/vantan_theme.mp3";
	}
}
