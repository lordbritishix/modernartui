package com.jimquitevis.modernartui.test;

import java.util.List;

import android.graphics.Color;
import android.test.AndroidTestCase;

import com.jimquitevis.modernartui.model.Block;
import com.jimquitevis.modernartui.model.BlocksModel;

public class BlocksTest extends AndroidTestCase {
	private BlocksModel blocksModel;
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
		
		blocksModel = null;
	}
	
	public void testBlockWidthDoesNotExceedCanvasWidthAndHeight() {
		blocksModel = new BlocksModel(123, 456);
		blocksModel.newSeed();

		int heightCounter = 0;
		for (List<Block> blocks : blocksModel.getBlocks()) {
			int widthCounter = 0;
			int maxHeight = 0;
			for (Block block : blocks) {
				widthCounter += block.getWidth();
				maxHeight = Math.max(maxHeight, block.getHeight());
			}

			heightCounter += maxHeight;
			
			assertEquals(blocksModel.getWidth(), widthCounter);
		}
		
		assertEquals(blocksModel.getHeight(), heightCounter);
	}
	
	public void testBlockCalculatesTheCorrectRate() {
		Block block1 = new Block(100, 100, Color.rgb(0, 0, 0), Color.rgb(255, 255, 255));

		for (int x = 0; x <= 100; ++x) {
			int color = block1.getColorForRate(x, 100);
			int expectedColor = (int)((x / 100.0f) * 255);
			
			assertEquals(color, Color.rgb(expectedColor, expectedColor, expectedColor));
		}
		
		Block block2 = new Block(100, 100, Color.rgb(25, 0, 3), Color.rgb(32, 1, 26));
		assertEquals(block2.getColorForRate(0, 100), Color.rgb(25, 0, 3));
		assertEquals(block2.getColorForRate(100, 100), Color.rgb(32, 1, 26));

		Block block3 = new Block(100, 100, Color.rgb(255, 20, 100), Color.rgb(11, 2, 0));
		assertEquals(block3.getColorForRate(0, 100), Color.rgb(255, 20, 100));
		assertEquals(block3.getColorForRate(100, 100), Color.rgb(11, 2, 0));

		Block block4 = new Block(100, 100, Color.rgb(0, 0, 0), Color.rgb(0, 0, 0));
		assertEquals(block4.getColorForRate(0, 100), Color.rgb(0, 0, 0));
		assertEquals(block4.getColorForRate(100, 100), Color.rgb(0, 0, 0));

		Block block5 = new Block(100, 100, Color.rgb(1, 0, 0), Color.rgb(0, 1, 0));
		assertEquals(block5.getColorForRate(0, 100), Color.rgb(1, 0, 0));
		assertEquals(block5.getColorForRate(100, 100), Color.rgb(0, 1, 0));
	}
}
