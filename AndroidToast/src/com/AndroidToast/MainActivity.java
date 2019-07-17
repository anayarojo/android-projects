package com.AndroidToast;

import android.os.Bundle;
import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	public void evtGravity(View view){
		Toast toastGravity = Toast.makeText(getApplicationContext(),"Toast con gravity", Toast.LENGTH_SHORT);
		toastGravity.setGravity(Gravity.BOTTOM|Gravity.LEFT,0,0);
		toastGravity.show();
	}
	
    public void evtCustome(View view){
    	Toast toastCustome = new Toast(getApplicationContext());
    	 
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout,(ViewGroup) findViewById(R.id.lytLayout));
 
        TextView txtMsg = (TextView)layout.findViewById(R.id.txtMensaje);
        txtMsg.setText("Toast Personalizado");
 
        toastCustome.setDuration(Toast.LENGTH_SHORT);
        toastCustome.setView(layout);
        toastCustome.show();
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
