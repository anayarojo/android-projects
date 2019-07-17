package com.AndroidSwitch;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends Activity implements CompoundButton.OnCheckedChangeListener{

	//Para La Cuestion Del Switch solo funcionara en la version 4.0 en adelante
	//Current api 14 como minimo en AndroidManifest.xml
    Switch sw;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sw = (Switch)findViewById(R.id.sw);
        sw.setOnCheckedChangeListener(this);
		
	}
	
	 public void onCheckedChanged(CompoundButton cb, boolean isChecked){
	        if(sw.isChecked()){
	            sw.setText("ON");
	        }else{
	            sw.setText("OFF");
	        }
	    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
