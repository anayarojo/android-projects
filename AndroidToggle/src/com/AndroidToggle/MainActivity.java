package com.AndroidToggle;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	ToggleButton btnToogle;
    TextView lblToogle;

    Vibrator vibrator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 btnToogle = (ToggleButton)findViewById(R.id.btnToogle);
	        lblToogle = (TextView)findViewById(R.id.lblToogle);

	        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		
	}

	//Para Dar Efecto A la Vibracion del telefono se tiene que dar permisos en el manifest
    //checar eso.

    //Evento ToogleButton
    public void evtToogle(View view){
        boolean on = ((ToggleButton) view).isChecked();

        if (on) {
            lblToogle.setText("ON");
            vibrator.vibrate(100);
        } else {
            lblToogle.setText("OFF");
            vibrator.vibrate(100);
        }
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
