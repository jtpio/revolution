package com.speed.run.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.equations.Quad;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.FrameBuffer;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Button.ButtonStyle;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.speed.run.IndieSpeedRun;
import com.speed.run.Player;
import com.speed.run.engine.Entity;
import com.speed.run.engine.Renderer;
import com.speed.run.items.Phone;
import com.speed.run.managers.Assets;
import com.speed.run.managers.Config;
import com.speed.run.npc.NpcManager;
import com.speed.run.tweens.BaseScreenAccessor;
import com.speed.run.tweens.BlurAccessor;
import com.speed.run.tweens.CameraAccessor;
import com.speed.run.tweens.PhoneAcessor;

public class BusStopScreen extends BaseScreen {
	
	protected Entity busstop;
	protected Entity background;
	protected BitmapFont font;
	protected NpcManager npcManager;

	protected boolean pause = false;
	protected boolean stop = false;
	protected boolean phoneLocked = false;
	protected boolean intro = true;
	
	protected ShaderProgram shaderProgram;
	protected FrameBuffer blurTargetA, blurTargetB;
	protected TextureRegion fboRegion;
	
	public static final int FBO_SIZE = 1024;
	public static final float MAX_BLUR = 2.0f;
	protected Blur blur = new Blur();
	
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
		//setupUI();
		
		// rendering
		Renderer.getInstance().addEntity(background);
		Renderer.getInstance().addEntity(busstop);
		Renderer.getInstance().addEntity(Player.getInstance());
		Renderer.getInstance().addEntity(Phone.getInstance());
		
		// start music
		Assets.getInstance().getMusic("mainTheme").play();
		
		Tween.to(camera, CameraAccessor.POSITION_Y, Config.CAMERA_MOVING)
			 .target(0)
			 .delay(5.0f)
			 .start(IndieSpeedRun.tweenManager)
			 .setCallback(new TweenCallback() {
				
				@Override
				public void onEvent(int type, BaseTween<?> source) {
					setupUI();
					intro = false;
				}
			});
		
		npcManager.setOnBusThreeMissed(new OnBusThreeMissed() {
			
			@Override
			public void lose() {
				switchToFailScreen();
			}
		});
		
		ShaderProgram.pedantic = false;
		shaderProgram = new ShaderProgram(Gdx.files.internal("data/shaders/blur.vert"), Gdx.files.internal("data/shaders/blur.frag"));
		//shaderProgram = new ShaderProgram(VERT, FRAG);
		
		if (!shaderProgram.isCompiled()) {
			System.err.println(shaderProgram.getLog());
			System.exit(0);
		}
		if (shaderProgram.getLog().length()!=0)
			System.out.println(shaderProgram.getLog());
		
		shaderProgram.begin();
		shaderProgram.setUniformf("dir", 0f, 0f);
		shaderProgram.setUniformf("resolution", Gdx.graphics.getWidth());
		shaderProgram.setUniformf("radius", 1f);
		shaderProgram.end();
		
		blurTargetA = new FrameBuffer(Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		blurTargetB = new FrameBuffer(Format.RGBA8888, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), false);
		fboRegion = new TextureRegion(blurTargetA.getColorBufferTexture());
		fboRegion.flip(false, true);
		
		camera.setToOrtho(false);
		camera.zoom = 1.0f;
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
					
					if (!enabled) Assets.getInstance().getSound("phone").play(Config.HALF_VOLUME);
					
					Tween.to(Phone.getInstance(), PhoneAcessor.POSITION_XY, 0.5f)
				    .target(x, y)
				    .delay(enabled?0.0f:1.0f)
				    .ease(Quad.INOUT)
				    .setCallback(new TweenCallback() {
						
						@Override
						public void onEvent(int type, BaseTween<?> source) {
							Phone.getInstance().open();
							phoneLocked = false;
							if (enabled) Assets.getInstance().getSound("phone").play();
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
				Assets.getInstance().getSound("itemClick").play();
				pause = !pause;
				
				if (pause) {
					Assets.getInstance().getMusic("mainTheme").stop();
					Assets.getInstance().getMusic("walkman").stop();
					Assets.getInstance().getMusic("walkman").play();
					
					Tween.to(blur, BlurAccessor.BLUR_AMOUNT, 1.0f)
					 .target(1.0f)
					 .start(IndieSpeedRun.tweenManager);
					
				} else {
					Assets.getInstance().getMusic("walkman").stop();
					Assets.getInstance().getMusic("walkman").stop();
					Assets.getInstance().getMusic("mainTheme").play();
					
					Tween.to(blur, BlurAccessor.BLUR_AMOUNT, 1.0f)
					 .target(0.0f)
					 .start(IndieSpeedRun.tweenManager);
				}
				
				npcManager.toggleTimers(pause);
			}
		});
		
