package com.alesegdia.thevaker.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.alesegdia.thevaker.GameConfig;
import com.alesegdia.thevaker.GdxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = GameConfig.WINDOW_WIDTH;
		config.height = GameConfig.WINDOW_HEIGHT;
		config.title = "TheVaker";
		new LwjglApplication(new GdxGame(), config);
	}
}
