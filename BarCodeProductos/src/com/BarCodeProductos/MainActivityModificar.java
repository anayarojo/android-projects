package com.BarCodeProductos;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivityModificar extends Activity {

	ClaseSQL claseSQL = new ClaseSQL(this);

    TextView txtCodigo;
    EditText txtProducto, txtPrecio;

    Vibrator vibrator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_modificar);
		
		vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        txtProducto = (EditText)findViewById(R.id.txtProducto);
        txtPrecio = (EditText)findViewById(R.id.txtPrecio);

        txtCodigo = (TextView)findViewById(R.id.txtCodigo);
        Intent intent = getIntent();
        String Codigo = intent.getStringExtra("Codigo");
        txtCodigo.setText(Codigo);
        MetodoDatosProducto(Codigo);
		
	}
	
	public void MetodoDatosProducto(String Codigo){
        try{
          String Producto="", Precio="";
          Cursor cursor = claseSQL.ConsultaFiltro(Codigo);
          while(cursor.moveToNext()){
            Producto = cursor.getString(cursor.getColumnIndex("Producto"));
            Precio = cursor.getString(cursor.getColumnIndex("Precio"));
          }
          txtProducto.setText(Producto);
          txtPrecio.setText(Precio);
        }catch(Exception ex){
          MetodoDialogoModificar("Modificar","Error En La Busqueda Del Producto");
        }
    }

    public void evtModificar(View view){
        vibrator.vibrate(100);

        AlertDialog.Builder Dialogo = new AlertDialog.Builder(this);
        Dialogo.setTitle("Modificar");
        Dialogo.setMessage("Desea Modificar El Producto?");

        Dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               MetodoModificar();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        Dialogo.create();
        Dialogo.show();
    }

    public void MetodoModificar(){
        try{
          String Codigo, Producto, Precio;

          Codigo = txtCodigo.getText().toString();
          Producto = txtProducto.getText().toString();
          Precio = txtPrecio.getText().toString();

          SQLiteDatabase db = claseSQL.getWritableDatabase();
          claseSQL.ModificarProducto(db, Codigo, Producto, Precio);

          MetodoDialogoModificar("Modificar","Producto Modificado");

          Intent intent = new Intent(this, MainActivityConsultas.class);
          startActivity(intent);

        }catch(Exception ex){
          MetodoDialogoModificar("Modificar","Error Al Modificar Producto");
        }
    }

    public void MetodoDialogoModificar(String Titulo, String Msj){
        AlertDialog.Builder Dialogo = new AlertDialog.Builder(this);
        Dialogo.setTitle(Titulo);
        Dialogo.setMessage(Msj);

        Dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        Dialogo.create();
        Dialogo.show();
    }

    public void evtVolver(View view){
        Intent intent = new Intent(this, MainActivityConsultas.class);
        startActivity(intent);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_modificar, menu);
		return true;
	}

}
