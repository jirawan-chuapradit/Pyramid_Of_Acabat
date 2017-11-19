package State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
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

	// private Menu menu;

	private Pyramid game;
	private Menu menu;
	public SpriteBatch sb;

	public static int count;
	
	public LevelSelect(Pyramid p) {

		this.game = p;
		buttonStage = new Stage();

		Gdx.input.setInputProcessor(buttonStage);

		background = new Texture("StartGame/levelSelect.png");

		// level1-Link
		level1Button = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("levelButton/button-level-01-out.png")))));
		level1Button.setBounds((Pyramid.V_WIDTH / 2) - 100, 500, 50, 50);
		level1Button.setPosition(165, 480);
		// level2-Link
		level2Button = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("levelButton/button-level-02-out.png")))));
		level2Button.setBounds((Pyramid.V_WIDTH / 2) - 100, 500, 50, 50);
		level2Button.setPosition(265, 480);
		// level3-Link
		level3Button = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("levelButton/button-level-03-out.png")))));
		level3Button.setBounds((Pyramid.V_WIDTH / 2) - 100, 500, 50, 50);
		level3Button.setPosition(365, 480);
		// level4-Link
		level4Button = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("levelButton/button-level-04-out.png")))));
		level4Button.setBounds((Pyramid.V_WIDTH / 2) - 100, 500, 50, 50);
		level4Button.setPosition(465, 480);
		// level5-Link
		level5Button = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("levelButton/button-level-05-out.png")))));
		level5Button.setBounds((Pyramid.V_WIDTH / 2) - 100, 500, 50, 50);
		level5Button.setPosition(565, 480);
		// level6-Link
		level6Button = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("levelButton/button-level-06-out.png")))));
		level6Button.setBounds((Pyramid.V_WIDTH / 2) - 100, 500, 50, 50);
		level6Button.setPosition(665, 480);
		// level7-Link
		level7Button = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("levelButton/button-level-07-out.png")))));
		level7Button.setBounds((Pyramid.V_WIDTH / 2) - 100, 500, 50, 50);
		level7Button.setPosition(765, 480);
		// level8-Link
		level8Button = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("levelButton/button-level-08-out.png")))));
		level8Button.setBounds((Pyramid.V_WIDTH / 2) - 100, 500, 50, 50);
		level8Button.setPosition(865, 480);
		// level9-Link
		level9Button = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("levelButton/button-level-09-out.png")))));
		level9Button.setBounds((Pyramid.V_WIDTH / 2) - 100, 500, 50, 50);
		level9Button.setPosition(965, 480);
		// level10-Link
		level10Button = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("levelButton/button-level-10-out.png")))));
		level10Button.setBounds((Pyramid.V_WIDTH / 2) - 100, 500, 50, 50);
		level10Button.setPosition(1065, 480);

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
	}

	@Override
	public void show() {
		sb = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		buttonStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);

		sb.begin();
		sb.draw(background, 0, 0, Pyramid.V_WIDTH, Pyramid.V_HEIGHT);
		sb.end();

		buttonStage.draw();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

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
