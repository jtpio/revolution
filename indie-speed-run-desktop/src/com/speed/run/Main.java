package com.speed.run;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.speed.run.managers.Config;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "indie-speed-run";
		cfg.useGL20 = false;
		cfg.width = Config.WIDTH;
		cfg.height = Config.HEIGHT;
		//cfg.fullscreen = true;
		
		new LwjglApplication(new IndieSpeedRun(), cfg);
	}
}
