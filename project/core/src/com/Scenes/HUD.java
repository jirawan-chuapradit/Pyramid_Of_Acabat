package com.Scenes;

import javax.swing.text.View;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.game.PyramidOfAcabat;

public class HUD {
	
	public Stage stage;
	private Viewport viewport;
	private Integer worldTimer;
	private float timeCount;
	private Integer score;
	
	Label countdownLabel;
	Label scoreLabel;
	Label timeLabel;
	Label leveLabel;
	Label worldLabel;
	Label PyramidLabel;
	
	public HUD(SpriteBatch sb) {
		worldTimer = 300;
		timeCount = 0;
		score = 0;
		
		viewport = new FitViewport(
				PyramidOfAcabat.V_WIDTH, 
				PyramidOfAcabat.V_HEIGHT, 
				new OrthographicCamera()
				);
		
		stage = new Stage(viewport,sb);
		
		Table table = new Table();
		table.top();
		table.setFillParent(true);
		
		countdownLabel = new Label(
				String.format("%03d", worldTimer),
				new Label.LabelStyle(new BitmapFont(), Color.WHITE)
				);
		scoreLabel = new Label(
				String.format("%06d", score),
				new Label.LabelStyle(new BitmapFont(), Color.WHITE)
				);
		timeLabel = new Label(
				"TIME",
				new Label.LabelStyle(new BitmapFont(), Color.WHITE)
				);
		leveLabel =  new Label(
				"Level-1",
				new Label.LabelStyle(new BitmapFont(), Color.WHITE)
				);
		 worldLabel =  new Label(
					"WORLD",
					new Label.LabelStyle(new BitmapFont(), Color.WHITE)
					);
		PyramidLabel =  new Label(
				"PYRAMID OF ACABAT",
				new Label.LabelStyle(new BitmapFont(), Color.WHITE)
				);
		
		table.add(PyramidLabel).expandX().padTop(10);
		table.add(worldLabel).expandX().padTop(10);
		table.add(timeLabel).expandX().padTop(10);
		table.row();
		table.add(scoreLabel).expandX();
		table.add(leveLabel).expandX();
		table.add(countdownLabel).expand();
		
		stage.addActor(table);		
	}
	
	
	
	

}