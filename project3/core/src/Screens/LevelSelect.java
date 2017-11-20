package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Pyramid;
import Screens.PlayScreen;

public class LevelSelect implements Screen {

	private Texture background;
	private Stage buttonStage;
	private ImageButton level1Button;
	private ImageButton level2Button;
	private ImageButton level3Button;
	private ImageButton level4Button;
	private ImageButton level5Button;
	private ImageButton level6Button;
	private ImageButton level7Button;
	private ImageButton level8Button;
	private ImageButton level9Button;
	private ImageButton level10Button;
	private ImageButton menuButton;
	
	private OrthographicCamera gameCam;
	private Viewport gamePort;

	private Pyramid game;

	public SpriteBatch sb;


	public static int count;

	public LevelSelect(Pyramid p) {

		this.game = p;
		buttonStage = new Stage();
		
		gameCam = new OrthographicCamera();
		// create a FitViewport to maintain virtual aspect ratio despite screen
		gamePort = new FitViewport(Pyramid.V_WIDTH, Pyramid.V_HEIGHT, gameCam);
		
		// initially set our gamcam to be centered correctly at the start of of map
		gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
				
		
		Gdx.input.setInputProcessor(buttonStage);

		background = new Texture("StartGame/levelSelect.png");

		// level1-Link
		level1Button = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/1.png")))));

		level1Button.setBounds((Pyramid.V_WIDTH /2) -  500,(Pyramid.V_WIDTH / 2)- 275, Pyramid.PPM + 100, Pyramid.PPM + 100);


		// level2-Link
		level2Button = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/2.png")))));
		level2Button.setBounds((Pyramid.V_WIDTH /2) -  325,(Pyramid.V_WIDTH / 2)- 275, Pyramid.PPM + 100, Pyramid.PPM + 100);

		// level3-Link
		level3Button = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/3.png")))));
		level3Button.setBounds((Pyramid.V_WIDTH /2) -  150,(Pyramid.V_WIDTH / 2)- 275, Pyramid.PPM + 100, Pyramid.PPM + 100);

		// level4-Link
		level4Button = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/4.png")))));
		level4Button.setBounds((Pyramid.V_WIDTH /2) + 50,(Pyramid.V_WIDTH / 2)- 275, Pyramid.PPM + 100, Pyramid.PPM + 100);

		// level5-Link
		level5Button = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/5.png")))));
		level5Button.setBounds((Pyramid.V_WIDTH /2) +  200,(Pyramid.V_WIDTH / 2) - 275, Pyramid.PPM + 100, Pyramid.PPM + 100);

		// level6-Link
		level6Button = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/6Click.png")))));
		level6Button.setBounds((Pyramid.V_WIDTH /2) -  500,(Pyramid.V_WIDTH / 2)  - 450, Pyramid.PPM + 100, Pyramid.PPM + 100);

		// level7-Link
		level7Button = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/7Click.png")))));
		level7Button.setBounds((Pyramid.V_WIDTH /2) -  325,(Pyramid.V_WIDTH / 2)  - 450, Pyramid.PPM + 100, Pyramid.PPM + 100);

		// level8-Link
		level8Button = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/8Click.png")))));
		level8Button.setBounds((Pyramid.V_WIDTH /2)-150,(Pyramid.V_WIDTH / 2) - 450, Pyramid.PPM + 100, Pyramid.PPM + 100);

		// level9-Link
		level9Button = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/9Click.png")))));
		level9Button.setBounds((Pyramid.V_WIDTH /2) +25,(Pyramid.V_WIDTH / 2) - 450, Pyramid.PPM + 100, Pyramid.PPM + 100);

		// level10-Link
		level10Button = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/10Click.png")))));
		level10Button.setBounds((Pyramid.V_WIDTH /2) +  300,(Pyramid.V_WIDTH / 2) - 450, Pyramid.PPM + 100, Pyramid.PPM + 100);
		
		menuButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/left.png")))));
		menuButton.setBounds((Pyramid.V_WIDTH /2) -600,(Pyramid.V_WIDTH / 2) - 650, Pyramid.PPM + 100, Pyramid.PPM + 100);
	
		menuButton.addListener(new ClickListener()   {
			
			public void clicked(InputEvent event  , float x , float y) {
				Pyramid.manager.get("sounds/button2.wav", Sound.class).play();
				super.clicked(event, x , y);
				game.setScreen(new Menu(game));
			}
		});
		level10Button.setBounds((Pyramid.V_WIDTH /2) +  200,(Pyramid.V_HEIGHT / 2) - 150, Pyramid.PPM + 100, Pyramid.PPM + 100);

		level1Button.addListener(new ClickListener() {

			public void clicked(InputEvent event, float x, float y) {
				count = 1;

				
				// Stop music
				Pyramid.manager.get("music/music_start.ogg", Music.class).stop();
				super.clicked(event, x, y);
				Pyramid.manager.get("sounds/button1.wav", Sound.class).play();
				game.setScreen(new PlayScreen(game));
			}
			});

		level2Button.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				count = 2;	
				
				// Stop music
				Pyramid.manager.get("music/music_start.ogg", Music.class).stop();
				super.clicked(event, x, y);
				Pyramid.manager.get("sounds/button1.wav", Sound.class).play();
				game.setScreen(new PlayScreen(game));
			}
		});

		level3Button.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				count = 3;

				
				// Stop music
				Pyramid.manager.get("music/music_start.ogg", Music.class).stop();
				super.clicked(event, x, y);
				Pyramid.manager.get("sounds/button1.wav", Sound.class).play();
				game.setScreen(new PlayScreen(game));
			}
		});

		level4Button.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				count = 4;
				
				// Stop music
				Pyramid.manager.get("music/music_start.ogg", Music.class).stop();
				super.clicked(event, x, y);
				Pyramid.manager.get("sounds/button1.wav", Sound.class).play();
				game.setScreen(new PlayScreen(game));
			}
		});

		level5Button.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				count = 5;
				// Stop music
				Pyramid.manager.get("music/music_start.ogg", Music.class).stop();
				super.clicked(event, x, y);
				Pyramid.manager.get("sounds/button1.wav", Sound.class).play();
				game.setScreen(new PlayScreen(game));
			}
		});

		buttonStage.addActor(level1Button);
		buttonStage.addActor(level2Button);
		buttonStage.addActor(level3Button);
		buttonStage.addActor(level4Button);
		buttonStage.addActor(level5Button);
		buttonStage.addActor(level6Button);
		buttonStage.addActor(level7Button);
		buttonStage.addActor(level8Button);
		buttonStage.addActor(level9Button);
		buttonStage.addActor(level10Button);
		buttonStage.addActor(menuButton);
	}

	// beer
	public void update(float dt) {
		
		count = PlayScreen.keep_count;
		
		// update our gamecam with correct coordinates after changes.
		gameCam.update();

	}

	@Override
	public void show() {
		sb = new SpriteBatch();

	}

	@Override
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Set our batch to now draw what the Hud camera see.
		game.sb.setProjectionMatrix(gameCam.combined);

		update(delta);
		
		game.sb.begin();
		game.sb.draw(background, 0, 0, Pyramid.V_WIDTH, Pyramid.V_HEIGHT);
		game.sb.end();
		
		buttonStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		buttonStage.draw();

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
		sb.dispose();
		background.dispose();
		buttonStage.dispose();

	}

}
