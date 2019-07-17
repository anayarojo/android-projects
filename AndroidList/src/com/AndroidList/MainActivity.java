package com.AndroidList;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

	 EditText txtCaptura;
	 ListView List;

	 //String[] Personas = {"Juan","Maria","Jesus","Pedro","Lupita","Ramon"};
	 //Este Arreglo Es Para Cuando Quieres Mostrar Valores Por Default Sobre La Lista y sin que se agrege ningun otro valor


	 ArrayAdapter<String> Adapter;
	 ArrayList<String> ArrayPersona;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtCaptura = (EditText)findViewById(R.id.txtCaptura);

        List = (ListView)findViewById(R.id.List);

        ArrayPersona = new ArrayList<String>();
        ArrayPersona.add("Juan");
        ArrayPersona.add("Maria");

        Adapter = new ArrayAdapter<String>(this,R.layout.listado,ArrayPersona);
        List.setAdapter(Adapter);

        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int iPosicion, long l) {

                Toast.makeText(getApplicationContext(),ArrayPersona.get(iPosicion)+" - "+iPosicion,Toast.LENGTH_SHORT).show();

            }
        });


        List.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int iPosicion, long l) {

                Toast.makeText(getApplicationContext(),"Has Eliminado: "+ArrayPersona.get(iPosicion)+" - "+iPosicion,Toast.LENGTH_SHORT).show();
                ArrayPersona.remove(iPosicion);
                Adapter.notifyDataSetChanged();

                return false;
            }
        });
		
	}
	
	public void evtCaptura(View view){

        String StrPersona = txtCaptura.getText().toString();
        ArrayPersona.add(StrPersona);

        Adapter.notifyDataSetChanged();

        txtCaptura.setText("");
        txtCaptura.requestFocus();

    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
