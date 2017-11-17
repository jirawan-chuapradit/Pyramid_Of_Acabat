package com.Sprites;

import com.Tools.B2WorldCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Pyramid;

public class BluePlayer extends Sprite{
	
	public World world;
	public Body b2body;
	private B2WorldCreator b2WorldCreator;
	public int currentColorBlue = 1;
	
	public BluePlayer(World world) {
		this.world = world;
		defineBluePlayer();
	}
	
	private void defineBluePlayer() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(1250/Pyramid.PPM,400/Pyramid.PPM); //Set new position
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		

		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(10/Pyramid.PPM, 15/Pyramid.PPM);

		
		fdef.shape = shape;
		fdef.filter.groupIndex = -1;
		Fixture body  = b2body.createFixture(fdef);
		System.out.println(body.getFilterData().groupIndex);
				
	}
	
	public void handleInput(float dt) {

		float currentY = b2body.getLinearVelocity().y;
		
		// control our player using inmudiate impulse 
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && currentY% 4 == 0) {
			b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x <= 2) {
			b2body.applyLinearImpulse(new Vector2(0.1f, 0),  b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -2) {
			b2body.applyLinearImpulse(new Vector2(-0.1f, 0),  b2body.getWorldCenter(), true);
		}
		
		
	}
	
	public void switchTypePlayer() {
		
		if(b2body.getType() == BodyType.StaticBody) {
			b2body.setType(BodyType.DynamicBody);
		}
		else if (b2body.getType() == BodyType.DynamicBody) {
			b2body.setType(BodyType.StaticBody);
		}
	}

}
