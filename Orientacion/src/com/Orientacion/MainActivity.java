package com.Orientacion;

import android.os.Bundle;
import android.app.Activity;
import android.content.res.Configuration;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView txtOrientacion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtOrientacion = (TextView)findViewById(R.id.txtOrientacion);
		
	}

		
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		
		if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
			txtOrientacion.setText("Orientacion Vertical");			
		}
		
		if(newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
			txtOrientacion.setText("Orientacion Horizontal");			
		}
		
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	

}
