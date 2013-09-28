package com.speed.run.npc;

import java.util.ArrayList;
import java.util.Iterator;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.speed.run.engine.Util;
import com.speed.run.managers.Assets;
import com.speed.run.managers.Config;

public class NpcManager {

	private ArrayList<Npc> npcs;
	private float lastSpawn, lastRemove;
	
	public NpcManager() {
		reset();
	}
	
	public void reset() {
		npcs = new ArrayList<Npc>();
		lastSpawn = 0;
		lastRemove = 0;
	}
	
	public void update(float dt) {
		lastSpawn += dt;
		if (lastSpawn > Config.SPAWN_INTERVAL) {
			add();
			lastSpawn = 0;
		}
		
		lastRemove += dt;
		if (lastRemove > Config.REMOVE_INTERVAL) {
			removeRandom();
			lastRemove = 0;
		}
		
		Iterator<Npc> iterator = npcs.iterator();
		while (iterator.hasNext()) {
			Npc npc = iterator.next();
			npc.update(dt);
		}
	}
	
	public void render(SpriteBatch batch) {
		Iterator<Npc> iterator = npcs.iterator();
		while (iterator.hasNext()) {
			Npc npc = iterator.next();
			npc.draw(batch);
		}
	}
	
	public void add() {
		if (npcs.size() >= Config.MAX_NB) return;
		// TODO: choose random animation
		Npc npc = new TalkingNpc(Assets.getInstance().getAnimation("npc0"));
		npc.setPosition(Util.randomSign() * 1000, Config.NPC_Y_POS + 10);
		npc.moveTo(Util.random(-Config.WIDTH/2,	Config.HEIGHT/2), Config.NPC_Y_POS + 10);
		npcs.add(npc);
	}
	
	public void removeRandom() {
		npcs.remove((int)Math.random()*npcs.size());
	}
}
