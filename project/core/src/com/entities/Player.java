package com.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Game;

public class Player extends B2DSprite{
	
	private int numCrystals;
	private int totalCrystals;
	
	public Player(Body body) {
		
		super(body);
		
		Texture tex =  Game.res.getTexture("bluePlayer");
		TextureRegion[] sprites = TextureRegion.split(tex, 100, 100)[0];

		setAnimaiton(sprites, 1 / 12f);
		
	}
	
	public void collectCrystal() {
		numCrystals++;
	}
	
	public int getNumCrystals() {
		return numCrystals;
	}
	
	public void setTotalCrystals(int i) {
		totalCrystals = i;
	}
	
	public int getTotalCyrstals() {
		return totalCrystals;
	}
	
	
}
