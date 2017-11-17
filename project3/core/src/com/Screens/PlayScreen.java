package com.Screens;

<<<<<<< HEAD
=======
import java.awt.Component.BaselineResizeBehavior;

>>>>>>> ่JugJig
import com.Sprites.BluePlayer;
import com.Sprites.PinkPlayer;
import com.Tools.B2WorldCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
<<<<<<< HEAD
<<<<<<< HEAD
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
=======
<<<<<<< HEAD
=======
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
=======
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
>>>>>>> ่JugJig
<<<<<<< HEAD
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
=======
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
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
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Pyramid;

public class PlayScreen implements Screen {
	private Pyramid game;
	Texture texture;
	private OrthographicCamera gameCam;	
	private Viewport gamePort;

	//Tile map variables
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer tmr;
	
	// Box2d variables
	private World world;
	private Box2DDebugRenderer b2dr;
	
<<<<<<< HEAD
=======
	private SpriteBatch sb;
	
<<<<<<< HEAD
<<<<<<< HEAD
	private TextureAtlas atlas;
=======
=======
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
>>>>>>> ่JugJig
	private BluePlayer bluePlayer;
	private PinkPlayer pinkPlayer;

	private B2WorldCreator b2WorldCreator;
<<<<<<< HEAD
=======
<<<<<<< HEAD
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
=======
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
	
>>>>>>> ่JugJig
	private float time;
	private boolean enableSwitchColor;
	public PlayScreen(Pyramid game) {
		
		atlas = new TextureAtlas("BluePlayer_Ac.pack");
		
		this.game = game;
		gameCam = new OrthographicCamera();
		// create a FitViewport to maintain virtual aspect ratio despite screen
		gamePort = new FitViewport(Pyramid.V_WIDTH/Pyramid.PPM, Pyramid.V_HEIGHT/Pyramid.PPM, gameCam);
		
		// load our map and setup our map renderer
		maploader = new TmxMapLoader();
		map = maploader.load("Map/level1.tmx");
		tmr = new OrthogonalTiledMapRenderer(map, 1 /Pyramid.PPM);
		
		// initially set our gamcam to be centered correctly at the start of of map
		gameCam.position.set(gamePort.getWorldWidth()/2, gamePort.getWorldHeight()/2, 0);
	
		time = 0;
		enableSwitchColor = true;
		
<<<<<<< HEAD
=======
		
>>>>>>> ่JugJig
		world = new World(new Vector2(0, -10), true);
		// allows for debug lines of our box2d world
		b2dr = new Box2DDebugRenderer();
<<<<<<< HEAD
<<<<<<< HEAD
	
		bluePlayer = new BluePlayer(world, this);
		
		BodyDef bdef = new BodyDef();
		PolygonShape shape = new PolygonShape();
		FixtureDef fdef = new FixtureDef();
		Body body;
		
		
		// create ground bodies/fixtures
		// playerPink
		for(MapObject object: map.getLayers().get(3).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() /2)/Pyramid.PPM, (rect.getY() + rect.getHeight()/ 2)/Pyramid.PPM);
			
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth() /2/Pyramid.PPM, rect.getHeight() /2/Pyramid.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
			
			
		}
		
		// create BlueBock bodies / fixtures
		for(MapObject object: map.getLayers().get(5).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() /2)/Pyramid.PPM, (rect.getY() + rect.getHeight()/ 2)/Pyramid.PPM);
			
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth() /2/Pyramid.PPM, rect.getHeight() /2/Pyramid.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
			
			
		}
		
		// create PinkBlock bodies / fixtures

		for(MapObject object: map.getLayers().get(4).getObjects().getByType(RectangleMapObject.class)) {
			
			Rectangle rect = ((RectangleMapObject) object).getRectangle();
			
			bdef.type = BodyDef.BodyType.StaticBody;
			bdef.position.set((rect.getX() + rect.getWidth() /2)/Pyramid.PPM, (rect.getY() + rect.getHeight()/ 2)/Pyramid.PPM);
			
			body = world.createBody(bdef);
			
			shape.setAsBox(rect.getWidth() /2/Pyramid.PPM, rect.getHeight() /2/Pyramid.PPM);
			fdef.shape = shape;
			body.createFixture(fdef);
			
			
		}
=======
		
		b2WorldCreator = new B2WorldCreator(world, map);
		
<<<<<<< HEAD
=======
		sb = new SpriteBatch();
		
>>>>>>> ่JugJig
//		public void handleInput(float dt) {
//
//			// control our player using inmudiate impulse 
//			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
//				b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
//			}
//		}
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
		
=======
		
		b2WorldCreator = new B2WorldCreator(world, map);
		
<<<<<<< HEAD
=======
		sb = new SpriteBatch();
		
>>>>>>> ่JugJig
//		public void handleInput(float dt) {
//
//			// control our player using inmudiate impulse 
//			if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
//				b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
//			}
//		}
		
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
		// create BluePlayer in our game world
		bluePlayer = new BluePlayer(world);
<<<<<<< HEAD
		pinkPlayer = new PinkPlayer(world);
<<<<<<< HEAD
		
=======
		pinkPlayer = new PinkPlayer(world);	
		
=======
		
=======
		pinkPlayer = new PinkPlayer(world);	
		
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
//		pinkPlayer.switchTypePlayer();
>>>>>>> ่JugJig
	}
	
	public TextureAtlas getAtlas() {
		return atlas;
	}
	@Override
	public void show() {
<<<<<<< HEAD
		// TODO Auto-generated method stub
=======
>>>>>>> ่JugJig
		
	}
	
<<<<<<< HEAD
<<<<<<< HEAD
	public void handleInput(float dt) {
		float current = bluePlayer.b2body.getLinearVelocity().y;
		// control our player using inmudiate impulse 
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && current%4 == 0) {
			bluePlayer.b2body.applyLinearImpulse(new Vector2(0, 4f), bluePlayer.b2body.getWorldCenter(), true);
		}
=======
	

	public void update(float dt) {
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
=======
	

	public void update(float dt) {
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
		
		if(enableSwitchColor) {
<<<<<<< HEAD
			if(Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
				
				b2WorldCreator.switchColor();
=======
			if(Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
//				
				b2WorldCreator.switchColor();

				
>>>>>>> ่JugJig
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
	
		if (b2WorldCreator.getCurrentColor() == 0) {
			pinkPlayer.handleInput(dt);
		}
		else {
			bluePlayer.handleInput(dt);
		}
		
		
		world.step(1/60f, 6, 2);
		
		
//		gameCam.position.x = playerPink.b2body.getPosition().x;
		
		bluePlayer.update(dt);
		
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
		
		game.sb.setProjectionMatrix(gameCam.combined);
		game.sb.begin();
		bluePlayer.draw(game.sb);
		game.sb.end();
		// Set our batch to now draw what the Hud camera see.
//		game.sb.setProjectionMatrix(hud.stage.getCamera().combined);
//		hud.stage.draw();
		
<<<<<<< HEAD
=======
		sb.begin();
		sb.draw(pinkPlayer.getFramePink(delta), pinkPlayer. b2body.getPosition().x/2 /Pyramid.PPM, pinkPlayer. b2body.getPosition().y/2/Pyramid.PPM, 100, 100);
		sb.end();
>>>>>>> ่JugJig
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
	

}
