package com.Sprites;

<<<<<<< HEAD
<<<<<<< HEAD
import java.awt.image.BufferedImage;

import com.Screens.PlayScreen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
=======
=======
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
import com.Tools.B2WorldCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
<<<<<<< HEAD
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
=======
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
<<<<<<< HEAD
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
=======
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
>>>>>>> ่JugJig
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.Pyramid;

public class BluePlayer extends Sprite {
	public World world;
	public Body b2body;
<<<<<<< HEAD
<<<<<<< HEAD
	private TextureRegion bluePlayerStand;

	public BluePlayer(World world, PlayScreen screen) {
		super(screen.getAtlas().findRegion("Walk1"));
=======
=======
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
	private B2WorldCreator b2WorldCreator;
	public int currentColorBlue = 1;
	
	public BluePlayer(World world) {
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
		this.world = world;
		defineBluePlayer();
		bluePlayerStand = new TextureRegion(getTexture(), 0, 0, 80, 80);
		setBounds(0, 0, 80 / Pyramid.PPM, 80 / Pyramid.PPM);
		setRegion(bluePlayerStand);
	}
<<<<<<< HEAD

	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 5);
	}
<<<<<<< HEAD

	public void defineBluePlayer() {
		BodyDef bdef = new BodyDef();
		bdef.position.set(120 / Pyramid.PPM, 500 / Pyramid.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);

=======
	
	private void defineBluePlayer() {
		BodyDef bdef = new BodyDef();
=======
	
	private void defineBluePlayer() {
		BodyDef bdef = new BodyDef();
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
		bdef.position.set(340/Pyramid.PPM,200/Pyramid.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
=======
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(10 / Pyramid.PPM);

		fdef.shape = shape;
		b2body.createFixture(fdef);
=======

		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(20/Pyramid.PPM, 20/Pyramid.PPM);
//		CircleShape shape = new CircleShape();
//		shape.setRadius(15/Pyramid.PPM);
		
		fdef.shape = shape;
		fdef.filter.groupIndex = -1;
		Fixture body  = b2body.createFixture(fdef);
		System.out.println(body.getFilterData().groupIndex);
				
>>>>>>> ่JugJig
<<<<<<< HEAD
=======
	}
	
	public void handleInput(float dt) {

<<<<<<< HEAD
		// control our player using inmudiate impulse 
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
=======
		float currentY = b2body.getLinearVelocity().y;
		
		// control our player using inmudiate impulse 
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && currentY% 4 == 0) {
			b2body.applyLinearImpulse(new Vector2(0, 5f), b2body.getWorldCenter(), true);
>>>>>>> ่JugJig
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x <= 2) {
			b2body.applyLinearImpulse(new Vector2(0.1f, 0),  b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -2) {
			b2body.applyLinearImpulse(new Vector2(-0.1f, 0),  b2body.getWorldCenter(), true);
		}
		
<<<<<<< HEAD
		// switch
		
		
=======
		
	}
	
	public void switchTypePlayer() {
		
		if(b2body.getType() == BodyType.StaticBody) {
			b2body.setType(BodyType.DynamicBody);
		}
		else if (b2body.getType() == BodyType.DynamicBody) {
			b2body.setType(BodyType.StaticBody);
		}
>>>>>>> ่JugJig
>>>>>>> d452d3d1a216d7e9f1846da044acae02b61506b9
	}
	
	public void handleInput(float dt) {

<<<<<<< HEAD
		// control our player using inmudiate impulse 
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
=======
		float currentY = b2body.getLinearVelocity().y;
		
		// control our player using inmudiate impulse 
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && currentY% 4 == 0) {
			b2body.applyLinearImpulse(new Vector2(0, 5f), b2body.getWorldCenter(), true);
>>>>>>> ่JugJig
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x <= 2) {
			b2body.applyLinearImpulse(new Vector2(0.1f, 0),  b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -2) {
			b2body.applyLinearImpulse(new Vector2(-0.1f, 0),  b2body.getWorldCenter(), true);
		}
		
<<<<<<< HEAD
		// switch
		
		
=======
		
	}
	
	public void switchTypePlayer() {
		
		if(b2body.getType() == BodyType.StaticBody) {
			b2body.setType(BodyType.DynamicBody);
		}
		else if (b2body.getType() == BodyType.DynamicBody) {
			b2body.setType(BodyType.StaticBody);
		}
>>>>>>> ่JugJig
	}
}
