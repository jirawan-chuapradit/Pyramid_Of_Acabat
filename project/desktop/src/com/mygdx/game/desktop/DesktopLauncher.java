package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.Game;
//import com.mygdx.game.PyramidOfAcabat;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		new LwjglApplication(new Game(), config);
		config.width = Game.V_WIDTH;
		config.height = Game.V_HEIGHT;
		config.title = "Pyramid Of Acabat V.test";
		
		System.out.println("Main");
		
	}
}
