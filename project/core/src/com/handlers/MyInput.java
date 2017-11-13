package com.handlers;

public class MyInput {
	
	public static boolean[] keys;
	public static boolean[] pkeys;
	
	public static final int NUM_KRYS = 2;
	public static final int BUTTON1 = 0;
	public static final int BUTTON2 = 1;
	
	static {
		keys = new boolean[NUM_KRYS];
		pkeys = new boolean[NUM_KRYS];
	}
	
	public static void update() {
		for(int i = 0; i < NUM_KRYS; i++) {
			pkeys[i] = keys[i];
		}
	}
	
	public static boolean setKey(int i, boolean b) {
		return keys[i] = b;
	}
	
	public static boolean isDown(int i) {
		return keys[i];
	}

	public static boolean isPreessed(int i) {
		return keys[i] && !pkeys[i];
	}
}
