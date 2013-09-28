package com.speed.run.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener.ChangeEvent;
import com.speed.run.IndieSpeedRun;
import com.speed.run.Player;
import com.speed.run.engine.Entity;
import com.speed.run.managers.Assets;
import com.speed.run.managers.Config;
import com.speed.run.npc.NpcManager;

public class BusStopScreen extends BaseScreen {
	
	protected Entity background;
	protected BitmapFont font;
	protected NpcManager npcManager;
	
	public BusStopScreen(IndieSpeedRun game) {
		super(game);
		background = new Entity();
		background.addAnimation("idleLeft", Assets.getInstance().getAnimation("bg"));
		background.setAnimation("idleLeft");
		background.setPosition(0, 300);
		font = Assets.getInstance().getFont("normal");
		font.scale(0.001f);
		npcManager = new NpcManager();

		setupUI();
		
		// start music
		Assets.getInstance().getMusic("mainTheme").play();
	}
	
	private void setupUI() {
		baseTable.pad(Config.ITEM_PADDING);
		baseTable.top().left();
		
		// phone
		final CheckBox phone = new CheckBox("P", getSkin());
		baseTable.add(phone).width(Config.ICON_SIZE).height(Config.ICON_SIZE);
		phone.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = phone.isChecked();
				System.out.println(enabled);
			}
		});
		
		// ipod
		final CheckBox ipod = new CheckBox("I", getSkin());
		baseTable.add(ipod).width(Config.ICON_SIZE).height(Config.ICON_SIZE);
		ipod.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = ipod.isChecked();
				System.out.println(enabled);
			}
		});
		
		// cigarettes
		final CheckBox cigarettes = new CheckBox("C", getSkin());
		baseTable.add(cigarettes).width(Config.ICON_SIZE).height(Config.ICON_SIZE);
		cigarettes.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = cigarettes.isChecked();
				System.out.println(enabled);
			}
		});
		
		// money
		final CheckBox money = new CheckBox("M", getSkin());
		baseTable.add(money).width(Config.ICON_SIZE)
				.height(Config.ICON_SIZE);
		money.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = money.isChecked();
				System.out.println(enabled);
			}
		});
	}
	
	Skin getSkin() {
		Skin skin = new Skin();

		Pixmap p = new Pixmap(1, 1, Format.RGBA8888);
		p.setColor(Color.WHITE);
		p.fill();
		skin.add("white", new Texture(p));
		skin.add("default", Assets.getInstance().getFont("normal"));

		// button
		CheckBoxStyle cbs = new CheckBoxStyle();
		cbs.checkboxOn = skin.newDrawable("white", Color.ORANGE);
		cbs.checkboxOff = skin.newDrawable("white", Color.GREEN);
		cbs.checked = skin.newDrawable("white", Color.ORANGE);
		cbs.font = skin.getFont("default");
		skin.add("default", cbs);

		LabelStyle ls = new LabelStyle();
		ls.font = skin.getFont("default");
		skin.add("default", ls);

		return skin;
		
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
		//camera.translate(0, 1);
		camera.update();
		
		// rendering
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		background.draw(batch);
		npcManager.render(batch);
		Player.getInstance().draw(batch);
	/*	font.draw(batch, Strings.WAITING_TIME,
				-150 -font.getBounds(Strings.WAITING_TIME).width / 2,
				150 -font.getBounds(Strings.WAITING_TIME).height / 2);*/
		batch.end();
		
		super.render(delta);
	}
	
	protected void input() {
		if (Gdx.input.justTouched()) {
			int x = Gdx.input.getX();
			int y = Gdx.input.getY();
			
			Vector2 stageXY = stage.screenToStageCoordinates(new Vector2(x, y));
			if (stage.hit(stageXY.x, stageXY.y, true) != null) return;
			
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
