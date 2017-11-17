package com.Sprites;

import com.Tools.B2WorldCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
<<<<<<< HEAD
import com.badlogic.gdx.graphics.g2d.Sprite;
=======
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
>>>>>>> ่JugJig
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
<<<<<<< HEAD
=======
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
>>>>>>> ่JugJig
import com.mygdx.game.Pyramid;

public class PinkPlayer extends Sprite{
	public World world;
	public Body b2body;
	
	private B2WorldCreator b2WorldCreator;
<<<<<<< HEAD
=======
	
	private Animation animation;
>>>>>>> ่JugJig
	public int currentColorPink = 0;
	
	public PinkPlayer(World world) {
		this.world = world;
		definePinkPlayr();
		
	}

	private void definePinkPlayr() {
<<<<<<< HEAD
=======
		
>>>>>>> ่JugJig
		BodyDef bdef = new BodyDef();
		bdef.position.set(120/Pyramid.PPM,200/Pyramid.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
<<<<<<< HEAD
		shape.setRadius(10/Pyramid.PPM);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
=======
		shape.setRadius(15/Pyramid.PPM);
		
		fdef.shape = shape;
		fdef.filter.groupIndex = -2;
		b2body.createFixture(fdef);
		
		Array<TextureRegion> framePink = new Array<TextureRegion>();
		for(int i = 1; i < 3; i++) {
			framePink.add(new TextureRegion(new Texture(Gdx.files.internal("pinkPlayer" + i + ".png"))));
		}
		
		animation = new Animation<TextureRegion>(0.1f, framePink,Animation.PlayMode.LOOP_PINGPONG);
		
		
>>>>>>> ่JugJig
	}
	
	public void handleInput(float dt) {

<<<<<<< HEAD
		// control our player using inmudiate impulse 
		if(Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
			b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
=======
		float currentY = b2body.getLinearVelocity().y;
		// control our player using inmudiate impulse 
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && currentY%4 == 0) {
			
			b2body.applyLinearImpulse(new Vector2(0, 5f), b2body.getWorldCenter(), true);
>>>>>>> ่JugJig
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x <= 2) {
			b2body.applyLinearImpulse(new Vector2(0.1f, 0),  b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -2) {
			b2body.applyLinearImpulse(new Vector2(-0.1f, 0),  b2body.getWorldCenter(), true);
		}

		// switch
	}
<<<<<<< HEAD
=======
	
	public TextureRegion getFramePink(float dt) {
		TextureRegion region;
		region = (TextureRegion) animation.getKeyFrame(dt, false);
		return region;
	}
	
	public void switchTypePlayer() {
		
		if(b2body.getType() == BodyType.StaticBody) {
			b2body.setType(BodyType.DynamicBody);
		}
		else if (b2body.getType() == BodyType.StaticBody) {
			b2body.setType(BodyType.StaticBody);
		}
	}
>>>>>>> ่JugJig
}
