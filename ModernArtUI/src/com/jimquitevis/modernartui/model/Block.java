package com.jimquitevis.modernartui.model;

import android.graphics.Color;

public class Block {
	private final int width;
	private final int height;
	private final int color;
	private final int finalColor;

	public Block(int width, int height, int color, int finalColor) {
		this.width = width;
		this.height = height;
		this.color = color;
		this.finalColor = finalColor;
	}
	
	public int getColorFromBit(int color, int mask, int shift) {
		return (color & mask) >> shift;
	}
	
	public int getColorForRate(int rate, int maxRate) {
		float position = (float)rate / (float)maxRate;
		
		int originalR = getColorFromBit(color, 0xff0000, 16);
		int originalG = getColorFromBit(color, 0x00ff00, 8);
		int originalB = getColorFromBit(color, 0x0000ff, 0);
		
		//Linearly determine the color based on the rate and max rate provided
		int r = (int)(originalR + (position * (getColorFromBit(finalColor, 0xff0000, 16) - originalR)));
		int g = (int)(originalG + (position * (getColorFromBit(finalColor, 0x00ff00, 8) - originalG)));
		int b = (int)(originalB + (position * (getColorFromBit(finalColor, 0x0000ff, 0) - originalB)));
		
		return Color.rgb(r, g, b);
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
	
	public int getColor() {
		return color;
	}
	
	public int getFinalColor() {
		return finalColor;
	}
}
