package com.c3m.flappybullet.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.c3m.flappybullet.FlappyBullet;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = FlappyBullet.WIDTH;
		config.height = FlappyBullet.HEIGHT;
		config.title = FlappyBullet.TITLE;
		new LwjglApplication(new FlappyBullet(), config);
	}
}
