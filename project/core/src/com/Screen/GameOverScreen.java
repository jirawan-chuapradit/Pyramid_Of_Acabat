package com.Screen;

import com.brentaureli.mariobros.MarioBros;
import com.brentaureli.mariobros.Screens.BitmapFont;
import com.brentaureli.mariobros.Screens.FitViewport;
import com.brentaureli.mariobros.Screens.Game;
import com.brentaureli.mariobros.Screens.Label;
import com.brentaureli.mariobros.Screens.OrthographicCamera;
import com.brentaureli.mariobros.Screens.PlayScreen;
import com.brentaureli.mariobros.Screens.Stage;
import com.brentaureli.mariobros.Screens.Table;
import com.brentaureli.mariobros.Screens.Viewport;

public class GameOverScreen {

	  private Viewport viewport;
	    private Stage stage;

	    private Game game;

	    public GameOverScreen(Game game){
	        this.game = game;
	        viewport = new FitViewport(MarioBros.V_WIDTH, MarioBros.V_HEIGHT, new OrthographicCamera());
	        stage = new Stage(viewport, ((MarioBros) game).batch);

	        Label.LabelStyle font = new Label.LabelStyle(new BitmapFont(), Color.WHITE);

	        Table table = new Table();
	        table.center();
	        table.setFillParent(true);

	        Label gameOverLabel = new Label("GAME OVER", font);
	        Label playAgainLabel = new Label("Click to Play Again", font);

	        table.add(gameOverLabel).expandX();
	        table.row();
	        table.add(playAgainLabel).expandX().padTop(10f);

	        stage.addActor(table);
	    }

	    @Override
	    public void show() {

	    }

	    @Override
	    public void render(float delta) {
	        if(Gdx.input.justTouched()) {
	            game.setScreen(new PlayScreen((MarioBros) game));
	            dispose();
	        }
	        Gdx.gl.glClearColor(0, 0, 0, 1);
	        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	        stage.draw();
	    }

	    @Override
	    public void resize(int width, int height) {

	    }

	    @Override
	    public void pause() {

	    }

	    @Override
	    public void resume() {

	    }

	    @Override
	    public void hide() {

	    }

	    @Override
	    public void dispose() {
	        stage.dispose();
	    }
	
}
