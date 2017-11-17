package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import State.Menu;
import handles.Content;

public class Pyramid extends Game {
	
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;
	public static final float PPM = 100;
	
	public SpriteBatch sb;
	
	private Texture img;
	
	public static Content res;
	public Pyramid() {
		
	}
	
	
	@Override
	public void create () {
		
		sb = new SpriteBatch();
		Gdx.gl.glClearColor(0, 1, 0, 1);
		this.setScreen(new Menu(this));
		

		
		
	}

	@Override
	public void render () {
		super.render();
		
		Gdx.gl.glClearColor(1, 0, 0, 1);
	}
	
	@Override
	public void dispose () {

		sb.dispose();

	}
	

	
	public void pause() {
		
	}
	
	public void resume() {
		
	}

}
