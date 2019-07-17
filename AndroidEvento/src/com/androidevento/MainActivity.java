package com.androidevento;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	TextView lblHello, lblContador;
	Button btnClick;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lblHello = (TextView)findViewById(R.id.lblHello);
		Hello();
		lblContador = (TextView)findViewById(R.id.lblContador);
		
		btnClick = (Button)findViewById(R.id.btnClick);		
		btnClick.setOnLongClickListener(LonListener);
		
	}
	
	public void Hello(){
		lblHello.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Hello Moto "+Contador, Toast.LENGTH_LONG).show();
			}
		});
	}
	
	int Contador;
	public void evtClick(View view){
      Contador++;
      lblContador.setText(Integer.toString(Contador));
	}
	
	public OnLongClickListener LonListener = new OnLongClickListener() {
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			Contador=0;
			lblContador.setText(Integer.toString(Contador));
			return true;
		}
	};
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
