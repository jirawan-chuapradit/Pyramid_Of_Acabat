package com.handles;

import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class Content {
	
	private HashMap<String, Texture> textures;
	private HashMap<String, Music> music;
	private HashMap<String, Sound> sounds;
	
	public Content() {
		textures = new HashMap<String, Texture>();
		music = new HashMap<String, Music>();
		sounds = new HashMap<String, Sound>();
	}
	
	public void loadTexture(String path) {
		int slashIndex = path.lastIndexOf('/');
		String key;
		if(slashIndex == -1) {
			key = path.substring(0, path.lastIndexOf('.'));
		}
		else {
			key = path.substring(slashIndex + 1, path.lastIndexOf('.'));
		}
		loadTexture(path, key);
	}
	
	/* Texture */
	private void loadTexture(String path, String key) {
		Texture tex = new Texture(Gdx.files.internal(path));
		textures.put(key, tex);	
	}
	
	public Texture getTexture(String key) {
		return textures.get(key);
	}
	public void removeTexture(String key) {
		Texture tex = textures.get(key);
		if(tex != null) {
			textures.remove(key);
			tex.dispose();
		}
	}
	
	/* Music */
	public void loadMusic(String path) {
		int slashIndex = path.lastIndexOf('/');
		String key;
		if(slashIndex == -1) {
			key = path.substring(0, path.lastIndexOf('.'));
		}
		else {
			key = path.substring(slashIndex + 1, path.lastIndexOf('.'));
		}
		loadMusic(path, key);
	}
	public void loadMusic(String path, String key) {
		Music m = Gdx.audio.newMusic(Gdx.files.internal(path));
		music.put(key, m);
	}
	public Music getMusic(String key) {
		return music.get(key);
	}
	public void removeMusic(String key) {
		Music m = music.get(key);
		if(m != null) {
			music.remove(key);
			m.dispose();
		}
	}
	
	/* SFX */
	public void loadSound(String path) {
		int slashIndex = path.lastIndexOf('/');
		String key;
		if(slashIndex == -1) {
			key = path.substring(0, path.lastIndexOf('.'));
		}
		else {
			key = path.substring(slashIndex + 1, path.lastIndexOf('.'));
		}
		loadSound(path, key);
	}
	public void loadSound(String path, String key) {
		Sound sound = Gdx.audio.newSound(Gdx.files.internal(path));
		sounds.put(key, sound);
	}
	public Sound getSound(String key) {
		return sounds.get(key);
	}
	public void removeSound(String key) {
		Sound sound = sounds.get(key);
		if(sound != null) {
			sounds.remove(key);
			sound.dispose();
		}
	}
	
	/* other */
	public void removeAll() {
		
		for(Object x : textures.values()) {
			Texture tex = (Texture) x;
			tex.dispose();
		}
		textures.clear();
		for(Object x : music.values()) {
			Music music = (Music) x;
			music.dispose();
		}
		music.clear();
		for(Object x : sounds.values()) {
			Sound sound = (Sound) x;
			sound.dispose();
		}
		sounds.clear();
	}
	

}