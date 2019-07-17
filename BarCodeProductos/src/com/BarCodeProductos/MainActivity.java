package com.BarCodeProductos;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	ClaseSQL claseSQL = new ClaseSQL(this);

	Vibrator vibrator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
		
	}

	public void evtScanner(View view){
        Intent intent = new Intent(this, MainActivityScanner.class);
        startActivity(intent);
    }

    public void evtConsultar(View view){
       Intent intent = new Intent(this, MainActivityConsultas.class);
       startActivity(intent);
    }

    String Opcion;
    public void evtOpciones(View view){
        vibrator.vibrate(100);

        final String [] Opciones = {"Consulta Especifica","Borrar Registros","Volver"};

        AlertDialog.Builder Dialogo = new AlertDialog.Builder(this);
        Dialogo.setTitle("Opciones");

        Dialogo.setSingleChoiceItems(Opciones,2,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Opcion = Opciones[i];
            }
        }).setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try{
                  if(Opcion.equals("Consulta Especifica")){
                    MetodoConsultasEspecificas();
                  }

                  if(Opcion.equals("Borrar Registros")){
                    MetodoBorrarRegistros();
                  }
                }catch(NullPointerException ex){}
            }
        });

        Dialogo.create();
        Dialogo.show();
    }

    public void MetodoConsultasEspecificas(){
       Intent intent = new Intent(this, MainActivityConsultaEspecifica.class);
       startActivity(intent);
    }

    public void MetodoBorrarRegistros(){
        AlertDialog.Builder Dialogo = new AlertDialog.Builder(this);
        Dialogo.setTitle("Borrar Registros");
        Dialogo.setMessage("Deseas Eliminar Todos Los Registros?");

        Dialogo.setPositiveButton("Si",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try{
                 SQLiteDatabase db = claseSQL.getWritableDatabase();
                 claseSQL.DeleteFromTable(db);
                 Toast.makeText(getApplicationContext(),"Los Registros Fueron Eliminado",Toast.LENGTH_SHORT).show();
                }catch(Exception ex){
                 Toast.makeText(getApplicationContext(),"Error En La Eliminacion",Toast.LENGTH_SHORT).show();
                }
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        Dialogo.create();
        Dialogo.show();
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
