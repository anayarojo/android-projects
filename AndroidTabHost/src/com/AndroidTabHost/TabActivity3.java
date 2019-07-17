package com.AndroidTabHost;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TabActivity3 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_activity3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab_activity3, menu);
		return true;
	}

}
