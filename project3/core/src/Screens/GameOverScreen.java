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



public class GameOverScreen implements Screen {

	private Texture background;
	private SpriteBatch sb;
	private Pyramid game;
	
	private OrthographicCamera gameCam;
	private Viewport gamePort;
	
	private Stage buttonStage;
	
	private ImageButton NextButton;
	private ImageButton replayButton;
	
	public GameOverScreen(Pyramid gsm) {
		this.game = gsm;
		gameCam = new OrthographicCamera();
		
		// create a FitViewport to maintain virtual aspect ratio despite screen
		gamePort = new FitViewport(Pyramid.V_WIDTH, Pyramid.V_HEIGHT , gameCam);
		
		// initially set our gamcam to be centered correctly at the start of of map
		gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);
		
		buttonStage = new Stage();
		
		Gdx.input.setInputProcessor(buttonStage);
		
		background = new Texture("GameOver/gameover.png");
		
		NextButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/left.png")))));
		NextButton.setBounds((Pyramid.V_WIDTH / 2) - 150, 100, 120, 120);
		
		replayButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("congrate/re.png")))));
		replayButton.setBounds((Pyramid.V_WIDTH / 2), 100, 120, 120);
		
		NextButton.addListener(new ClickListener() {
			
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Pyramid.manager.get("sounds/button1.wav", Sound.class).play();
				Pyramid.manager.get("music/music_start.ogg", Music.class).play();
				game.setScreen(new LevelSelect(game));
			}
		});

		replayButton.addListener(new ClickListener() {
			
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Pyramid.manager.get("sounds/button1.wav", Sound.class).play();
				game.setScreen(new PlayScreen(game));
			}
		});

		buttonStage.addActor(NextButton);
		buttonStage.addActor(replayButton);

	}
	
	public void update(float dt) {
		// update our gamecam with correct coordinates after changes.
				gameCam.update();

	}

	@Override
	public void show() {
		sb = new SpriteBatch();		
	}

	@Override
	public void render(float delta) {
		// Clear the game screen with Black
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		game.sb.setProjectionMatrix(gameCam.combined);
		
		
		
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
		buttonStage.dispose();
		background.dispose();		
	}
}
