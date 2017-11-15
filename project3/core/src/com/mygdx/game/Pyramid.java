package com.mygdx.game;

import com.Screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Pyramid extends Game {
	
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;
	public static final float PPM = 100;
	
	public SpriteBatch sb;
	
	@Override
	public void create () {
		sb = new SpriteBatch();
		setScreen(new PlayScreen(this));
	}

	@Override
	public void render () {
		super.render();
	}
	
	@Override
	public void dispose () {

	}
	

	
	public void pause() {
		
	}
	
	public void resume() {
		
	}

}
