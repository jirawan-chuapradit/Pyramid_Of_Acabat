package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Pyramid;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Pyramid(), config);
		

		config.width = Pyramid.V_WIDTH;
		config.height = Pyramid.V_HEIGHT;
		config.title = "Pyramid Of Acabat V.test";
	
	}
}
