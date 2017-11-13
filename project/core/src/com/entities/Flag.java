package com.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.mygdx.game.Game;

public class Flag extends B2DSprite {
	
	public Flag(Body body) {
		
		super(body);
		
		Texture tex = Game.res.getTexture("Flag");
//		TextureRegion[] sprites = TextureRegion.split(tex, 16, 16)[0];
		
//		setAnimaiton(sprites, 1/12f);
		
		
	}

}
