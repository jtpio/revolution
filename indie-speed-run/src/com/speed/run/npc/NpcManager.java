package com.speed.run.npc;

import java.util.ArrayList;
import java.util.Iterator;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Quint;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.Timer.Task;
import com.speed.run.IndieSpeedRun;
import com.speed.run.engine.MoveableEntity;
import com.speed.run.engine.Renderer;
import com.speed.run.engine.Util;
import com.speed.run.items.BusSign;
import com.speed.run.managers.Assets;
import com.speed.run.managers.Config;
import com.speed.run.managers.Dialogs;
import com.speed.run.screens.OnBusThreeMissed;
import com.speed.run.tweens.MoveableEntityAccessor;

public class NpcManager {
	
	private ArrayList<Npc> npcs;
	private ArrayList<Pair> pairs;
	private int current = 0;
	public static int nbBus = 0;
	private OnBusThreeMissed onBusThreeMissed;
	
	public OnBusThreeMissed getOnBusThreeMissed() {
		return onBusThreeMissed;
	}

	public void setOnBusThreeMissed(OnBusThreeMissed onBusThreeMissed) {
		this.onBusThreeMissed = onBusThreeMissed;
	}

	private float[][] spots = {
			{-0.8f * Config.WIDTH/2, -Config.WIDTH/3},
			{Config.WIDTH/4, 0.8f * Config.WIDTH/2}
	};
	
	private String[] names = {"catlady", "bro1", "bro2", "lady", "phoneGuy"};
	
	private BusSign busSign;
	private MoveableEntity bus;
	
	// timers
	private Timer busTimer;
	private Timer spawnTimer;
	private Timer busSignTimer;
	
	public NpcManager() {
		reset();
	}
	
	public boolean isBusHere() {
		return Math.abs(bus.getPosition().x) < 100;
	}
	
	public void reset() {
		npcs = new ArrayList<Npc>();
		pairs = new ArrayList<Pair>(Dialogs.getInstance().getPairs());
		
		// retrieve pairs
		bus = new MoveableEntity();
		bus.setPosition(Config.INIT_POS_X_BUS, Config.POS_Y_BUS);
		bus.addAnimation("idle", Assets.getInstance().getAnimation("bus"));
		bus.setDepth(150);
		Renderer.getInstance().addEntity(bus);
		
		busSign = new BusSign();
		busSign.setPosition(Config.PANEL_X, Config.PANEL_Y);
		busSign.setDepth(149);
		Renderer.getInstance().addEntity(busSign);
		
		busTimer = new Timer();
		
		spawnTimer = new Timer();
		spawnTimer.scheduleTask(new Task() {
			@Override
			public void run() {
				add(current);
			}
		}, Config.FIRST_SPAWN, Config.SPAWN_INTERVAL);
		
		busSignTimer = new Timer();
		busSignTimer.scheduleTask(new Task() {
			@Override
			public void run() {
				busSign.setWaitingTime(MathUtils.random(2, 15));
			}
		}, 0, MathUtils.random(10, 20));
		
	}
	
	public void toggleTimers(boolean pause) {
		if (pause) {
			busTimer.stop();
			busSignTimer.stop();
			spawnTimer.stop();
		} else {
			busTimer.start();
			busSignTimer.start();
			spawnTimer.start();
		}
	}
	
	public void update(float dt) {		
		Iterator<Npc> iterator = npcs.iterator();
		while (iterator.hasNext()) {
			Npc npc = iterator.next();
			npc.update(dt);
		}
	}
	
