package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Pyramid;

import Sprites.BluePlayer;
import Sprites.PinkPlayer;
<<<<<<< HEAD:project3/core/src/Screens/PlayScreen.java
import Tools.B2WorldCreator;
=======
import State.LevelSelect;
import Tools.B2WorldCreator;
import Tools.WorldContactListener;
>>>>>>> Beer:project3/core/src/Screens/PlayScreen.java

public class PlayScreen implements Screen {
	private Pyramid game;
//	private LevelSelect game;
	Texture texture;
	
	// beer
	private TextureAtlas atlas;
	
	private OrthographicCamera gameCam;	
	private Viewport gamePort;

	
	//Tile map variables
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer tmr;
	
	// Box2d variables
	private World world;
	private Box2DDebugRenderer b2dr;
	
	private SpriteBatch sb;
	
	private BluePlayer bluePlayer;
	private PinkPlayer pinkPlayer;
	
	private B2WorldCreator b2WorldCreator;
	
	private float time;
	private boolean enableSwitchColor;
<<<<<<< HEAD:project3/core/src/Screens/PlayScreen.java
	
	private Music music;
	
	public PlayScreen(Pyramid game) {
		
		atlas = new TextureAtlas("Animation/Player_Animation.pack");
		
		this.game = game;
		
=======
	public PlayScreen(Pyramid gsm) {
		
		atlas = new TextureAtlas("Animation/Player_Animation.pack");
		
		this.game = gsm;
		
>>>>>>> Beer:project3/core/src/Screens/PlayScreen.java
		gameCam = new OrthographicCamera();
		// create a FitViewport to maintain virtual aspect ratio despite screen
		gamePort = new FitViewport(Pyramid.V_WIDTH/Pyramid.PPM, Pyramid.V_HEIGHT/Pyramid.PPM, gameCam);
		
		// load our map and setup our map renderer
		maploader = new TmxMapLoader();
<<<<<<< HEAD:project3/core/src/Screens/PlayScreen.java
		map = maploader.load("Map/level3.tmx");
=======
		map = maploader.load("Map/level5.tmx");
>>>>>>> Beer:project3/core/src/Screens/PlayScreen.java
		tmr = new OrthogonalTiledMapRenderer(map, 1 /Pyramid.PPM);
		
		// initially set our gamcam to be centered correctly at the start of of map
		gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
	
		time = 0;
		enableSwitchColor = true;
		
	
		
		world = new World(new Vector2(0, -10), true);
		// allows for debug lines of our box2d world
		b2dr = new Box2DDebugRenderer();
		
		b2WorldCreator = new B2WorldCreator(world, map);
		
		sb = new SpriteBatch();

		
<<<<<<< HEAD:project3/core/src/Screens/PlayScreen.java
//		public void handleInput(float dt) {
//
//			// control our player using inmudiate impulse 
//			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
//				b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
//			}
//		}
		
		// create BluePlayer in our game world
		bluePlayer = new BluePlayer(world, this);
		pinkPlayer = new PinkPlayer(world, this);	
		
		music = Pyramid.manager.get("music/music1.ogg", Music.class);
		music.setLooping(true);
		music.play();
=======
		// create BluePlayer and PinkPlayer in our game world
		bluePlayer = new BluePlayer(world);
		pinkPlayer = new PinkPlayer(world, this);	
		
		world.setContactListener(new WorldContactListener());
		
>>>>>>> Beer:project3/core/src/Screens/PlayScreen.java
		
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
<<<<<<< HEAD:project3/core/src/Screens/PlayScreen.java
=======
	
>>>>>>> Beer:project3/core/src/Screens/PlayScreen.java


	@Override
	public void show() {
		
		
	}
	
	

	public void update(float dt) {
		
		if(enableSwitchColor) {
			if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
<<<<<<< HEAD:project3/core/src/Screens/PlayScreen.java
				Pyramid.manager.get("sounds/shift.wav", Sound.class).play();
				
=======
		
>>>>>>> Beer:project3/core/src/Screens/PlayScreen.java
				b2WorldCreator.switchColor();

				
				enableSwitchColor = false;
			}
		}
		else {
			if(time >= 2) {
				enableSwitchColor = true;
				time = 0;
			}
			else {
				time += dt;
			}
			System.out.println(time);
		}
		
		// handle user input first
	
		if (b2WorldCreator.getCurrentColor() != 0) {
			pinkPlayer.handleInput(dt);
		}
		else {
			bluePlayer.handleInput(dt);
		}
		
		
<<<<<<< HEAD:project3/core/src/Screens/PlayScreen.java
		
		world.step(1/60f, 6, 2);
=======
		//  check two player Stay on the Flag
		
		CheckNextLevel();
		
		
		
		world.step(1/60f, 6, 2);
		
		pinkPlayer.update(dt);
>>>>>>> Beer:project3/core/src/Screens/PlayScreen.java
		
		pinkPlayer.update(dt);
		bluePlayer.update(dt);
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
		
		// render our game map
		tmr.render();

		// renderer our Box2DDubugLines
		b2dr.render(world, gameCam.combined);
		
		// Set our batch to now draw what the Hud camera see.
		game.sb.setProjectionMatrix(gameCam.combined);
		game.sb.begin();
		pinkPlayer.draw(game.sb);
<<<<<<< HEAD:project3/core/src/Screens/PlayScreen.java
		bluePlayer.draw(game.sb);
=======
>>>>>>> Beer:project3/core/src/Screens/PlayScreen.java
		game.sb.end();
		
		
//		System.out.println(pinkPlayer.b2body.getPosition());
//		sb.begin();
//		sb.draw(pinkPlayer.getFramePink(delta), (pinkPlayer. b2body.getPosition().x*Pyramid.PPM) -25, (pinkPlayer. b2body.getPosition().y*Pyramid.PPM)-15 , 50, 50);
//		sb.end();
		
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
//		hud.dispose();
		
	}
	
	public void CheckNextLevel() {
		System.out.println(WorldContactListener.CHECKPINK);
		System.out.println(WorldContactListener.CHECKBLUE);
		// check Next Stage
		if(WorldContactListener.CHECKPINK == true && WorldContactListener.CHECKBLUE == true) {
			System.out.println("worldContactListener.checkPink == true");

				game.setScreen(new LevelSelect(game));
		}
	}
	

}
