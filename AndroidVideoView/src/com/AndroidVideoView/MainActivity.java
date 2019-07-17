package com.AndroidVideoView;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.MediaController;
import android.widget.VideoView;

public class MainActivity extends Activity {

	VideoView Video;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 Video = (VideoView)findViewById(R.id.videoView);
	     Video.setVideoURI(Uri.parse("android.resource://" + getPackageName() +"/"+R.raw.chris_brown));
	     Video.setMediaController(new MediaController(this));
	     Video.requestFocus();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
