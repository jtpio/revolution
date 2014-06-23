package com.speed.run.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.speed.run.IndieSpeedRun;
import com.speed.run.engine.Entity;
import com.speed.run.managers.Assets;

public class GameOverScreen extends BaseScreen {

	private Entity busBG;
	
	public GameOverScreen(IndieSpeedRun game) {
		super(game);
				
		busBG = new Entity();
		busBG.addAnimation("idleLeft", Assets.getInstance().getAnimation("gameover"));
		busBG.setAnimation("idleLeft");
		busBG.setPosition(0, 0);
		busBG.setDepth(110);

	}
	
	@Override
	public void show() {
		super.show();
		Assets.getInstance().getMusic("mainTheme").stop();
		Assets.getInstance().getMusic("walkman").stop();
		Assets.getInstance().getMusic("gameOverTheme").play();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		busBG.draw(batch);
		batch.end();
		
		super.render(delta);
	}
	
	
}
