package com.mygdx.game;

import com.Screen.PlayScreen;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PyramidOfAcabat extends Game {


	public static final int V_WIDTH = 1080;
	public static final int V_HEIGHT = 720;
	
	
	public SpriteBatch sb;

	
	@Override
	public void create () {
		
		System.out.println("Create");
		
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
