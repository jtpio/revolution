package com.speed.run;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;
import com.badlogic.gdx.utils.Array;

public class IndieSpeedRunTests extends Game {
	
	public static final int WIDTH = 1200;
	public static final int HEIGHT = 720;
	
	private OrthographicCamera camera;
	private SpriteBatch batch;
	private Sprite sprite;
	private Sprite sprite2;
	Animation oldEnemyAnimation;

	float stateTime = 0;
	
	Stage stage;
	Button button;
	
	float zoom = 0;

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);
		System.out.println(camera.position);
		batch = new SpriteBatch();

		TextureAtlas atlas = new TextureAtlas("data/spritesheet.txt");
		sprite = atlas.createSprite("potato");
		sprite.setPosition(0f, 0.0f);

		sprite2 = atlas.createSprite("potato");
		sprite2.setPosition(5f, 5f);

		Array<AtlasRegion> regions = atlas.findRegions("oldEnemyDead");
		System.out.println("regions: " + regions.size);
		oldEnemyAnimation = new Animation(0.5f, regions);
		oldEnemyAnimation.setPlayMode(Animation.LOOP);
		
		stage = new Stage(w, h, true);
		Gdx.input.setInputProcessor(stage);
		Table table = new Table();
		table.setFillParent(true);
		stage.addActor(table);

		TextButton start = new TextButton("Start", getSkin());
		table.add(start);
	}

	@Override
	public void render() {
		stateTime += Gdx.graphics.getDeltaTime();

		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		camera.zoom = (float) Math.sin(stateTime);
		camera.update();
		//camera.translate(new Vector2(1, 0));
		//camera.rotateAround(new Vector3(0,0,0), new Vector3(0,0,1), 3f * ((float)Math.random()-0.5f));
				
		// batch.setProjectionMatrix(camera.combined);
		batch.setProjectionMatrix(camera.combined);
		batch.begin();
		sprite.draw(batch);
		sprite2.draw(batch);
		TextureRegion tr = oldEnemyAnimation.getKeyFrame(stateTime);
		batch.draw(tr, 50, 50);
		batch.end();

		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}

	@Override
	public void dispose() {
		batch.dispose();
		stage.dispose();
	}

	public Skin getSkin() {
		Skin skin = null;
		if (skin == null) {
			skin = new Skin();

			Pixmap p = new Pixmap(1, 1, Format.RGBA8888);
			p.setColor(Color.WHITE);
			p.fill();
			skin.add("white", new Texture(p));
			skin.add("default", getFont());

			// button
			TextButtonStyle tbs = new TextButtonStyle();
			tbs.up = skin.newDrawable("white", Color.ORANGE);
			tbs.down = skin.newDrawable("white", Color.ORANGE);
			tbs.checked = skin.newDrawable("white", Color.BLACK);
			tbs.over = skin.newDrawable("white", Color.WHITE);
			tbs.font = skin.getFont("default");
			skin.add("default", tbs);

			LabelStyle ls = new LabelStyle();
			ls.font = skin.getFont("default");
			skin.add("default", ls);

		}

		return skin;
	}

	public BitmapFont getFont() {
		BitmapFont font = null;
		if (font == null) {
			font = new BitmapFont(Gdx.files.internal("data/ui/fnt.fnt"), false);
		}
		return font;
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}

