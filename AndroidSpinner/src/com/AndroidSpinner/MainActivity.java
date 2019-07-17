package com.AndroidSpinner;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity {

	Spinner Spiner;
    TextView lblSpiner;
    EditText txtAgregar;

    //ArrayList que usaremos para agregar datos al Spinner
    ArrayList<String> Lista = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lblSpiner = (TextView)findViewById(R.id.lblSpiner);
        txtAgregar = (EditText)findViewById(R.id.txtAgregar);

        Spiner = (Spinner)findViewById(R.id.Spiner);
        //Adaptador para agregar el arrayString (Animales) de animales creados en el package de values llamado recursoSpiner.xml
        ArrayAdapter<CharSequence> Adapter = ArrayAdapter.createFromResource(this,R.array.Animales,android.R.layout.simple_spinner_item);

        Spiner.setAdapter(Adapter);

        Spiner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lblSpiner.setText(Spiner.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
		
	}
	
	public void evtRestablecer(View view){
        ArrayAdapter<CharSequence> Adapter = ArrayAdapter.createFromResource(this,R.array.Animales,android.R.layout.simple_spinner_item);
        Spiner.setAdapter(Adapter);
    }

    public void evtAgregar(View view){

        Lista.add(txtAgregar.getText().toString());

        ArrayAdapter<String> Adaptador = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, Lista);

        Spiner.setAdapter(Adaptador);

        txtAgregar.setText("");
        txtAgregar.requestFocus();
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
