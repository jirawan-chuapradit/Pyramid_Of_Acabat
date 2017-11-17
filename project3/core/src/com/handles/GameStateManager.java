package com.handles;

import java.util.Stack;

import com.State.GameState;
import com.State.LevelSelect;
import com.State.Menu;
import com.mygdx.game.Pyramid;



public class GameStateManager {

private Pyramid game;
	
	private Stack<GameState> gameStates;
	
	public static final int MENU = 83774392;
	public static final int PLAY = 388031654;
	public static final int LEVEL_SELECT = -9238732;
	
	public GameStateManager(Pyramid game) {
		this.game = game;
		gameStates = new Stack<GameState>();
//		pushState(MENU);
	}
	
	public void update(float dt) {
//		gameStates.peek().update(dt);
	}
	
	public void render() {
//		gameStates.peek().render();
	}
	
	public Pyramid game() { return game; }
	
	
	private GameState getState(int state) {
		if(state == MENU) return new Menu(this);
//		if(state == PLAY) return new Play(this);
		if(state == LEVEL_SELECT) return new LevelSelect(this);
		return null;
	}
	
	public void setState(int state) {
		popState();
		pushState(state);
	}
	
	public void pushState(int state) {
		gameStates.push(getState(state));
	}
	
	public void popState() {
		GameState g = gameStates.pop();
		g.dispose();
	}
}
