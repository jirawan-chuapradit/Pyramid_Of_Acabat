package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.handlers.Content;
import com.handlers.GameStateManager;
import com.handlers.MyInput;
import com.handlers.MyInputProcessor;

public class Game implements ApplicationListener{
	
	public static final int V_WIDTH = 1280;
	public static final int V_HEIGHT = 720;
	
	public static final float STEP = 1/60f;
	private float accum;
	
	private SpriteBatch sb;
	private OrthographicCamera cam;
	private OrthographicCamera hudCam;
	
	private GameStateManager gsm;
	
	public static Content res;
	
	
	public void create() {
		
//		Texture.setEnforcePotImages(false);
		
		Gdx.input.setInputProcessor(new MyInputProcessor());
		
		res = new Content();
		res.loadTexture("bluePlayer.gif", "bluePlayer");
		res.loadTexture("flag.png", "flag");
		
		
		sb = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		hudCam = new OrthographicCamera();
		hudCam.setToOrtho(false, V_WIDTH, V_HEIGHT);
		
		gsm = new GameStateManager(this);
		
		
	}
	
	public void render() {
		
		accum += Gdx.graphics.getDeltaTime();
		while(accum > STEP) {
			accum -= STEP;
			gsm.update(STEP);
			gsm.update(STEP);
			gsm.render();
			MyInput.update();
		}
		
		
		
	}
	
	public void dispose() {
		
	}
	
	public SpriteBatch getSpriteBathch() {
		return sb;
	}
	
	public OrthographicCamera getCamera() {
		return cam;
	}
	
	public OrthographicCamera getHUDCamera() {
		return hudCam;
	}
	
	public void resize(int w, int h) {
		
	}
	

	public void pause() {
		// TODO Auto-generated method stub
		
	}

	public void resume() {
		
	}
	
	

}
