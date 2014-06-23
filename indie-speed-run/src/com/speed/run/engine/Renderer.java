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
		
	private Renderer() {
		entities = new TreeMap<Integer, ArrayList<Entity>>();

	}
	
	public void addEntity(Entity e) {
		if (entities.get(e.getDepth()) == null) entities.put(e.getDepth(), new ArrayList<Entity>());
		entities.get(e.getDepth()).add(e);
	}
	
	public void render(SpriteBatch batch, boolean frontRendering) {
		Iterator<ArrayList<Entity>> it = entities.values().iterator();
		while (it.hasNext()) {
			ArrayList<Entity> ls = it.next();
			for (Entity e: ls) {
				if ((frontRendering && e.getDepth() < Player.getInstance().getDepth()) || (!frontRendering && e.getDepth() >= Player.getInstance().getDepth())) {
					e.draw(batch);
				}
			}
		}
	}
	
	public void removeLayer(int i) {
		entities.remove(i);
	}
}
