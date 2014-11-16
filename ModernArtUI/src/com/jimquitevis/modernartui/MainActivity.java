package com.jimquitevis.modernartui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.Toast;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

import com.jimquitevis.modernartui.view.BlocksView;

public class MainActivity extends ActionBarActivity {
	private BlocksView blocksView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
	    getWindow().getDecorView().setBackgroundColor(Color.BLACK);
		
		SeekBar seekBar = (SeekBar) findViewById(R.id.seekBar);
		seekBar.setProgress(0);
		
		blocksView = (BlocksView) findViewById(R.id.blocks);
		blocksView.setRate(seekBar.getProgress());
		blocksView.setMaxRate(seekBar.getMax());
		
		seekBar.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				blocksView.setRate(progress);
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_more_info) {
			AlertDialog dialog = 
				new AlertDialog.Builder(this)
					.setMessage(R.string.visit_moma_title)
					.setPositiveButton(R.string.visit_moma_ok, new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							Intent intent = new Intent(Intent.ACTION_VIEW);
							intent.setData(Uri.parse("http://www.moma.org"));
							startActivity(intent);
						}
					})
					.setNegativeButton(R.string.visit_moma_cancel, new OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int which) {
							//no-op
						}
					})
					.create();
			
			dialog.show();
			TextView messageView = (TextView)dialog.findViewById(android.R.id.message);
			messageView.setGravity(Gravity.CENTER);
			
			return true;
		}
		else if (id == R.id.action_new_seed) {
			blocksView.newSeed();
		}
		else if (id == R.id.action_about) {
			Toast.makeText(MainActivity.this, R.string.about, Toast.LENGTH_SHORT).show();
		}
		
		return super.onOptionsItemSelected(item);
	}
}
