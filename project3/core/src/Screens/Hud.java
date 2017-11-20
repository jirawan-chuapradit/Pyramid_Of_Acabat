package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.Pyramid;

import Tools.WorldContactListener;


public class Hud{

	public Stage stage;
	private Viewport viewport;
	private Pyramid game;
	
	private Integer worldTimer;
	private float timeCount;
	protected static int health;
	
	private Stage buttonStage;
	private ImageButton clockButton;
	private ImageButton hpButton;
	
	
	private WorldContactListener worldContactListener;
	
	private float timeHealth;

	
	Label coundownLabel;
	Label scoreLabel;
	Label timeLabel;
	Label levelLabel;
	Label worldLabel;
	Label playerLabel;
	
	
	
	public Hud() {
		
		worldTimer = 120;
		timeCount = 0;
		health = 3;
		timeHealth = 0;
		
		buttonStage = new Stage();
		worldContactListener = new WorldContactListener();
		
//		setup the HUD viewport using a new camera seperate from our gamecam
//      define our stage using that viewport and our games spritebatch
		viewport = new FitViewport(Pyramid.V_WIDTH, Pyramid.V_HEIGHT, new OrthographicCamera());
		stage = new Stage( viewport);
		
		 //define a table used to organize our hud's labels
		Table table = new Table();
		//Top-Align table
		table.top();
		
        //make the table fill the entire stage
        table.setFillParent(true);

		game = new Pyramid();
	
		
	
		//define our labels using the String, and a Label style consisting of a font and color
		coundownLabel = new Label(String.format("%03d", worldTimer), new Label.LabelStyle(new BitmapFont(), Color.GOLDENROD));
		scoreLabel = new Label(String.format("%06d", health), new Label.LabelStyle(new BitmapFont(), Color.GOLDENROD));
		timeLabel = new Label("TIME", new Label.LabelStyle(new BitmapFont(), Color.GOLDENROD));
		levelLabel = new Label("Level " + PlayScreen.keep_count, new Label.LabelStyle(new BitmapFont(), Color.GOLDENROD));
		worldLabel = new Label("STAGE", new Label.LabelStyle(new BitmapFont(), Color.GOLDENROD));
		playerLabel = new Label("Health", new Label.LabelStyle(new BitmapFont(), Color.GOLDENROD));
		
//		 add our labels to our table, padding the top, and giving them all equal width with expandX
		table.add(playerLabel).expandX().padTop(10);
		table.add(worldLabel).expandX().padTop(10);
		table.add(timeLabel).expandX().padTop(10);
		
//	    add a second row to our table
		table.row();
		table.add().expandX();
		table.add(levelLabel).expandX();
		table.add(coundownLabel).expandX();
		
//		 add our table to the stage
		stage.addActor(table);
		

		
		// Create Icon
		// HP Button
		hpButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("StartGame/health3.png")))
						));
		hpButton.setBounds(500, 500, 400, 100);
		
		// Clock Icon
		clockButton = new ImageButton(new TextureRegionDrawable(
				new TextureRegion(new Texture(Gdx.files.internal("StartGame/clock.png")))
				));
		hpButton.setBounds(1000, 500, 400, 100);
		
		buttonStage.addActor(hpButton);
		buttonStage.addActor(clockButton);

		
	}

	public void update(float dt){
		timeCount += dt;
		if(timeCount >= 1) {
			setWorldTimer(worldTimer);
			coundownLabel.setText(String.format("%03d", worldTimer));
			timeCount = 0;
		}
		
		if(worldContactListener.isCheckHitHealth() == true && timeHealth >= 2) {
			setHealth(health);
			System.out.println(health);
			scoreLabel.setText(String.format("%06d", health));
			timeHealth = 0;	
			
		}
		else if(worldContactListener.isCheckHitHealth() == true && timeHealth <= 2) {
			timeHealth += dt;
		}
		else {
			timeHealth =  0;
		}
		
	}
	
	


	public Integer getWorldTimer() {
		return worldTimer;
	}

	public void setWorldTimer(Integer worldTimers) {
		
		this.worldTimer = worldTimers;
		worldTimer--;
	}

	public Integer getHealth() {
		return health;
	}

	public void setHealth(Integer healths) {
		this.health = healths;
		health--;
	}
	
	public void dispose() {
		
		game.dispose();
		stage.dispose();
		buttonStage.dispose();
		
		
	}

	
	
	
}
