
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
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;


//import handler.GameStateManager;

public class Play extends GameState{
		
		private World world;
		private Box2DDebugRenderer b2dr;
		

		
		private OrthographicCamera b2dCam;
		
		private Body playerBody;
//		private MyContactListener cl;
		
		private TmxMapLoader maploader;
		private TiledMap tileMap;
		private float tileSize;
		private OrthogonalTiledMapRenderer tmr;
		
		public Play(GameStateManager gsm) {
			
			super(gsm);

//			
//			world = new World(new Vector2(0, -9.81f), true);
//			
//			cl = new MyContactListener();
//			world.setContactListener(cl);
//			
//			b2dr = new Box2DDebugRenderer();
//			
//			BodyDef bdef = new BodyDef();
//			FixtureDef fdef = new FixtureDef();
//			PolygonShape shape = new PolygonShape();
			
			// create player
//			bdef.position.set(160 / PPM, 200 / PPM);
//			bdef.type = BodyType.DynamicBody;
//			playerBody = world.createBody(bdef);
			
//			shape.setAsBox(5 / PPM, 5 / PPM);
//			fdef.shape = shape;
//			fdef.filter.categoryBits = B2DVar.BIT_PLAYER;
//			fdef.filter.maskBits = B2DVar.BIT_RED;
//			playerBody.createFixture(fdef).setUserData("player");
//			
			// create foot sensor
//			shape.setAsBox(2 / PPM, 2 / PPM, new Vector2(0, -5 / PPM), 0);
//			fdef.shape = shape;
//			fdef.filter.categoryBits = B2DVar.BIT_PLAYER;
//			fdef.filter.maskBits = B2DVar.BIT_RED;
//			fdef.isSensor = true;
//			playerBody.createFixture(fdef).setUserData("foot");
//			
			// set up box2d cam
			b2dCam = new OrthographicCamera();
			b2dCam.setToOrtho(false, PyramidOfAcabat.WEIDTH, PyramidOfAcabat.HEIGHT);
			
			//////////////////////////////////////////////
			
			// load tile map
			maploader = new TmxMapLoader();
			tileMap = maploader.load("assets/leve1.tmx");
			tmr = new OrthogonalTiledMapRenderer(tileMap);
			
			TiledMapTileLayer layer = (TiledMapTileLayer) tileMap.getLayers().get("graphics");
			
			tileSize = layer.getTileWidth();
//			
			// go through all the cells in the layer
			for(int row = 0; row < layer.getHeight(); row++) {
				for(int col = 0; col < layer.getWidth(); col++) {
					
					// get cell
					Cell cell = layer.getCell(col, row);
					
					// check if cell exists
					if(cell == null) continue;
					if(cell.getTile() == null) continue;
//					
				// create a body + fixture from cell
//					bdef.type = BodyType.StaticBody;
//					bdef.position.set(
//						(col + 0.5f) * tileSize / PPM,
//						(row + 0.5f) * tileSize / PPM
//					);
//					
//					ChainShape cs = new ChainShape();
//					Vector2[] v = new Vector2[3];
//					v[0] = new Vector2(
//						-tileSize / 2 / PPM, -tileSize / 2 / PPM);
//					v[1] = new Vector2(
//						-tileSize / 2 / PPM, tileSize / 2 / PPM);
//					v[2] = new Vector2(
//						tileSize / 2 / PPM, tileSize / 2 / PPM);
//					cs.createChain(v);
//					fdef.friction = 0;
//					fdef.shape = cs;
//					fdef.filter.categoryBits = B2DVar.BIT_PINK;
//					fdef.filter.maskBits = B2DVar.BIT_PLAYER;
//					fdef.isSensor = false;
//					world.createBody(bdef).createFixture(fdef);
//					
//					
				}
			}
			
		}
		
		public void handleInput() {
			
			// player jump
//			if(MyInput.isPressed(MyInput.BUTTON1)) {
//				if(cl.isPlayerOnGround()) {
//					playerBody.applyForceToCenter(0, 200, true);
//				}
//			}
//			
		}
		
		public void update(float dt) {
			
			handleInput();
			
			world.step(dt, 6, 2);
			
		}
		
		public void render() {
			
			// clear screen
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			
			// draw tile map
			tmr.setView(cam);
			tmr.render();
//			
			// draw box2d world
			b2dr.render(world, b2dCam.combined);
			
		}
		
		public void dispose() {}

	}
