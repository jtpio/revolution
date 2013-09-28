package com.speed.run.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector3;
import com.speed.run.IndieSpeedRun;
import com.speed.run.Player;
import com.speed.run.engine.Entity;
import com.speed.run.managers.Assets;
import com.speed.run.managers.Strings;
import com.speed.run.npc.NpcManager;

public class BusStopScreen extends BaseScreen {
	
	protected Entity background;
	protected BitmapFont font;
	protected NpcManager npcManager;
	
	public BusStopScreen(IndieSpeedRun game) {
		super(game);
		Player.getInstance().setAnimation(Assets.getInstance().getAnimation("player"));
		background = new Entity(Assets.getInstance().getAnimation("bg"));
		background.setPosition(0, 300);
		font = Assets.getInstance().getFont("normal");
		font.scale(0.001f);
		npcManager = new NpcManager();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		// process input
		input();
		
		// updates
		Player.getInstance().update(delta);
		//camera.position.set(Player.getInstance().getPos().x, Player.getInstance().getPos().y, 0);
		npcManager.update(delta);
		camera.update();
		
		// rendering
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		background.draw(batch);
		npcManager.render(batch);
		Player.getInstance().draw(batch);
		font.draw(batch, Strings.WAITING_TIME,
				-150 -font.getBounds(Strings.WAITING_TIME).width / 2,
				150 -font.getBounds(Strings.WAITING_TIME).height / 2);
		batch.end();
		
		super.render(delta);
	}
	
	protected void input() {
		if (Gdx.input.justTouched()) {
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			Vector3 vec = new Vector3(x, y, 0);
			camera.unproject(vec);
			Player.getInstance().moveTo(vec.x, vec.y);
		}
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		super.show();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		super.hide();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		super.pause();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		super.resume();
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		super.dispose();
	}
	
}
