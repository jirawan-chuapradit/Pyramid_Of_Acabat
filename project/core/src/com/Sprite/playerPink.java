package com.Sprite;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.mygdx.game.PyramidOfAcabat;

public class playerPink extends Sprite {
	
	public World world;
	public Body b2body;
	
	public playerPink(World world) {
		this.world = world;
		definePleyerPink();
		
	}

	private void definePleyerPink() {
		
		BodyDef bdef = new BodyDef();
		bdef.position.set(32/PyramidOfAcabat.PPM, 32/PyramidOfAcabat.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(5/PyramidOfAcabat.PPM);
		
		fdef.shape = shape;
		b2body.createFixture(fdef);
	}

}
