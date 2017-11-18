package State;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
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

		background = new Texture("menu.jpg");
		

//		for (int j = 1; j <= 10; j++) {
//			if (j <= 5) {
//				levelButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(
//						new Texture(Gdx.files.internal("levelButton/button-level-0" + j + "-out.png")))));
//				levelButton.setBounds((Pyramid.V_WIDTH / 2) - (50 * j), 500, 50, 50);
//			}
//			else if(j > 5) {
//				
//			}
//		}
			
		level1Button = new ImageButton(
				new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("levelButton/button-level-01-out.png")))));
		level1Button.setBounds((Pyramid.V_WIDTH / 2) - 100, 500, 50, 50);

		
		level1Button.addListener(new ClickListener() {
			
			public void clicked(InputEvent event, float x, float y) {

				super.clicked(event, x, y);
//				game.setScreen(new LevelSelect(game));
				game.setScreen(new PlayScreen(game));
			}
		});

		buttonStage.addActor(level1Button);
//		buttonStage.addActor(level2Button);
//		buttonStage.addActor(level3Button);
//		buttonStage.addActor(level4Button);
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
		background.dispose();
	}

}
