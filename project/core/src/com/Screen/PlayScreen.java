package com.Screen;

import com.Scenes.HUD;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PyramidOfAcabat;

public class PlayScreen implements Screen {
	
	private PyramidOfAcabat game;
	private OrthographicCamera gameCam;
	private Viewport gamePort;
	private HUD hud;
	
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer tmr;
	
	
//	Texture texture;
	
	
	public  PlayScreen(PyramidOfAcabat game) {
		this.game = game;
//		texture = new Texture("background.png");
		gameCam = new OrthographicCamera();
		gamePort = new FitViewport(PyramidOfAcabat.V_WIDTH, PyramidOfAcabat.V_HEIGHT, gameCam);
		hud = new HUD(game.sb);
		maploader = new TmxMapLoader();
		map = maploader.load("level1.tmx");
		tmr = new OrthogonalTiledMapRenderer(map);
		gameCam.position.set(gamePort.getScreenWidth() / 2, gamePort.getWorldHeight() /2, 0);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void handleInput(float dt) {
		if(Gdx.input.isTouched()) {
			gameCam.position.x += 100*dt;
		}
		
	}
	

	public void update(float dt) {

		handleInput(dt);
		
		gameCam.update();
		tmr.setView(gameCam);
		
	}
	
	@Override
	public void render(float delta) {
		
		update(delta);
		
		// Clear the game screen with Black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
//		game.sb.setProjectionMatrix(gameCam.combined);
//		
//		game.sb.begin();
//		game.sb.draw(texture, 0, 0);
//		game.sb.end();
		
		tmr.render();
		
		
		// Set our batch to now draw what the Hud camera see.
		game.sb.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		gamePort.update(width, height);
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
