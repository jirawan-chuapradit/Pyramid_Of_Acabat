package com.Sprites;

import javax.print.DocFlavor.STRING;

import com.Screens.PlayScreen;
import com.Tools.B2WorldCreator;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ai.fsm.State;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Pyramid;

public class PinkPlayer extends Sprite {
	public enum State {
		STANDING, RUNNING
	};

	public State currentState;
	public State previousState;
	public World world;
	public Body b2body;
	private Animation pinkPlayerJump;
	private Animation pinkPlayerRun;
	private float stateTimer;
	private boolean runningRight;
	private TextureRegion pinkPlayerStand;
	
	private B2WorldCreator b2WorldCreator;

	public int currentColorPink = 0;

	public PinkPlayer(World world, PlayScreen screen) {
		super(screen.getAtlas().findRegion("pinkPlayer"));
		this.world = world;
		

		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;

		Array<TextureRegion> frames = new Array<TextureRegion>();
		for (int i = 1; i < 7; i++) {
			frames.add(new TextureRegion(getTexture(),(i * 55), 524, 55, 92));
		}
		pinkPlayerRun = new Animation(1f, frames);
		frames.clear();

		pinkPlayerStand = new TextureRegion(getTexture(), 110, 226, 55, 92);
		definePinkPlayr();
		
		setBounds(0, 0, 50 / Pyramid.PPM, 65 / Pyramid.PPM);
		setRegion(pinkPlayerStand);
		
		
//		pinkPlayerStand = new TextureRegion(getTexture(), 0, 0, 40, 40);
//		setBounds(0, 0, 40 / Pyramid.PPM, 40 / Pyramid.PPM);
//		setRegion(pinkPlayerStand);
	}

	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 3);
		setRegion(getFrame(dt));
	}

	public TextureRegion getFrame(float dt) {
		currentState = getState();

		TextureRegion region;
		switch (currentState) {
			case RUNNING:
				region = (TextureRegion) pinkPlayerRun.getKeyFrame(stateTimer, true);
				break;
			case STANDING:
				default:
					region = pinkPlayerStand;
					break;
		}
		if (b2body.getLinearVelocity().x < 0 || !(runningRight) && !region.isFlipX()) {
			region.flip(true, false);
			runningRight = false;
		}
		else if (b2body.getLinearVelocity().x > 0 || !(runningRight) && !region.isFlipX()) {
			region.flip(true, false);
			runningRight = true;
		}
		
		stateTimer = currentState == previousState ? stateTimer + dt : 0;
		previousState = currentState;
		return region;
	}

	public State getState() {
		if (b2body.getLinearVelocity().x != 0)
			return State.RUNNING;
		else
			return State.STANDING;
	}
	
	private void definePinkPlayr() {

		BodyDef bdef = new BodyDef();
		bdef.position.set(30 / Pyramid.PPM, 400 / Pyramid.PPM); // Set new position
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);

		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(10 / Pyramid.PPM, 15 / Pyramid.PPM);

		fdef.shape = shape;
		fdef.filter.groupIndex = -2;
		b2body.createFixture(fdef);

//		Array<TextureRegion> framePink = new Array<TextureRegion>();
//		for (int i = 1; i < 3; i++) {
//			framePink.add(new TextureRegion(new Texture(Gdx.files.internal("pinkPlayer" + i + ".png"))));
//		}
//
//		animation = new Animation<TextureRegion>(0.1f, framePink, Animation.PlayMode.LOOP_PINGPONG);

	}

	public void handleInput(float dt) {

		float currentY = b2body.getLinearVelocity().y;
		// control our player using inmudiate impulse
		if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && currentY % 4 == 0) {

			b2body.applyLinearImpulse(new Vector2(0, 4f), b2body.getWorldCenter(), true);
		}

		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x <= 2) {
			b2body.applyLinearImpulse(new Vector2(0.1f, 0), b2body.getWorldCenter(), true);
		}
		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -2) {
			b2body.applyLinearImpulse(new Vector2(-0.1f, 0), b2body.getWorldCenter(), true);
		}

		// switch
	}

	public void switchTypePlayer() {

		if (b2body.getType() == BodyType.StaticBody) {
			b2body.setType(BodyType.DynamicBody);
		} else if (b2body.getType() == BodyType.StaticBody) {
			b2body.setType(BodyType.StaticBody);
		}
	}
}
