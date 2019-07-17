package com.ListDesplegable;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends Activity {

	//Para Ver Detalladamente El Desarrollo De La Aplicacion VerVideos Youtube:
    //tutorial 150a hasta la 155 en español. desarrollo de aplicaciones android

    ExpandableListView ListExpandible;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		 ListExpandible = (ExpandableListView)findViewById(R.id.ListExpandible);
	        ListExpandible.setAdapter(new Adaptador(this));

	        ListExpandible.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
	            @Override
	            public boolean onChildClick(ExpandableListView expandableListView, View view, int PosicionGroup, int SubPosicionHijo, long l) {

	                String Articulo = Adaptador.SubTitulos[PosicionGroup][SubPosicionHijo];
	                Toast.makeText(getApplicationContext(),Articulo,Toast.LENGTH_SHORT).show();
	                return false;
	            }
	        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
