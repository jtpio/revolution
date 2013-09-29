package com.speed.run.engine;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeMap;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.speed.run.Player;

public class Renderer {
	
	private static Renderer instance = null;
	
	public static Renderer getInstance() {
		if (instance == null) instance = new Renderer();
		return instance;
	}
	
	private TreeMap<Integer, ArrayList<Entity>> entities;
	
	public Renderer() {
		entities = new TreeMap<Integer, ArrayList<Entity>>();
	}
	
	public void addEntity(Entity e) {
		if (entities.get(e.getDepth()) == null) entities.put(e.getDepth(), new ArrayList<Entity>());
		entities.get(e.getDepth()).add(e);
	}
	
	public void render(SpriteBatch batch, boolean pause, float alpha) {
		Iterator<ArrayList<Entity>> it = entities.values().iterator();
		while (it.hasNext()) {
			ArrayList<Entity> ls = it.next();
			for (Entity e: ls) {
				if (e.getDepth() < Player.getInstance().getDepth()) {
					float colorCmpt = pause?0.5f:1.0f;
					batch.setColor(colorCmpt, colorCmpt, colorCmpt, alpha);
				} else {
					batch.setColor(1.0f, 1.0f, 1.0f, alpha);
				}
				e.draw(batch);
			}
		}
	}
	
	public void removeLayer(int i) {
		entities.remove(i);
	}
}
