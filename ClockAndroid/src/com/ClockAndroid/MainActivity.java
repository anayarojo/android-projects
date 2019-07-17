package com.ClockAndroid;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AnalogClock;
import android.widget.TextClock;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextClock textClock;
    AnalogClock AnalogoClock;
    TextView lblFecha;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textClock = (TextClock)findViewById(R.id.textClock);
        textClock.setClickable(true);

        AnalogoClock = (AnalogClock)findViewById(R.id.AnalogoClock);
        lblFecha = (TextView)findViewById(R.id.lblFecha);

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formatoFecha = dateFormat.format(cal.getTime());
        lblFecha.setText(formatoFecha);
		
	}

	public void evtClick(View view){
        System.out.println("click en textclock");
        Toast.makeText(getApplicationContext(),textClock.getText().toString(),Toast.LENGTH_SHORT).show();
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
