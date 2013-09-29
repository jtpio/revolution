package com.speed.run.npc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Quint;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.speed.run.IndieSpeedRun;
import com.speed.run.dialog.Sentence;
import com.speed.run.engine.MoveableEntity;
import com.speed.run.engine.Renderer;
import com.speed.run.engine.Util;
import com.speed.run.managers.Assets;
import com.speed.run.managers.Config;
import com.speed.run.tweens.MoveableEntityAccessor;

public class NpcManager {

	public static final String[] jsonFiles = {
		"data/dialogs/bros.json",
		"data/dialogs/homeless.json"
	};
	
	private ArrayList<Npc> npcs;
	private float spawnTimer;
	private float busTimer;
	private ArrayList<Pair> pairs;
	
	// next schedules
	private float nextBus;
	private float nextSpawn;
	
	private MoveableEntity bus;
	
	public NpcManager() {
		reset();
	}
	
	public boolean isBusHere() {
		return bus.getPosition().x == 0;
	}
	
	private void nextBus() {
		nextBus = MathUtils.random(Config.MIN_TIME_BUS_COMES, Config.MAX_TIME_BUS_COMES);
		busTimer = 0;
	}
	
	private void nextSpawn() {
		nextSpawn = Config.SPAWN_INTERVAL;
		spawnTimer = 0;
	}
	
	public void reset() {
		npcs = new ArrayList<Npc>();
		pairs = new ArrayList<Pair>();
		nextBus();
		nextSpawn();
		for (int i = 0; i < jsonFiles.length; i++) {
			loadDialogs(jsonFiles[i]);
		}
		bus = new MoveableEntity();
		bus.setPosition(Config.INIT_POS_X_BUS, Config.POS_Y_BUS);
		bus.addAnimation("idle", Assets.getInstance().getAnimation("bus"));
		bus.setDepth(150);
		Renderer.getInstance().addEntity(bus);
	}
	
	public void update(float dt) {
		busTimer += dt;
		spawnTimer += dt;
		if (spawnTimer > nextSpawn) {
			add();
			nextSpawn();
		}
		
		Iterator<Npc> iterator = npcs.iterator();
		while (iterator.hasNext()) {
			Npc npc = iterator.next();
			npc.update(dt);
		}
		
		if (busTimer > nextBus) {
			nextBus();
			Timeline.createSequence()
				.push(Tween.to(bus, MoveableEntityAccessor.POSITION_XY, 1.0f).target(0, bus.getPosition().y).ease(Quint.OUT).setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						for (Npc npc: npcs) {
							Renderer.getInstance().removeLayer(npc.getDepth());
						}
						npcs = new ArrayList<Npc>();
						
					}
				}))
				.pushPause(Config.WAITING_TIME_STOP)
				.push(Tween.to(bus, MoveableEntityAccessor.POSITION_XY, 1.0f).target(Config.END_POS_X_BUS, bus.getPosition().y).ease(Quint.IN).setCallback(new TweenCallback() {
					
					@Override
					public void onEvent(int type, BaseTween<?> source) {
						bus.setPosition(Config.INIT_POS_X_BUS, Config.POS_Y_BUS);	
					}
				}))
				.start(IndieSpeedRun.tweenManager);
		}
	}
	
	
	
	public void render(SpriteBatch batch) {
		Iterator<Npc> iterator = npcs.iterator();
		while (iterator.hasNext()) {
			Npc npc = iterator.next();
			npc.draw(batch);
		}
		bus.draw(batch);
	}
	
	public void add() {
		if (npcs.size() >= Config.MAX_NB) return;
		// TODO: choose random animation
		
		Npc npc = null;
		Npc npc2 = null;
		if (Math.random() > 0 && pairs.size() > 0) {
			int dudes = (int)Math.random() * pairs.size();
			Pair pair = pairs.remove(dudes);
			
			npc = new TalkingNpc(pair.getFirst());
			if (pair.getSecond() != null) {
				npc2 = new TalkingNpc(pair.getSecond());
			}
		} else {
			npc = new Npc();
		}
		
		int layer = MathUtils.random(0, Config.NB_LAYERS);
		npc.setDepth(Config.START_DEPTH - layer);
		float newY = Config.NPC_Y_POS + Config.DEPTH_INCR * layer;
		Vector2 nPos = new Vector2(Util.randomSign() * Config.X_SPAWN, newY);
		Vector2 nTarget = new Vector2(MathUtils.random(-Config.WIDTH/2, Config.HEIGHT/2), newY);
		npc.setPosition(nPos.x, nPos.y);
		npc.moveTo(nTarget.x, nTarget.y);
		
		if (npc2 != null) {
			npc2.setPosition(nPos.x + Config.DISTANCE_DIALOG, nPos.y);
			npc2.moveTo(nTarget.x + Config.DISTANCE_DIALOG, nTarget.y);
			npc2.setDepth(Config.START_DEPTH - layer);
			npcs.add(npc2);
			Renderer.getInstance().addEntity(npc2);
		}

		npcs.add(npc);
		Renderer.getInstance().addEntity(npc);
		
	}
	
	private void loadDialogs(String jsonFile) {
		JsonReader jsonReader = new JsonReader();
		JsonValue root = jsonReader.parse(Gdx.files.internal(jsonFile));
		int n = root.getInt("n");
		LinkedList<Sentence> firstList = new LinkedList<Sentence>();
		LinkedList<Sentence> secondList = null;
		String firstName = root.getString("first");
		String secondName = null;
		if (n == 2) {
			secondList = new LinkedList<Sentence>();
			secondName = root.getString("second");
		}
		JsonValue dialogs = root.get("dialogs");
		for (int i = 0; i < dialogs.size; i++) {
			JsonValue v = dialogs.get(i);
			Sentence s = new Sentence(i * (Config.SENTENCE_DURATION + Config.PAUSE), Config.SENTENCE_DURATION, v.getString(1));
			
			if (v.getInt(0) == 0) firstList.add(s);
			else secondList.add(s);
		}
		
		pairs.add(new Pair(firstList, secondList, firstName, secondName));
	}
	
	public void removeRandom() {
		Npc npc = npcs.get((int)Math.random()*npcs.size());
		npc.kill();
		npcs.remove(npc);
	}
}
