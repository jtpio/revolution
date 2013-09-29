package com.speed.run.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Quad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.speed.run.IndieSpeedRun;
import com.speed.run.Player;
import com.speed.run.engine.Entity;
import com.speed.run.engine.Renderer;
import com.speed.run.items.Money;
import com.speed.run.items.Phone;
import com.speed.run.managers.Assets;
import com.speed.run.managers.Config;
import com.speed.run.npc.NpcManager;
import com.speed.run.tweens.BaseScreenAccessor;
import com.speed.run.tweens.MoveableEntityAccessor;
import com.speed.run.tweens.PhoneAcessor;

public class BusStopScreen extends BaseScreen {
	
	protected Entity busstop;
	protected Entity background;
	protected BitmapFont font;
	protected NpcManager npcManager;
	
	protected boolean pause = false;
	protected boolean stop = false;
	protected boolean phoneLocked = false;
	
	public BusStopScreen(IndieSpeedRun game) {
		super(game);
		
		// background
		background = new Entity();
		background.addAnimation("idleLeft", Assets.getInstance().getAnimation("bg"));
		background.setAnimation("idleLeft");
		background.setPosition(0, 300);
		background.setDepth(110);
		
		// booth
		busstop = new Entity();
		busstop.addAnimation("idleLeft", Assets.getInstance().getAnimation("busstop"));
		busstop.setAnimation("idleLeft");
		busstop.setPosition(0, -60);
		busstop.setDepth(120);
		
		font = Assets.getInstance().getFont("normal");
		font.scale(0.001f);
		npcManager = new NpcManager();

		// UI stuff for icons
		setupUI();
		
		// rendering
		Renderer.getInstance().addEntity(background);
		Renderer.getInstance().addEntity(busstop);
		Renderer.getInstance().addEntity(Player.getInstance());
		Renderer.getInstance().addEntity(Phone.getInstance());
		
		// start music
		Assets.getInstance().getMusic("mainTheme").play();
	}
	
	private void setupUI() {
		baseTable.pad(Config.ITEM_PADDING);
		baseTable.top().left();
		
		// phone
		final CheckBox phone = new CheckBox("", getSkin(Assets.getInstance().getSprites("phoneIcon")));
		baseTable.add(phone).width(Config.ICON_SIZE).height(Config.ICON_SIZE);
		phone.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				final boolean enabled = phone.isChecked();
				if (!pause && !phoneLocked) {
					Phone.getInstance().close();
					
					float x = Config.PHONE_X;
					float y = enabled ? Config.PHONE_Y_END:Config.PHONE_Y_INIT;
					phoneLocked = true;
					
					Tween.to(Phone.getInstance(), PhoneAcessor.POSITION_XY, 0.5f)
				    .target(x, y)
				    .delay(enabled?0.0f:1.0f)
				    .ease(Quad.INOUT)
				    .setCallback(new TweenCallback() {
						
						@Override
						public void onEvent(int type, BaseTween<?> source) {
							Phone.getInstance().open();
							phoneLocked = false;
						}
					})
				    .start(IndieSpeedRun.tweenManager);
				}
			}
		});
		
		// ipod
		final CheckBox ipod = new CheckBox("", getSkin(Assets.getInstance().getSprites("ipodIcon")));
		baseTable.add(ipod).width(Config.ICON_SIZE).height(Config.ICON_SIZE);
		ipod.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = ipod.isChecked();
				pause = !pause;
			}
		});
		
		// cigarettes
		final CheckBox cigarettes = new CheckBox("", getSkin(Assets.getInstance().getSprites("cigarettesIcon")));
		baseTable.add(cigarettes).width(Config.ICON_SIZE).height(Config.ICON_SIZE);
		cigarettes.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				boolean enabled = cigarettes.isChecked();
				if (!pause) {
					// TODO
					System.out.println(enabled);
				}
			}
		});
		
		// money
		final CheckBox money = new CheckBox("", getSkin(Assets.getInstance().getSprites("cashIcon")));
		baseTable.add(money).width(Config.ICON_SIZE).height(Config.ICON_SIZE);
		money.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				baseTable.removeActor(money);
				Money moneySprite = new Money();
				moneySprite.setDepth(190);
				Renderer.getInstance().addEntity(moneySprite);
				moneySprite.setPosition(Player.getInstance().getPosition().x, Player.getInstance().getPosition().y);
				
				Timeline.createSequence()
						.beginParallel()
							.push(Tween.to(moneySprite, MoveableEntityAccessor.SCALE_XY, 1.0f)
								    .target(10000.0f, 10000.0f)
								    .ease(Quad.IN))
							.push(Tween.to(moneySprite, MoveableEntityAccessor.ALPHA, 1.0f)
								    .target(0.0f)
								    .ease(Quad.IN))
						.end()
						.start(IndieSpeedRun.tweenManager);
			}
		});
		
		// money
		final CheckBox busTicket = new CheckBox("", getSkin(Assets.getInstance().getSprites("cashIcon")));
		baseTable.add(busTicket).width(Config.ICON_SIZE).height(Config.ICON_SIZE);
		busTicket.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				baseTable.removeActor(busTicket);
				if (npcManager.isBusHere()) {
					pause = true;
					Tween.to(game.getBusStopScreen(), BaseScreenAccessor.ALPHA, 2.0f)
					 .target(0.0f)
					 .setCallback(new TweenCallback() {
						
						@Override
						public void onEvent(int type, BaseTween<?> source) {
							game.setScreen(game.getInBusScreen());
						}
					})
					 .start(IndieSpeedRun.tweenManager);
				}
			}
		});
	}
	
	Skin getSkin(Sprite sprite) {
		Skin skin = new Skin();		
		skin.add("white", sprite);
		skin.add("default", Assets.getInstance().getFont("normal"));
		CheckBoxStyle cbs = new CheckBoxStyle();
		cbs.checkboxOn = skin.newDrawable("white");
		cbs.checkboxOff = skin.newDrawable("white");
		cbs.checkboxOver = skin.newDrawable("white", Color.GREEN);
		cbs.font = skin.getFont("default");
		skin.add("default", cbs);
		
		return skin;
		
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(1f, 1f, 1f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		
		if (!stop) {
			input();
			IndieSpeedRun.tweenManager.update(delta);
			Player.getInstance().update(delta);
			Phone.getInstance().update(delta);
		
			if (!pause) {		
				//camera.position.set(Player.getInstance().getPos().x, Player.getInstance().getPos().y, 0);
				npcManager.update(delta);
				//camera.translate(0, 1);
				camera.update();
			}
		}
		
		// rendering
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		
		Renderer.getInstance().render(batch, pause, alpha);
		/*background.draw(batch);
		busstop.draw(batch);
		npcManager.render(batch);*/
	/*	Player.getInstance().draw(batch);
		Phone.getInstance().draw(batch);*/
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
