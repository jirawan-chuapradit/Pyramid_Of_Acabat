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

	
	
	
	

//	private Menu menu;
	
	private Pyramid game;
	private Menu menu;
	public SpriteBatch sb;

	public LevelSelect(Pyramid p) {

		this.game = p;
		buttonStage = new Stage();
		
		Gdx.input.setInputProcessor(buttonStage);

		background = new Texture("StartGame/levelSelect.png");
		
			
		level1Button = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/button-level-01-out.png")))));
		level1Button.setBounds((Pyramid.V_WIDTH / 2) - 100, 500, 50, 50);

		
		level1Button.addListener(new ClickListener() {
			
			public void clicked(InputEvent event, float x, float y) {
				//Stop music
				Pyramid.manager.get("music/music3.ogg", Music.class).stop();
				super.clicked(event, x, y);
				
				Pyramid.manager.get("sounds/button1.wav", Sound.class).play();
				game.setScreen(new PlayScreen(game));
			}
		});

		buttonStage.addActor(level1Button);

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
