package com.jimquitevis.modernartui.model;

import java.util.List;
import java.util.Random;

import android.graphics.Color;

import com.google.common.collect.Lists;

public class BlocksModel {
	public static final int MAX_RECT_WIDTH = 100;
	public static final int MIN_RECT_WIDTH = 30;
	public static final int RECT_HEIGHT = 50;
	
	private final List<List<Block>> blocks;
	private final int canvasWidth;
	private final int canvasHeight;
	private final Random random = new Random(System.currentTimeMillis());

	private int getRandomIntBetween(int from, int to) {
		return random.nextInt((to - from) + 1) + from;
	}
	
	public BlocksModel(int canvasWidth, int canvasHeight) {
		this.canvasWidth = canvasWidth;
		this.canvasHeight = canvasHeight;
		
		blocks = Lists.newArrayList();
	}
	
	public void newSeed() {
		int widthCtr = 0;
		int heightCtr = 0;
		blocks.clear();
		
		while (heightCtr < canvasHeight) {
			List<Block> row = Lists.newArrayList();

			while (widthCtr < canvasWidth) {
				int nextWidth = getRandomIntBetween(MIN_RECT_WIDTH, MAX_RECT_WIDTH);
				
				int actualWidth = 0;
				if ((widthCtr + nextWidth) <= canvasWidth) {
					actualWidth = nextWidth;
				}
				else {
					actualWidth = canvasWidth - widthCtr;
				}
				
				widthCtr += actualWidth;

				Block block = null;
				
				//Give shades of gray a probability of appearing by 20% 
				if (getRandomIntBetween(0, 10) > 2) {
					//Produce "colored" blocks
					block = new Block(actualWidth, 
										(heightCtr + RECT_HEIGHT) <= canvasHeight ? RECT_HEIGHT : canvasHeight - heightCtr, 
										Color.rgb(getRandomIntBetween(0, 255), 
													getRandomIntBetween(0, 255), 
													getRandomIntBetween(0, 255)),
										Color.rgb(getRandomIntBetween(0, 255), 
													getRandomIntBetween(0, 255), 
													getRandomIntBetween(0, 255)));
				}
				else {
					//Produce "gray" blocks
					int randomColor = getRandomIntBetween(0, 255);
					block = new Block(actualWidth, 
						(heightCtr + RECT_HEIGHT) <= canvasHeight ? RECT_HEIGHT : canvasHeight - heightCtr, 
						Color.rgb(randomColor, randomColor, randomColor),
						Color.rgb(randomColor, randomColor, randomColor));
				}
				
				row.add(block);
			}
			
			heightCtr += RECT_HEIGHT;
			widthCtr = 0;

			blocks.add(row);
		}
	}
	
	public int getWidth() {
		return canvasWidth;
	}
	
	public int getHeight() {
		return canvasHeight;
	}
	
	public List<List<Block>> getBlocks() {
		return blocks;
	}
}
