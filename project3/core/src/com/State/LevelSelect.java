package com.State;

import com.Screens.PlayScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.handles.GameButton;
import com.handles.GameStateManager;
import com.mygdx.game.Pyramid;

public class LevelSelect extends GameState {
	
	private TextureRegion reg;
	
	private GameButton[][] buttons;
	
	public LevelSelect(GameStateManager gsm) {
		
		super(gsm);
		
		reg = new TextureRegion(Pyramid.res.getTexture("bgs"), 0, 0, 320, 240);
		
		TextureRegion buttonReg = new TextureRegion(Pyramid.res.getTexture("hud"), 0, 0, 32, 32);
		buttons = new GameButton[5][5];
		for(int row = 0; row < buttons.length; row++) {
			for(int col = 0; col < buttons[0].length; col++) {
				buttons[row][col] = new GameButton(buttonReg, 80 + col * 40, 200 - row * 40, null);
				buttons[row][col].setText(row * buttons[0].length + col + 1 + "");
			}
		}
		
	}
	
	public void handleInput() {
	}
	
	public void update(float dt) {
		
		handleInput();
		
		for(int row = 0; row < buttons.length; row++) {
			for(int col = 0; col < buttons[0].length; col++) {
				buttons[row][col].update(dt);
				if(buttons[row][col].isClicked()) {
//					PlayScreen.level = row * buttons[0].length + col + 1;
					Pyramid.res.getSound("levelselect").play();
					gsm.setState(GameStateManager.PLAY);
				}
			}
		}
		
	}
	
	public void render() {
		sb.begin();
		sb.draw(reg, 0, 0);
		sb.end();
		
		for(int row = 0; row < buttons.length; row++) {
			for(int col = 0; col < buttons[0].length; col++) {
				buttons[row][col].render(sb);
			}
		}
		
	}
	
	public void dispose() {
		// everything is in the resource manager com.neet.blockbunny.handlers.Content
	}
	
}
