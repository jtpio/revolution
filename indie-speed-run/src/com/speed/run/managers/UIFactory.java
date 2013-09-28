package com.speed.run.managers;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class UIFactory {

	public static Skin newItemSkin() {
		Skin skin = new Skin();

		Pixmap p = new Pixmap(1, 1, Format.RGBA8888);
		p.setColor(Color.WHITE);
		p.fill();
		skin.add("white", new Texture(p));
		skin.add("default", Assets.getInstance().getFont("normal"));

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

		return skin;
	}

}
