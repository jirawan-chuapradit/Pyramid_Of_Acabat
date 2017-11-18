package Sprites;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.Pyramid;

import Screens.PlayScreen;
import Tools.B2WorldCreator;

public class PinkPlayer extends Sprite{
	public World world;
	public Body b2body;
	
	private B2WorldCreator b2WorldCreator;
	
	public int currentColorPink = 0;
	
	// animation --- beer
	public State currentState;
	public State previousState;
	private Animation pinkPlayerRunRight;
	private float stateTimer;
	private boolean runningRight;
	private boolean stateRunRight;
	private Animation pinkPlayerStand;
	private	Array<TextureRegion> playerStandRight = new Array<TextureRegion>();
	private	Array<TextureRegion> playerStandLeft = new Array<TextureRegion>();
	private Array<TextureRegion> playerRunRight = new Array<TextureRegion>();
	private Array<TextureRegion> playerRunLeft = new Array<TextureRegion>();
	
	
	public PinkPlayer(World world,  PlayScreen screen) {
		
		super(screen.getAtlas().findRegion("pinkPlayer"));
		this.world = world;
		
		currentState = State.STANDING;
		previousState = State.STANDING;
		stateTimer = 0;
		runningRight = true;
		stateRunRight = true;

		state();
		
		definePinkPlayer();
		
		setBounds(0, 0, 50 / Pyramid.PPM, 65 / Pyramid.PPM);	
	}
	
	public enum State {
		STANDING, RUNNING
	};
	
	public void update(float dt) {
		setPosition(b2body.getPosition().x - getWidth() / 2, b2body.getPosition().y - getHeight() / 3);
		setRegion(getFrame(dt));
	}

	public void state() {
		if (runningRight == true) {
			for (int i = 2; i < 4; i++) {
				playerStandRight.add(new TextureRegion(getTexture(),(i * 54), 226, 55, 92));
			}
			pinkPlayerStand = new Animation(1f, playerStandRight);
			playerStandRight.clear();
			if (stateRunRight==true) {
				for (int i = 1; i < 7; i++) {
					playerRunRight.add(new TextureRegion(getTexture(),(i * 54), 521, 55, 92));
				}
				pinkPlayerRunRight = new Animation(0.1f, playerRunRight);
				playerRunRight.clear();
			}
		}
		else {
			for (int i = 0; i < 2; i++) {
				playerStandLeft.add(new TextureRegion(getTexture(),(i * 54), 226, 55, 92));
			}
			pinkPlayerStand = new Animation(1f, playerStandLeft);
			playerStandLeft.clear();
			runningRight = true;
			if (stateRunRight==false) {
				for (int i = 1; i < 7; i++) {
					playerRunLeft.add(new TextureRegion(getTexture(),(i * 54), 422, 55, 92));
				}
				pinkPlayerRunRight = new Animation(0.1f, playerRunLeft);
				playerRunRight.clear();
			}
			stateRunRight = true;
		}
	}
	
	public TextureRegion getFrame(float dt) {
		currentState = getState();

		TextureRegion region;
		switch (currentState) {
			case RUNNING:
				region = (TextureRegion) pinkPlayerRunRight.getKeyFrame(stateTimer, true);
				break;
			case STANDING:
				default:
					region = (TextureRegion) pinkPlayerStand.getKeyFrame(stateTimer, true);
					break;
		}
		if (b2body.getLinearVelocity().x < 0 || !(runningRight) || !(stateRunRight)) {
//			region.flip(true, false);
			runningRight = false;
			stateRunRight = false;
			state();
		}
		else if (b2body.getLinearVelocity().x > 0) {
//			region.flip(true, false);
			runningRight = true;
			stateRunRight = true;
			state();
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

	private void definePinkPlayer() {
		
		BodyDef bdef = new BodyDef();
		bdef.position.set(30/Pyramid.PPM,700/Pyramid.PPM); //Set new position
		bdef.type = BodyDef.BodyType.DynamicBody;
		b2body = world.createBody(bdef);
		
		// create RecPink Player
		FixtureDef fdef = new FixtureDef();
		PolygonShape shape = new PolygonShape();
		shape.setAsBox(15 / Pyramid.PPM, 15 / Pyramid.PPM);
		fdef.shape = shape;
		fdef.filter.groupIndex = -2;
		b2body.createFixture(fdef);
		
//		Array<TextureRegion> framePink = new Array<TextureRegion>();
//		for(int i = 1; i < 3; i++) {
//			framePink.add(new TextureRegion(new Texture(Gdx.files.internal("pinkPlayer" + i + ".png"))));
//		}
//		
//		animation = new Animation<TextureRegion>(0.1f, framePink,Animation.PlayMode.LOOP_PINGPONG);
		
		
	}

	public void handleInput(float dt) {

		float currentY = b2body.getLinearVelocity().y;
		// control our player using inmudiate impulse 
		if(Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && currentY%4 == 0) {
			Pyramid.manager.get("sounds/jump.wav", Sound.class).play();
			b2body.applyLinearImpulse(new Vector2(0, 5f), b2body.getWorldCenter(), true);
		}
		
		if(Gdx.input.isKeyPressed(Input.Keys.RIGHT) && b2body.getLinearVelocity().x <= 2) {
			b2body.applyLinearImpulse(new Vector2(0.1f, 0),  b2body.getWorldCenter(), true);
		}
		if(Gdx.input.isKeyPressed(Input.Keys.LEFT) && b2body.getLinearVelocity().x >= -2) {
			b2body.applyLinearImpulse(new Vector2(-0.1f, 0),  b2body.getWorldCenter(), true);
		}

		// switch
	}
	
	public void switchTypePlayer() {
		
		if(b2body.getType() == BodyType.StaticBody) {
			b2body.setType(BodyType.DynamicBody);
		}
		else if (b2body.getType() == BodyType.StaticBody) {
			b2body.setType(BodyType.StaticBody);
		}
	}
}
