
package com.Sprite;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.PyramidOfAcabat;

public class playerBlue extends Sprite {

	public enum State {
		FALLING, JUMPING, STANDING, RUNNING
	};

	public State currentState;
	public State preState;

	public World world;
	public Body b2body;

	private Animation bluePlayerRun;
	private Animation bluePlayerJump;
	private boolean runningRight;
	private float stateTimer;

	public playerBlue(World world) {
			this.world = world;
			currentState = State.STANDING;
			preState = State.STANDING;
			stateTimer = 0;
			runningRight  = true;
			definePleyerPink();
			
			Array<TextureRegion> frames = new Array<TextureRegion>();
			for(int i =1; i < 4; i++) {
				frames.add(new TextureRegion(getTexture(), i * 16, 0, 16,16));
				bluePlayerRun = new Animation(0.1f, frames);
				frames.clear();
				
				
			}
			
		}

	private void definePleyerPink() {

		BodyDef bdef = new BodyDef();
		bdef.position.set(32 / PyramidOfAcabat.PPM, 32 / PyramidOfAcabat.PPM);
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		CircleShape shape = new CircleShape();
		shape.setRadius(5 / PyramidOfAcabat.PPM);

		fdef.shape = shape;
		b2body.createFixture(fdef);
	}

}
