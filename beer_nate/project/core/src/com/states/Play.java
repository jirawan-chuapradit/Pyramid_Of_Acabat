package com.states;

import static com.handlers.B2DVars.PPM;

import java.util.Vector;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
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
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.handlers.B2DVars;
import com.handlers.GameStateManager;
import com.handlers.MyContactListener;
import com.handlers.MyInput;
import com.mygdx.game.Game;

public class Play extends GameState {

	private World world;
	private Box2DDebugRenderer b2dr;

	private OrthographicCamera b2dCam;

	private Body playerBody;
	private MyContactListener cl;

	private TiledMap tileMap;
	private float tileSize;
	private OrthogonalTiledMapRenderer tmr;

	public Play(GameStateManager gsm) {
		super(gsm);

		world = new World(new Vector2(0, -9.81f), true);
		cl = new MyContactListener();
		world.setContactListener(cl);
		b2dr = new Box2DDebugRenderer();

		// create player
		createPlayer();

		// create tiles
		createTiles();

		// set up box2d cam
		b2dCam = new OrthographicCamera();
		b2dCam.setToOrtho(false, Game.V_WIDTH / PPM, Game.V_HEIGHT / PPM);

		/////////////////////////////////////////////////////////////////////

	}

	public void handleInput() {

		// player jump

		if (MyInput.isPreessed(MyInput.BUTTON1)) {
			if (cl.isPlayerOnGround()) {
				playerBody.applyForceToCenter(0, 100, true);
			}
		}

	}

	public void update(float dt) {

		handleInput();

		world.step(dt, 6, 2);

	}

	public void render() {

		// clear screen
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// drew tile map
		tmr.setView(cam);
		tmr.render();

		// draw box2d wolrd
		b2dr.render(world, b2dCam.combined);

	}

	public void dispose() {

	}

	private void createPlayer() {

		BodyDef bdef = new BodyDef();
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();

		// create player
		bdef.position.set(160 / PPM, 200 / PPM);
		bdef.type = BodyType.DynamicBody;
		playerBody = world.createBody(bdef);

		shape.setAsBox(5 / PPM, 5 / PPM);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_BLUE;
		playerBody.createFixture(fdef).setUserData("player");

		// crate foot sensor
		shape.setAsBox(2 / PPM, 2 / PPM, new Vector2(0, -5 / PPM), 0);
		fdef.shape = shape;
		fdef.filter.categoryBits = B2DVars.BIT_PLAYER;
		fdef.filter.maskBits = B2DVars.BIT_BLUE;
		fdef.isSensor = true;
		playerBody.createFixture(fdef).setUserData("foot");

	}

	private void createTiles() {

		// load tile map
		tileMap = new TmxMapLoader().load("level1.tmx");
		tmr = new OrthogonalTiledMapRenderer(tileMap);
		tileSize =  tileMap.getProperties().get("tilewidth", Integer.class);
		
		TiledMapTileLayer layer;
		
		layer = (TiledMapTileLayer) tileMap.getLayers().get("blue");
		createLayer(layer, B2DVars.BIT_BLUE);
		
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

				// ChainShape cs = new ChainShape();
				Vector2[] v = new Vector2[3];
				v[0] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[1] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				v[2] = new Vector2(-tileSize / 2 / PPM, -tileSize / 2 / PPM);
				// cs.createChain(v);
				fdef.friction = 0;
				// fdef.shape = cs;
				fdef.filter.categoryBits = B2DVars.BIT_BLUE;
				fdef.filter.maskBits = B2DVars.BIT_PLAYER;
				fdef.isSensor = false;
				world.createBody(bdef).createFixture(fdef);

			}
		}
	}

}
