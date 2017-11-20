package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

public class Congrate implements Screen {
	private Texture background;
	private OrthographicCamera gameCam;
	private Viewport gamePort;
	private ImageButton nextStage;
	private ImageButton replay;
	private Pyramid game;
	private Stage buttonStage;
	public SpriteBatch sb;
	
	public Congrate(Pyramid g) {
		this.game = g;
		gameCam = new OrthographicCamera();
		buttonStage = new Stage();
		
		Gdx.input.setInputProcessor(buttonStage);
		
		// create a FitViewport to maintain virtual aspect ratio despite screen
		gamePort = new FitViewport(Pyramid.V_WIDTH, Pyramid.V_HEIGHT, gameCam);

		// initially set our gamcam to be centered correctly at the start of of map
		gameCam.position.set(gamePort.getWorldWidth() / 2, gamePort.getWorldHeight() / 2, 0);

		background = new Texture("congrate/congrateBG.png");

		// Next Stage Button
		nextStage = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("congrate/right.png")))));
		nextStage.setBounds((Pyramid.V_WIDTH / 2), 180, 120, 120);
		
		// Replay
		replay = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("congrate/re.png")))));
		replay.setBounds((Pyramid.V_WIDTH / 2) - 150, 180, 120, 120);

		
		nextStage.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				// Stop music
				super.clicked(event, x, y);
				PlayScreen.keep_count++;
				LevelSelect.count++;
				System.out.println(PlayScreen.keep_count);
				Pyramid.manager.get("sounds/button1.wav", Sound.class).play();
				Pyramid.manager.get("sounds/win_stage.wav", Sound.class).stop();
				game.setScreen(new PlayScreen(game));
			}
		});
		
		replay.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				// Stop music
				super.clicked(event, x, y);
				Pyramid.manager.get("sounds/button1.wav", Sound.class).play();
				Pyramid.manager.get("sounds/win_stage.wav", Sound.class).stop();
				game.setScreen(new PlayScreen(game));
			}
		});
		
		buttonStage.addActor(nextStage);
		buttonStage.addActor(replay);
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
		game.dispose();

	}

}
