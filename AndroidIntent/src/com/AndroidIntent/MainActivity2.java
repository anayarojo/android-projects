package com.AndroidIntent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends Activity {

	TextView lblMensaje;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_activity2);
		
		lblMensaje = (TextView)findViewById(R.id.txtMensaje);

        Intent intent = getIntent();
        String Mensaje = intent.getStringExtra("Msj");

        lblMensaje.setText(Mensaje);
		
	}
	
	public void evtRegresar(View view){
        lblMensaje.setText("");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity2, menu);
		return true;
	}

}
