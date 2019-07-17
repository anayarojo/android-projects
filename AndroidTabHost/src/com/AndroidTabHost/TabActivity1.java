package com.AndroidTabHost;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TabActivity1 extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tab_activity1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.tab_activity1, menu);
		return true;
	}

}
