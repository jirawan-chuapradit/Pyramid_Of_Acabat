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


public class Menu implements Screen{
	
	private Texture background;
	private Stage buttonStage;
	private ImageButton startButton;
	private ImageButton helpButton;
	private ImageButton exitButton;
	
	private OrthographicCamera gameCam;
	private Viewport gamePort;
	
	private Pyramid game;
	public SpriteBatch sb;
	public Menu(Pyramid gsm) {
		
		this.game = gsm;
		buttonStage = new Stage();
		
		gameCam = new OrthographicCamera();
		// create a FitViewport to maintain virtual aspect ratio despite screen
		gamePort = new FitViewport(Pyramid.V_WIDTH / Pyramid.PPM, Pyramid.V_HEIGHT / Pyramid.PPM, gameCam);

		Gdx.input.setInputProcessor(buttonStage);	
		
		background = new Texture("StartGame/GUI.png");
		
		// Create start Button
		startButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("StartGame/start.png")))
				));
		
		startButton.setBounds(100, 0, 240, 220);
		startButton.addListener(new ClickListener()   {
			
			public void clicked(InputEvent event  , float x , float y) {
				Pyramid.manager.get("sounds/button2.wav", Sound.class).play();
				super.clicked(event, x , y);
				game.setScreen(new LevelSelect(game));
			}
		});
		
		// Create Help Buutton
		helpButton = new ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("StartGame/help.png")))));
		helpButton.setBounds((Pyramid.V_WIDTH / 2) - 150 , 0, 240, 220);
	
		helpButton.addListener(new ClickListener()   {
			
			public void clicked(InputEvent event  , float x , float y) {
				Pyramid.manager.get("sounds/button2.wav", Sound.class).play();
				super.clicked(event, x , y);
				game.setScreen(new Help(game));
			}
		});
		
		// Create Exit Button
		 exitButton = new  ImageButton(new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("StartGame/Exit.png")))));
		 exitButton.setBounds((Pyramid.V_WIDTH / 2) + 200, 0, 240, 220);
		 exitButton.addListener(new ClickListener() {
	         
             public void clicked(InputEvent event, float x , float y) {
                  super.clicked(event, x, y);
                  Gdx.app.exit();
          
             }
           });
		 
		//Play music
			Pyramid.manager.get("music/music_start.ogg", Music.class).play();

			buttonStage.addActor(startButton);
			buttonStage.addActor(helpButton);
			buttonStage.addActor(exitButton);
	}



	@Override
	public void dispose() {
		buttonStage.dispose();
		sb.dispose();
		background.dispose();
		
	}

	@Override
	public void show() {
		
		sb = new SpriteBatch();
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		game.sb.setProjectionMatrix(gameCam.combined);

		
		buttonStage.getViewport().update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
		
		sb.begin();
		sb.draw(background, 0, 0, Pyramid.V_WIDTH, Pyramid.V_HEIGHT);
		sb.end();
		
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
}