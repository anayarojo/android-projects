package com.AndroidDialog;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	//Dialogo 1
    public void evtDialogo1(View view){
        AlertDialog.Builder Dialogo1 = new AlertDialog.Builder(this);

        Dialogo1.setTitle("Dialogo 1");
        Dialogo1.setMessage("Elija Opcion");

        Dialogo1.setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Has Oprimido OK",Toast.LENGTH_SHORT).show();
            }
        });

        Dialogo1.setNeutralButton("Cancelar",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Has Oprimido Cancelar",Toast.LENGTH_SHORT).show();
            }
        });

        Dialogo1.setNegativeButton("Close",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Has Oprimido Close",Toast.LENGTH_SHORT).show();
            }
        });

        Dialogo1.create();
        Dialogo1.show();

    }


    //Dialogo 2
    public void evtDialogo2(View view){

        final String[] ArraySexo = {"Hombre","Mujer","Otro"};
        //String[] ArraySexo = {"Hombre","Mujer","Otro"};

        AlertDialog.Builder Dialogo2 = new AlertDialog.Builder(this);

        Dialogo2.setTitle("Dialogo 2");

        Dialogo2.setItems(ArraySexo,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String Sexo = ArraySexo[i];
                Toast.makeText(getApplicationContext(),"Has Elejido "+Sexo,Toast.LENGTH_SHORT).show();
            }
        });

        Dialogo2.create();
        Dialogo2.show();
    }


    //Dialogo 3
    String Sexo="Hombre";
    public void evtDialogo3(View vie){
        final String[] ArraySexo = {"Hombre","Mujer","Otro"};


        AlertDialog.Builder Dialogo3 = new AlertDialog.Builder(this);

        Dialogo3.setTitle("Elije Opcion");

        Dialogo3.setSingleChoiceItems(ArraySexo, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Sexo = ArraySexo[i];
            }
        }).setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(),"Has Elejido "+Sexo,Toast.LENGTH_SHORT).show();
            }
        });

        Dialogo3.create();
        Dialogo3.show();

    }


    //Dialogo 4
    public void evtDialogo4(View view){

        final String[] Verduras = {"Lechuga","Tomate","Zanaoria"};
        final ArrayList<String> Seleccion = new ArrayList<String>();

        AlertDialog.Builder Dialogo4 = new AlertDialog.Builder(this);
        Dialogo4.setTitle("Dialogo 4");

        Dialogo4.setMultiChoiceItems(Verduras,null,new DialogInterface.OnMultiChoiceClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i, boolean isCheked) {

                if (isCheked) {
                    // If the user checked the item, add it to the selected items
                    Seleccion.add(Verduras[i]);
                } else if (Seleccion.contains(Verduras[i])) {
                    // Else, if the item is already in the array, remove it
                    Seleccion.remove(Verduras[i]);
                }

            }
        }).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "" + Seleccion, Toast.LENGTH_SHORT).show();
            }
        });

        Dialogo4.create();
        Dialogo4.show();

    }
    
    public void evtDialogo5(View view){
    	LayoutInflater li = LayoutInflater.from(this);
		View prompt = li.inflate(R.layout.dialog_custome, null);

		// Creamos un constructor de Alert Dialog y le añadimos nuestro layout al cuadro de dialogo
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
		//alertDialogBuilder.setIcon(R.drawable.ic_launcher);
		alertDialogBuilder.setTitle("Escribe algo");
		alertDialogBuilder.setView(prompt);

		final EditText nombreUsuario = (EditText) prompt.findViewById(R.id.txtAlgo);

		// Mostramos el mensaje del cuadro de dialogo
		alertDialogBuilder
		.setCancelable(false)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// Rescatamos el nombre del EditText y lo mostramos por pantalla
				Toast.makeText(getApplicationContext(),"Hola "+nombreUsuario.getText(), Toast.LENGTH_LONG).show();
			}
		})
		.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// Cancelamos el cuadro de dialogo
				dialog.cancel();
			}
		});

		// Creamos un AlertDialog y lo mostramos
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();	
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
