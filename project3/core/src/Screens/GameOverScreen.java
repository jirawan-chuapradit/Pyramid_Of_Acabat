package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.mygdx.game.Pyramid;

import State.LevelSelect;

public class GameOverScreen implements Screen {

	private Texture background;
	private SpriteBatch sb;
	private Pyramid game;
	
	private Stage buttonStage;
	
	private ImageButton NextButton;

	public GameOverScreen(Pyramid gsm) {
		this.game = gsm;
		
		buttonStage = new Stage();
		
		Gdx.input.setInputProcessor(buttonStage);
		
		background = new Texture("GameOver/gameover.png");
		
		NextButton = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("StartGame/right.png")))));
		NextButton.setBounds((Pyramid.V_WIDTH / 2) - 100, 100, 200, 280);

		
		NextButton.addListener(new ClickListener() {
			
			public void clicked(InputEvent event, float x, float y) {

		
				super.clicked(event, x, y);
				game.setScreen(new LevelSelect(game));
			}
		});

		buttonStage.addActor(NextButton);
		

	}

	@Override
	public void show() {
		sb = new SpriteBatch();		
	}

	@Override
	public void render(float delta) {
		sb.begin();
		sb.draw(background, 0, 0, Pyramid.V_WIDTH, Pyramid.V_HEIGHT);
		sb.end();
		
		buttonStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
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
		buttonStage.dispose();
		background.dispose();		
	}
}
