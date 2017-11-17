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
//import com.badlogic.gdx.scenes.scene2d.ui.Button;

public class Menu implements Screen{
	


	private Texture background;
	private Stage buttonStage;
	private ImageButton playButton;
	final Pyramid game;
	SpriteBatch sb;
	public Menu(final Pyramid gsm) {
		
		
		this.game = gsm;
		buttonStage = new Stage();
		Gdx.input.setInputProcessor(buttonStage);	
		background = new Texture("menu.jpg");
		playButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("playButton.png")))));
		playButton.setBounds((Pyramid.V_WIDTH/2) - 50, 100, 100, 100);
		playButton.addListener(new ClickListener()   {
			
			public void clicked(InputEvent event  , float x , float y) {
				
				super.clicked(event, x , y);
				game.setScreen(new PlayScreen(game));
			}
		});
		
		buttonStage.addActor(playButton);
		
	}





	@Override
	public void dispose() {
		background.dispose();
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
}