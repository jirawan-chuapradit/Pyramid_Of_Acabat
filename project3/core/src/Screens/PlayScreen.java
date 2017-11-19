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
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Pyramid;
import Sprites.BluePlayer;
import Sprites.PinkPlayer;
import Tools.B2WorldCreator;
import Tools.WorldContactListener;

public class PlayScreen implements Screen {

	private Texture background;
	private Pyramid game;
	private GameOverScreen gameOverScreen;

	// beer
	private TextureAtlas atlas;

	public static int keep_count;
	private ImageButton levelStage;
	// switch
	public static ImageButton switchBlueButton;
	public static ImageButton switchPinkButton;
	public static Stage buttonStage;
	private Switch sw;

	private OrthographicCamera gameCam;
	private Viewport gamePort;

	// Tile map variables
	private TmxMapLoader maploader;
	private TiledMap map;
	private OrthogonalTiledMapRenderer tmr;

	// Box2d variables
	private World world;
	private Box2DDebugRenderer b2dr;

	private SpriteBatch sb;
	private Hud hud;

	public static BluePlayer bluePlayer;
	public static PinkPlayer pinkPlayer;
	private ImageButton clockButton;
	private ImageButton hpButton;

	public static B2WorldCreator b2WorldCreator;

	public static float time;

	private Music music;

	public static boolean enableSwitchColor;

	public PlayScreen(Pyramid gsm) {

		atlas = new TextureAtlas("Animation/Player.pack");

		buttonStage = new Stage();

		this.game = gsm;

		gameCam = new OrthographicCamera();
		// create a FitViewport to maintain virtual aspect ratio despite screen
		gamePort = new FitViewport(Pyramid.V_WIDTH / Pyramid.PPM, Pyramid.V_HEIGHT / Pyramid.PPM, gameCam);

		keep_count = LevelSelect.count;
		// load our map and setup our map renderer
		maploader = new TmxMapLoader();
		map = maploader.load("Map/level" + keep_count + ".tmx");
		tmr = new OrthogonalTiledMapRenderer(map, 1 / Pyramid.PPM);

		// initially set our gamcam to be centered correctly at the start of of map
		gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

		time = 0;
		enableSwitchColor = true;

		world = new World(new Vector2(0, -10), true);
		
		// Create Icon
		// HP Button
		hpButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("StartGame/health3.png")))
						));
//		hpButton.setBounds(500, Pyramid.PPM, 5, 20);
		
		// Clock Icon
		clockButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("StartGame/clock.png")))
				));
		clockButton.setBounds(1000, 1200, 100, 25);


		// buttonStage
		Gdx.input.setInputProcessor(buttonStage);
		levelStage = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("StartGame/level-stage.png")))));
		levelStage.setBounds(0, 0, 125, 100);
		
		levelStage.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				keep_count = 0;
				// Stop music Pyramid.manager.get("music/music_start.ogg", Music.class).stop();
				super.clicked(event, x, y);
				Pyramid.manager.get("sounds/button1.wav", Sound.class).play();
				game.setScreen(new LevelSelect(game));
			}
		});
		
		// button switch blue
		switchBlueButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch/sb.png")))));
		switchBlueButton.setBounds(600, 570, 100, 100);
		// button switch pink
		switchPinkButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("switch/sp.png")))));
		switchPinkButton.setBounds(600, 570, 100, 100);

		buttonStage.addActor(levelStage);
//		buttonStage.addActor(hpButton);
		buttonStage.addActor(clockButton);
		
		// allows for debug lines of our box2d world
		b2dr = new Box2DDebugRenderer();

		b2WorldCreator = new B2WorldCreator(world, map);

		sb = new SpriteBatch();

		hud = new Hud();
		
		// create BluePlayer and PinkPlayer in our game world
		bluePlayer = new BluePlayer(world, this);
		pinkPlayer = new PinkPlayer(world, this);

		// Object switch
		sw = new Switch();

		// load music Game
		music = Pyramid.manager.get("music/music1.ogg", Music.class);
		music.setLooping(true);
		music.play();

		world.setContactListener(new WorldContactListener());

	}

	public TextureAtlas getAtlas() {
		return atlas;
	}

	@Override
	public void show() {

	}

	public void update(float dt) {

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		hud.update(dt);

		keep_count = LevelSelect.count;

		// case press click
		switch (B2WorldCreator.currentColor) {
		case 0:
			buttonStage.addActor(switchBlueButton);
			sw.switchBlueButton();
			break;
		case 1:
			buttonStage.addActor(switchPinkButton);
			sw.switchPinkButton();
			break;
		}
		//System.out.println(B2WorldCreator.currentColor);
		// case press shift
		sw.update(dt);

		// check two player Stay on the Flag
		CheckNextLevel();

		// GameOver
		CheckGameOver();

		// tekes 1 step in the physics simulation(60 times per second
		world.step(1 / 60f, 6, 2);

		pinkPlayer.update(dt);
		bluePlayer.update(dt);

		hud.update(dt);

		// update our gamecam with correct coordinates after changes.
		gameCam.update();

		// tell our render to draw only what our camers can see in our game world.
		tmr.setView(gameCam);
		
		
		if(Hud.health == 2) {
			hpButton = new ImageButton(new TextureRegionDrawable(
					new TextureRegion(new Texture(Gdx.files.internal("StartGame/health2.png")))
							));
			
		}
		else if(Hud.health == 1) {
			hpButton = new ImageButton(new TextureRegionDrawable(
					new TextureRegion(new Texture(Gdx.files.internal("StartGame/health1.png")))
					));
		}
		else if(Hud.health == 0)
		{
			hpButton = new ImageButton(new TextureRegionDrawable(
					new TextureRegion(new Texture(Gdx.files.internal("StartGame/health0.png")))
					));
		
		}
		
		hpButton.setBounds(500, 500, 400, 100);
		buttonStage.addActor(hpButton);

	}

	@Override
	public void render(float delta) {

		// Clear the game screen with Black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// separate our update screen logic from render
		update(delta);

		// render buttonStage
		buttonStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

		// render our game map
		tmr.render();

		// renderer our Box2DDubugLines
		b2dr.render(world, gameCam.combined);

		// Set our batch to now draw what the Hud camera see.
		game.sb.setProjectionMatrix(gameCam.combined);
		game.sb.begin();
		pinkPlayer.draw(game.sb);
		bluePlayer.draw(game.sb);
		game.sb.end();

		// draw buttonStage
		buttonStage.draw();

		game.sb.setProjectionMatrix(hud.stage.getCamera().combined);
		hud.stage.draw();

	}

	public void CheckNextLevel() {

		// check Next Stage
		if (WorldContactListener.ischeckPink() == true && WorldContactListener.ischeckBlue() == true) {

			WorldContactListener.setcheckPink(false);
			WorldContactListener.setcheckBlue(false);

			game.setScreen(new LevelSelect(game));
		}
	}

	private void CheckGameOver() {

		// check player is on Grounds
		if (WorldContactListener.isCheckGameOver() == true) {
			WorldContactListener.setCheckGameOver(false);
			game.setScreen(new GameOverScreen(game));
		}

		// check player Time up
		if (hud.getWorldTimer() == 0) {
			game.setScreen(new GameOverScreen(game));
		}

		// check Health is zero
		if (hud.getHealth() == 0) {
			game.setScreen(new GameOverScreen(game));
		}

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
		sb.dispose();
		hud.dispose();
		atlas.dispose();
		map.dispose();
		tmr.dispose();
		world.dispose();
		b2dr.dispose();
		background.dispose();
	}

}
