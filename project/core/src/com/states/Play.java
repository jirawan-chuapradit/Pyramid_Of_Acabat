package com.states;

import static com.handlers.B2DVars.PPM;

import java.security.CryptoPrimitive;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapLayer;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.entities.Flag;
import com.entities.Player;
import com.handlers.B2DVars;
import com.handlers.GameStateManager;
import com.handlers.MyContactListener;
import com.handlers.MyInput;
import com.mygdx.game.Game;

public class Play extends GameState {
	
	private boolean debug = false;

	private World world;
	private Box2DDebugRenderer b2dr;
//	public Body b2body;

	private OrthographicCamera b2dCam;
	private MyContactListener cl;

	private TiledMap tileMap;
	private float tileSize;
	private OrthogonalTiledMapRenderer tmr;
	
	private Player player;
	private Array<Flag> flag;
	

	public Play(GameStateManager gsm) {
		super(gsm);

		// set up  box2d stuff
		world = new World(new Vector2(0, -9.81f), true);
		cl = new MyContactListener();
		world.setContactListener(cl);
		b2dr = new Box2DDebugRenderer();

		// create player
		createPlayer();

		// create tiles
		createTiles();
		
		// create flag
//		createFlag();
		

		// set up box2d cam
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);

		/////////////////////////////////////////////////////////////////////

	}

	public void handleInput() {

		// player jump

		if (MyInput.isPreessed(MyInput.BUTTON1)) {
			if (player.getBody().getLinearVelocity().y == 0) {
				player.getBody().applyForceToCenter(0, 500, true);
			}
		}
			
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && player.getBody().getLinearVelocity().x <= 2) {
			player.getBody().applyLinearImpulse( new Vector2(0.1f, 0), player.getBody().getWorldCenter() , true);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && player.getBody().getLinearVelocity().x >= -2) {
			player.getBody().applyLinearImpulse( new Vector2(-0.1f, 0), player.getBody().getWorldCenter() , true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
			player.getBody().setLinearVelocity(0 , 0);
		}


	}

	public void update(float dt) {
		// handle user input first
		handleInput();

		world.step(dt, 6, 2);

		
		
		player.update(dt);
		
//		for(int i = 0; i < flag.size; i++) {
//			flag.get(i).update(dt);
//		}

	}

	public void render() {

		// clear screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// drew tile map
		tmr.setView(cam);
		tmr.render();
		
		// draw player
		sb.setProjectionMatrix(cam.combined);
		player.render(sb);

		// draw flag
//		for(int i = 0; i < flag.size; i++) {
//			flag.get(i).render(sb);
//		}

		
		// draw box2d wolrd
		
		if(debug) {
		b2dr.render(world, b2dCam.combined);
		}
	}

	public void dispose() {

	}

	private void createPlayer() {

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		// create player  น้องฟ้า
		bdef.position.set(160 / PPM, 200 / PPM);
		bdef.type = BodyType.DynamicBody;
		bdef.linearVelocity.set(.1f, 0);
		Body body = world.createBody(bdef);
		
		
		shape.setAsBox(25 / PPM, 30 / PPM);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_BLUE | B2DVars.BIT_GROUND;
		body.createFixture(fdef).setUserData("player");

		// crate foot sensor
		shape.setAsBox(25 / PPM, 5 / PPM, new Vector2(0, -45 / PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER | B2DVars.BIT_GROUND;
		fdef.filter.maskBits = B2DVars.BIT_BLUE;

		fdef.isSensor = true;
		body.createFixture(fdef).setUserData("foot");
		
		// create player
		player = new Player(body);
		
		body.setUserData(player);		

	}

	private void createTiles() {

		// load tile map
		tileMap = new TmxMapLoader().load("level1.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		tileSize =  tileMap.getProperties().get("tilewidth", Integer.class);
		
		TiledMapTileLayer layer;
		
		
		layer = (TiledMapTileLayer) tileMap.getLayers().get("blue");
		System.out.print(layer);
		 createLayer(layer, B2DVars.BIT_BLUE);
		 
		 layer = (TiledMapTileLayer) tileMap.getLayers().get("ground");
			System.out.print(layer);
			 createLayer(layer, B2DVars.BIT_GROUND);
		
		
		layer = (TiledMapTileLayer) tileMap.getLayers().get("pink");
		createLayer(layer, B2DVars.BIT_PINK);

	}

	private void createLayer(TiledMapTileLayer layer, short bits) {

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();

		// go through all the cells in the layer
		for (int row = 0; row < layer.getHeight(); row++) {
			for (int col = 0; col < layer.getWidth(); col++) {

				// get cell
				Cell cell = layer.getCell(col, row);

				// check if exists
				if (cell == null)
					continue;
				if (cell.getTile() == null)
					continue;

				// create a body + fixture from cell
			  	bdef.type = BodyType.StaticBody;
				bdef.position.set((col + 0.5f) * tileSize / PPM, (row + 0.5f) * tileSize / PPM);

				 ChainShape cs = new ChainShape();
				
				Vector2[] v = new Vector2[3];
				v[0] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[1] = new Vector2(-tileSize / 2 / PPM, tileSize / 2 / PPM);
				v[2] = new Vector2(tileSize / 2 / PPM, tileSize / 2 / PPM);
				 cs.createChain(v);
				 
				fdef.friction = 0;
				 fdef.shape = cs;
				fdef.filter.categoryBits = bits;
				fdef.filter.maskBits = B2DVars.BIT_PLAYER;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef);

			}
		}
	}
	
//	private void createFlag() {
//		BodyDef bdef = new BodyDef();
//		FixtureDef fdef = new FixtureDef();
//		Polygonshape shape = Polygonshape();
//		bdef.type = BodyType.StaticBody;
//		bdef.position.set();
//		world.createBody(bdef).createFixture(fdef);
//			
//			
//		}
	}

//}
