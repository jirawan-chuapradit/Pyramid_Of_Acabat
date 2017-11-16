package com.Screen;

import com.Scenes.HUD;
import com.Sprite.playerPink;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PyramidOfAcabat;
import com.tools.B2WorldCreator;

public class PlayScreen implements Screen {
	
	private PyramidOfAcabat game;
	private OrthographicCamera gameCam;
	private Viewport gamePort;
	private HUD hud;
	
	
	
	//Tile map variables
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer tmr;
	
	// Box2d variables
	private World world;
	private Box2DDebugRenderer b2dr;
	
	private playerPink playerPink;
	
	
//	Texture texture;
	
	
	public  PlayScreen(PyramidOfAcabat game) {
		this.game = game;
//		texture = new Texture("background.png");
		gameCam = new OrthographicCamera();
		
		// create a FitViewport to maintain virtual aspect ratio despite screen
		gamePort = new FitViewport(PyramidOfAcabat.V_WIDTH/PyramidOfAcabat.PPM, PyramidOfAcabat.V_HEIGHT/PyramidOfAcabat.PPM, gameCam);
		
		// create our game HUD for scores / timers/ level info
		hud = new HUD(game.sb);
		 
		// load our map and setup our map renderer
		maploader = new TmxMapLoader();
		map = maploader.load("level1.tmx");
		tmr = new OrthogonalTiledMapRenderer(map, 1 /PyramidOfAcabat.PPM);
		
		// initially set our gamcam to be centered correctly at the start of of map
		gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
	
		world = new World(new Vector2(0, -10), true);
		// allows for debug lines of our box2d world
		b2dr = new Box2DDebugRenderer();

		new B2WorldCreator(world, map);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}
	
	public void handleInput(float dt) {

		// control our player using inmudiate impulse 
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			playerPink.b2body.applyLinearImpulse(new Vector2(0, 4f), playerPink.b2body.getWorldCenter(), true);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && playerPink.b2body.getLinearVelocity().x <= 2) {
			playerPink.b2body.applyLinearImpulse(new Vector2(0.1f, 0),  playerPink.b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && playerPink.b2body.getLinearVelocity().x >= -2) {
			playerPink.b2body.applyLinearImpulse(new Vector2(-0.1f, 0),  playerPink.b2body.getWorldCenter(), true);
		}
		
	}
	

	public void update(float dt) {

		// handle user input first
		handleInput(dt);
		
		world.step(1/60f, 6, 2);
		
		
//		gameCam.position.x = playerPink.b2body.getPosition().x;
		
		
		
//		update our gamecam with correct coordinates after changes.
		gameCam.update();
		
		// tell our render to draw only what our camers can see in our game world.
		tmr.setView(gameCam);
		
	}
	
	@Override
	public void render(float delta) {
		
		// separate our update screen logic from render
		update(delta);
		
		// Clear the game screen with Black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
//		game.sb.setProjectionMatrix(gameCam.combined);
		
//		game.sb.begin();
//		game.sb.draw(texture, 0, 0);
//		game.sb.end();
		
		// render our game map
		tmr.render();
		
		// renderer our Box2DDubugLines
		b2dr.render(world, gameCam.combined);
		
		
		// Set our batch to now draw what the Hud camera see.
		game.sb.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();
		
	}

	@Override
	public void resize(int width, int height) {
		
		// update our game viewport
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
		
		map.dispose();
		tmr.dispose();
		world.dispose();
		b2dr.dispose();
		hud.dispose();
		
	}

}
