package com.mygdx.game;

import com.Screens.PlayScreen;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.handles.Content;

public class Pyramid extends Game implements ApplicationListener {
	
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;
	public static final float PPM = 100;
	
	public SpriteBatch sb;
	
	public static Content res;
	
	@Override
	public void create () {
		
		sb = new SpriteBatch();
		setScreen(new PlayScreen(this));
		
		res = new Content();
		
		
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
