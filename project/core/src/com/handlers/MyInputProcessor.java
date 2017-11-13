package com.handlers;

import java.awt.RenderingHints.Key;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

public class MyInputProcessor extends InputAdapter{

	public boolean keyDown(int k) {
		
		if(k == Keys.Z) {
			MyInput.setKey(MyInput.BUTTON1, true);
		}
		
		if(k == Keys.X ) {
			MyInput.setKey(MyInput.BUTTON2, true);
		}
		
		if(	k == Keys.RIGHT) {	
			MyInput.setKey(MyInput.BUTTON3, true);
		}
		
		if (k == Keys.LEFT) {
			MyInput.setKey(MyInput.BUTTON4, true);
		}
		return true;
	}
	
	public boolean keyUp(int k) {
		if(k == Keys.Z) {
			MyInput.setKey(MyInput.BUTTON1, false);
		}
		
		if(k == Keys.X ) {
			MyInput.setKey(MyInput.BUTTON2, false);
		}
		
		
		if(	k == Keys.RIGHT) {	
			MyInput.setKey(MyInput.BUTTON3, false);
		}
		
		if (k == Keys.LEFT) {
			MyInput.setKey(MyInput.BUTTON4, false);
		}
		
		return true;
	}
}
