package com.AndroidMenu;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	TextView textView;
	ImageView ImgView;
	ImageButton imgButon;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		textView = (TextView)findViewById(R.id.textView);
        ImgView = (ImageView)findViewById(R.id.ImgView);
		
	}

	@Override
    public boolean onOptionsItemSelected(MenuItem item) {

        String Msj="";

        switch(item.getItemId()){

            case R.id.menu_new:
                Log.i("ActionBar","Nuevo");
                Msj = "Nuevo";
                Metodo(Msj);
                return true;
            case R.id.menu_save:
                Log.i("ActionBar","Guardar");
                Msj = "Guardar";
                Metodo(Msj);
                return true;
            default:
            return super.onOptionsItemSelected(item);

        }
    }

	
    public void Metodo(String Msj){
        textView.setText(Msj);

        if(Msj.equals("Guardar")){
            ImgView.setImageURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.onebit_11));
        }

        if(Msj.equals("Nuevo")){
            ImgView.setImageURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.onebit_31));
        }

    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
