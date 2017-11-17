package com.State;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.handles.Background;
import com.handles.GameButton;
import com.handles.GameStateManager;
import com.mygdx.game.Pyramid;


public class Menu extends GameState {

private boolean debug = false;
	
	private Background bg;
//	private Animation animation;
	private GameButton playButton;
	
	private World world;
	private Box2DDebugRenderer b2dRenderer;
	
	
	
	public Menu(GameStateManager gsm) {
		super(gsm);
		
		Texture tex = Pyramid.res.getTexture("menu");
		bg = new Background(new TextureRegion(tex));
		bg.setVector(-20, 0);
		
		tex = Pyramid.res.getTexture("hud");
		playButton = new GameButton(new TextureRegion(tex, 0, 34, 58, 27), 160, 100, null);

		world = new World(new Vector2(0, -9.8f * 5), true);
		//world = new World(new Vector2(0, 0), true);
		b2dRenderer = new Box2DDebugRenderer();
		
	}

	@Override
	public void handleInput() {
		
		// mouse/touch input
		if(playButton.isClicked()) {
			Pyramid.res.getSound("crystal").play();
			gsm.setState(GameStateManager.LEVEL_SELECT);
		}
		
		
	}

	@Override
	public void update(float dt) {

		handleInput();
		
		world.step(dt / 5, 8, 3);
		
		bg.update(dt);
		
		playButton.update(dt);
		
	}

	@Override
	public void render() {
		
		// draw background
		bg.render(sb);
		
		// draw button
		playButton.render(sb);
		
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	

}