	private void showBus() {
		nbBus++;
		busSign.setWaitingTime(MathUtils.random(2, 15));
		Assets.getInstance().getSound("bus").play(Config.HALF_VOLUME);
		Timeline.createSequence()
			.push(Tween.to(bus, MoveableEntityAccessor.POSITION_XY, Config.BUS_TRIP_LANDING).target(0, bus.getPosition().y).ease(Quint.OUT).setCallback(new TweenCallback() {
				
				@Override
				public void onEvent(int type, BaseTween<?> source) {
					for (Npc npc: npcs) {
						Renderer.getInstance().removeLayer(npc.getDepth());
					}
					npcs = new ArrayList<Npc>();
					
				}
			}))
			.pushPause(Config.WAITING_TIME_STOP)
			.push(Tween.to(bus, MoveableEntityAccessor.POSITION_XY, Config.BUS_TRIP_LEAVING).target(Config.END_POS_X_BUS, bus.getPosition().y).ease(Quint.IN).setCallback(new TweenCallback() {
				
				@Override
				public void onEvent(int type, BaseTween<?> source) {
					bus.setPosition(Config.INIT_POS_X_BUS, Config.POS_Y_BUS);
				}
			}))
			.start(IndieSpeedRun.tweenManager);
	}
	
	public void render(SpriteBatch batch) {
		Iterator<Npc> iterator = npcs.iterator();
		while (iterator.hasNext()) {
			Npc npc = iterator.next();
			npc.draw(batch);
		}
		bus.draw(batch);
	}
	
	// add a new wave
	public void add(int nbDialogs) {
				
		for (Npc npc: npcs) {
			if (npc instanceof TalkingNpc) {
				TalkingNpc tnpc = (TalkingNpc) npc;
				if (tnpc.isSpeaking()) return;
			}
		}
		
		current++;
		
		if (current == 3) {
			showBus();
			current = 0;
			return;
		}
		
		// bunch of random guys
		for (int i = 0; i < Config.MAX_RANDOM_GUYS; i++) {
			String name = getRandomName();
			Npc npc = new Npc(name);
			randomGuy(npc, -Config.WIDTH * 0.25f, Config.WIDTH * 0.25f);
			npcs.add(npc);
		}
		
		for (int i = 0; i < nbDialogs; i++) {
			if (pairs.size() == 0) continue;
			int dudes = (int)Math.random() * pairs.size();
			Pair pair = pairs.remove(dudes);
			TalkingNpc npc = new TalkingNpc(pair.getFirstName(), pair.getFirst());
			randomGuy(npc, spots[i][0], spots[i][1]);
			npcs.add(npc);
			TalkingNpc npc2 = null;
			if (pair.getSecond() != null) {
				npc2 = new TalkingNpc(pair.getSecondName(), pair.getSecond());
				randomGuy(npc2, spots[i][0], spots[i][1]);
				npc2.setPosition(npc.getPosition().x + 50.0f, npc.getPosition().y);
				npc2.setSpeed(npc.getSpeed());
				npc2.setDepth(npc.getDepth());
				npc2.moveTo(npc.getTarget().x + 50.0f, npc2.getTarget().y);
				npcs.add(npc2);
			}
		}
	}
	
	private String getRandomName() {
		return names[MathUtils.random(0, names.length-1)];
	}
	
	private void randomGuy(Npc npc, float xMin, float xMax) {
		npc.setSpeed(MathUtils.random(Config.INIT_SPEED, Config.MAX_SPEED));
		
		int layer = MathUtils.random(0, Config.NB_LAYERS);
		npc.setDepth(Config.START_DEPTH - layer);
		float newY = Config.NPC_Y_POS + Config.DEPTH_INCR * layer;
		Vector2 nPos = new Vector2(Util.randomSign() * Config.X_SPAWN, newY);
		Vector2 nTarget = new Vector2(MathUtils.random(xMin, xMax), newY);
		npc.setPosition(nPos.x, nPos.y);
		npc.moveTo(nTarget.x, nTarget.y);
		Renderer.getInstance().addEntity(npc);
	}
	
	public void removeRandom() {
		Npc npc = npcs.get((int)Math.random()*npcs.size());
		npc.kill();
		npcs.remove(npc);
	}
}