		/*
		// money
		final CheckBox money = new CheckBox("", getSkin(Assets.getInstance().getSprites("cashIcon")));
		baseTable.add(money).width(Config.ICON_SIZE).height(Config.ICON_SIZE);
		money.addListener(new ChangeListener() {
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				Assets.getInstance().getSound("itemClick").play();
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
		*/
		
		// bus ticket
		final Button busTicket = new Button(getButtonSkin(Assets.getInstance().getSprites("ticketIcon")));
		baseTable.add(busTicket).width(Config.ICON_SIZE).height(Config.ICON_SIZE);
		busTicket.addListener(new ClickListener() {

			@Override
			public void clicked(InputEvent event, float x, float y) {
				if (npcManager.isBusHere()) {
					baseTable.removeActor(busTicket);
					pause = true;
					
					Player.getInstance().setPosition(0, 3000);
					
					if (npcManager.nbBus == Config.BUS_NBR) {
						IndieSpeedRun.win = true;
						switchToSuccessScreen();
					} else {
						IndieSpeedRun.win = false;
						switchToFailScreen();
					}
				}
				super.clicked(event, x, y);
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
	
	Skin getButtonSkin(Sprite sprite) {
		Skin skin = new Skin();		
		skin.add("white", sprite);
		skin.add("default", Assets.getInstance().getFont("normal"));
		ButtonStyle cbs = new ButtonStyle();
		cbs.up = skin.newDrawable("white");
		skin.add("default", cbs);
		
		return skin;
		
	}

	private void switchToSuccessScreen() {
		Tween.to(game.getBusStopScreen(), BaseScreenAccessor.ALPHA, 2.0f)
		 .target(0.9f)
		 .setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				game.setScreen(game.getInBusScreen());
			}
		})
		 .start(IndieSpeedRun.tweenManager);
	}
	
	private void switchToFailScreen() {
		Tween.to(game.getBusStopScreen(), BaseScreenAccessor.ALPHA, 2.0f)
		 .target(0.9f)
		 .setCallback(new TweenCallback() {
			
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				game.setScreen(game.getGameOverScreen());
			}
		})
		 .start(IndieSpeedRun.tweenManager);
	}
	
	void resizeBatch(int width, int height) {
		//camera.setToOrtho(false, width, height);
		batch.setProjectionMatrix(camera.combined);
	}
	
	@Override
	public void render(float delta) {		
		if (!stop) {
			IndieSpeedRun.tweenManager.update(delta);
			
			if (!intro) {
				input();
				Player.getInstance().update(delta);
				Phone.getInstance().update(delta);
			
				if (!pause) {
					npcManager.update(delta);
				}
			}
			camera.update();
		}
		
		// rendering

		blurTargetA.begin();
		batch.setShader(null);
		resizeBatch(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

		batch.begin();
		Renderer.getInstance().render(batch, true);
		batch.flush();
		
		blurTargetA.end();
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1f);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		batch.setShader(shaderProgram);
		shaderProgram.setUniformf("dir", 1f, 0f);
		shaderProgram.setUniformf("radius", blur.getAmount() * MAX_BLUR);
		
		blurTargetB.begin();
		fboRegion.setTexture(blurTargetA.getColorBufferTexture());
		batch.draw(fboRegion, camera.position.x-blurTargetA.getWidth()/2, camera.position.y-blurTargetA.getHeight()/2);
		batch.flush();
		blurTargetB.end();
		
		resizeBatch(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		shaderProgram.setUniformf("dir", 0f, 1f);
		shaderProgram.setUniformf("radius", blur.getAmount() * MAX_BLUR);

		fboRegion.setTexture(blurTargetB.getColorBufferTexture());
		batch.draw(fboRegion, camera.position.x-blurTargetB.getWidth()/2, camera.position.y-blurTargetB.getHeight()/2);
		
		batch.setShader(null);

		Renderer.getInstance().render(batch, false);
		
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
		camera.position.set(0, 600, 0);
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
