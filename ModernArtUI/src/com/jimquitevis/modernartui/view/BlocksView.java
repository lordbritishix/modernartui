package com.jimquitevis.modernartui.view;

import java.util.List;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.jimquitevis.modernartui.model.Block;
import com.jimquitevis.modernartui.model.BlocksModel;

public class BlocksView extends View {
	private Paint blockPaint;
	private BlocksModel model;
	private int rate = 0;
	private int maxRate = 100;
	
	public BlocksView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public void newSeed() {
		model.newSeed();
		invalidate();
	}
	
	private void init() {
		blockPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	}
	
	public void setMaxRate(int maxRate) {
		this.maxRate = maxRate;
	}
	
	public void setRate(int rate) {
		this.rate = rate;
		invalidate();
	}

	public BlocksModel getModel() {
		return model;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		if (model == null) {
			model = new BlocksModel(canvas.getWidth(), canvas.getHeight());
			model.newSeed();
		}
		
		int y = 0;

		for (List<Block> blocks : model.getBlocks()) {
			int x = 0;
			int blockHeight = 0;
			for (Block block : blocks) {
				blockPaint.setColor(block.getColorForRate(rate, maxRate));
				canvas.drawRect(x, y, x + block.getWidth(), y + block.getHeight(), blockPaint);
				x += block.getWidth();
				blockHeight = Math.max(blockHeight, block.getHeight());
			}
			
			y += blockHeight;
		}
	}
}
