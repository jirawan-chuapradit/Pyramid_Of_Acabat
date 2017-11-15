package com.Sprites;

import com.Tools.B2WorldCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Pyramid;

public class PinkPlayer extends Sprite{
	public World world;
	public Body b2body;
	
	private B2WorldCreator b2WorldCreator;
	public int currentColorPink = 0;
	
	public PinkPlayer(World world) {
		this.world = world;
		definePinkPlayr();
		
	}

	private void definePinkPlayr() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(120/Pyramid.PPM,200/Pyramid.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(10/Pyramid.PPM);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
	}
	
	public void handleInput(float dt) {

		// control our player using inmudiate impulse 
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x <= 2) {
			b2body.applyLinearImpulse(new Vector2(0.1f, 0),  b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -2) {
			b2body.applyLinearImpulse(new Vector2(-0.1f, 0),  b2body.getWorldCenter(), true);
		}

		// switch
	}
}
