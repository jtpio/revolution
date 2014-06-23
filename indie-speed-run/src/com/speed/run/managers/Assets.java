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
		TextureAtlas atlas2 = assetManager.get(Path.SPRITESHEET2, TextureAtlas.class);
		TextureAtlas atlas3 = assetManager.get(Path.SPRITESHEET3, TextureAtlas.class);
		
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
		
		// cash
		icon = atlas.createSprite("ticketIcon");
		icon.setOrigin(icon.getWidth()/2, icon.getHeight()/2);
		sprites.put("ticketIcon", icon);

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
		animations.put("bg", new Animation(0.5f, atlas2.findRegions("bg")));
		
		// background
		animations.put("finish", new Animation(0.5f, atlas3.findRegions("finish")));
		animations.put("gameover", new Animation(0.5f, atlas3.findRegions("gameover")));
		
		// bus background
		animations.put("busbg", new Animation(0.5f, atlas2.findRegions("busbg")));
		
		// busstop
		animations.put("busstop", new Animation(0.5f, atlas.findRegions("busstop")));
		
		// bussign
		animations.put("bussign", new Animation(0.5f, atlas.findRegions("bussign")));
		
		// bus
		animations.put("bus", new Animation(0.5f, atlas.findRegions("bus")));
		
		// cash
		animations.put("cashIcon", new Animation(0.5f, atlas.findRegions("cashIcon")));
		
		// bro1
		animations.put("bro1WalkRight", new Animation(Config.WALKING_SPEED, atlas.findRegions("npcBro1Right"), Animation.LOOP));
		animations.put("bro1IdleRight", new Animation(Config.WALKING_SPEED, atlas.findRegions("npcBro1"), Animation.LOOP));
		
		// cat lady
		animations.put("catladyWalkRight", new Animation(Config.WALKING_SPEED, atlas.findRegions("npcCatladyRight"), Animation.LOOP));
		animations.put("catladyIdleRight", new Animation(Config.WALKING_SPEED, atlas.findRegions("npcCatlady"), Animation.LOOP));
		
		// bro2
		animations.put("bro2WalkRight", new Animation(Config.WALKING_SPEED, atlas.findRegions("npcBro2Right"), Animation.LOOP));
		animations.put("bro2IdleRight", new Animation(Config.WALKING_SPEED, atlas.findRegions("npcBro2"), Animation.LOOP));
		
		// lady
		animations.put("ladyWalkRight", new Animation(Config.WALKING_SPEED, atlas.findRegions("npcLadyRight"), Animation.LOOP));
		animations.put("ladyIdleRight", new Animation(Config.WALKING_SPEED, atlas.findRegions("npcLady"), Animation.LOOP));
		
		// phone guy
		animations.put("phoneGuyWalkRight", new Animation(Config.WALKING_SPEED, atlas.findRegions("npcPhoneGuyRight"), Animation.LOOP));
		animations.put("phoneGuyIdleRight", new Animation(Config.WALKING_SPEED, atlas.findRegions("npcPhoneGuy"), Animation.LOOP));
		
		// sounds
		Sound phone = assetManager.get(Path.SOUND_PHONE, Sound.class);
		sounds.put("phone", phone);
		
		Sound bus = assetManager.get(Path.SOUND_BUS, Sound.class);
		sounds.put("bus", bus);
		
		Sound itemClick = assetManager.get(Path.SOUND_ITEM_CLICK, Sound.class);
		sounds.put("itemClick", itemClick);
		
		// musics
		Music mainTheme = assetManager.get(Path.MUSIC_THEME, Music.class);
		mainTheme.setLooping(true);
		musics.put("mainTheme", mainTheme);
		
		Music walkmanTheme = assetManager.get(Path.MUSIC_WALKMAN, Music.class);
		mainTheme.setLooping(true);
		musics.put("walkman", walkmanTheme);
		
		Music themeUpbeat = assetManager.get(Path.MUSIC_THEME_UPBEAT, Music.class);
		mainTheme.setLooping(true);
		musics.put("themeUpbeat", themeUpbeat);
		
		Music gameOverTheme = assetManager.get(Path.MUSIC_GAMEOVER, Music.class);
		mainTheme.setLooping(true);
		musics.put("gameOverTheme", gameOverTheme);
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
	
	public Sound getSound(String soundName) {
		if (sounds.containsKey(soundName)) {
			return sounds.get(soundName);
		} else {
			Gdx.app.error(TAG, "Sound" + soundName + " does not exist!");
			return null;
		}
	}
	
	public static class Path {
		public static final String SPRITESHEET = "data/spritesheet.txt";
		public static final String SPRITESHEET2 = "data/spritesheet2.txt";
		public static final String SPRITESHEET3 = "data/spritesheet3.txt";
		public static final String FONT_NORMAL = "data/font/silkscreen.fnt";
		public static final String FONT_BUBBLE = "data/font/silkBubble.fnt";
		public static final String MUSIC_THEME = "data/music/vantan_theme.mp3";
		public static final String MUSIC_WALKMAN = "data/music/vantan_walkman.mp3";
		public static final String MUSIC_THEME_UPBEAT = "data/music/vantan_theme-upbeat.mp3";
		public static final String MUSIC_GAMEOVER = "data/music/vantan_gameover.mp3";
		public static final String SOUND_PHONE = "data/sounds/phone.mp3";
		public static final String SOUND_BUS = "data/sounds/bus.mp3";
		public static final String SOUND_ITEM_CLICK = "data/sounds/item_click.mp3";
	}
}

