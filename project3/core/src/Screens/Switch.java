package Screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.mygdx.game.Pyramid;

public class Switch implements Screen {
	private boolean keep_enableSwitchColor;
	public Switch() {
		keep_enableSwitchColor = PlayScreen.enableSwitchColor;
	}
	
	public void switchPinkButton() {
		PlayScreen.switchPinkButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Pyramid.manager.get("sounds/button1.wav", Sound.class).play();
				PlayScreen.b2WorldCreator.switchColor();
			}
		});
	}
	
	public void switchBlueButton() {
		PlayScreen.switchBlueButton.addListener(new ClickListener() {
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Pyramid.manager.get("sounds/button1.wav", Sound.class).play();
				PlayScreen.b2WorldCreator.switchColor();
			}
		});
	}
	
	public void update(float dt) {
		if (keep_enableSwitchColor) {
			if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT) || Gdx.input.isKeyPressed(Input.Keys.SHIFT_RIGHT)) {

				Pyramid.manager.get("sounds/shift.wav", Sound.class).play();

				PlayScreen.b2WorldCreator.switchColor();

				keep_enableSwitchColor = false;
			}
		} else {
			if (PlayScreen.time >= 2) {
				keep_enableSwitchColor = true;
				PlayScreen.time = 0;
			} else {
				PlayScreen.time += dt;
			}
		}
		// handle user input first
		if (PlayScreen.b2WorldCreator.getCurrentColor() != 0) {
				PlayScreen.pinkPlayer.handleInput(dt);
		} else {
				PlayScreen.bluePlayer.handleInput(dt);
		}
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		update(delta);
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
	}
	
}
