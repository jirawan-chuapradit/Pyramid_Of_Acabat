package com.mygdx.game;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
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
	//music
	public static AssetManager manager;
	
	public Pyramid() {
	
	}
	
	@Override
public void create () {
		
		sb = new SpriteBatch();	
		
		//set music
		manager = new AssetManager();
		manager.load("music/music1.ogg", Music.class);
		manager.load("music/music2.ogg", Music.class);
		//manager.load("music/music3.ogg", Music.class);
		manager.load("music/music_start.ogg", Music.class);
		manager.load("music/music_end.ogg", Music.class);
		manager.load("sounds/jump.wav", Sound.class);
		manager.load("sounds/win_stage.wav", Sound.class);
		manager.load("sounds/shift.wav", Sound.class);
		manager.load("sounds/button1.wav", Sound.class);
		manager.load("sounds/button2.wav", Sound.class);
		manager.load("sounds/endSound.wav", Sound.class);
		manager.finishLoading();
	
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
