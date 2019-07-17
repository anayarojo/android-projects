package com.AndroidCamara;

import java.io.ByteArrayOutputStream;

import android.os.Bundle;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

	ClaseSQL claseSQL = new ClaseSQL(this);

    ImageView Image;

    private static int TAKE_PICTURE = 1;
    
    EditText txtID;
    TextView lblID;
    Button btnRegistrar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtID = (EditText)findViewById(R.id.txtID);
        lblID = (TextView)findViewById(R.id.lblID);
        btnRegistrar = (Button)findViewById(R.id.btnRegistrar);

        btnRegistrar.setEnabled(false);

        txtID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String Texto = txtID.getText().toString();
                if(Texto.length() > 0){
                  btnRegistrar.setEnabled(true);
                }else{
                  btnRegistrar.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
		
	}

	public void evtFoto(View view){
        Intent intent =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        int code = TAKE_PICTURE;

        startActivityForResult(intent, code);
    }


    byte[] byteArray;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try{
         if(requestCode == TAKE_PICTURE) {
           if(data.hasExtra("data")) {
            Image = (ImageView)findViewById(R.id.ImageView);


               Bitmap bmp = data.getParcelableExtra("data");

               Image.setImageBitmap(bmp);
               //Tambien Puede Ser Asi Pero lo pusimos en un bitmap para insertarlo a la BD
               //Image.setImageBitmap((Bitmap) data.getParcelableExtra("data"));

               ByteArrayOutputStream stream = new ByteArrayOutputStream();
               bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
               byteArray = stream.toByteArray();
           }
         }
        }catch(Exception ex){
          MetodoDialogoInsert("onActivityResult", "Error En: " + ex.getMessage());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
    
    public void MetodoDialogoInsert(String Titulo, String Msj){
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

    public void evtRegistrar(View view){
        try{
          String ID = txtID.getText().toString();
          Cursor cursor = claseSQL.ConsultaID(ID);

          String Identificacion="";
          while(cursor.moveToNext()){
           Identificacion = cursor.getString(cursor.getColumnIndex("ID"));
          }

          if(ID.equals(Identificacion)){
            MetodoDialogoInsert("Agregar","La Imagen Con ID: "+ID+" Ya Esta Registrada");
            txtID.requestFocus();
            btnRegistrar.setEnabled(false);
          }else{
            MetodoDialogoPreuntarInsertar(ID);
          }
        }catch(Exception ex){
            MetodoDialogoInsert("Agregar", "Error De: " + ex.getMessage());
        }
    }

    public void MetodoInsert(String ID){
        try{
         int cantidad=0;
         for(int i=0; i<byteArray.length; i++){
           cantidad=i;
         }

         MetodoDialogoInsert("Imagen", "Cantidad De Byte: " + cantidad + " Bytes");

         claseSQL.Insert(ID, byteArray);
         MetodoDialogoInsert("Imagen", "Se Ha Agregado La Imagen A La Base De Datos");
         txtID.setText("");
         Image.setImageBitmap(null);
        }catch(Exception ex){
          MetodoDialogoInsert("Insert", "Error Al Insertar A La Base De Datos: " + ex.getMessage());
        }
    }

    public void MetodoDialogoPreuntarInsertar(final String ID){
        AlertDialog.Builder Dialogo = new AlertDialog.Builder(this);
        Dialogo.setTitle("Agregar");
        Dialogo.setMessage("Deseas Agregar La Imagen A La Base De Datos?");
        Dialogo.setPositiveButton("Si",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              MetodoInsert(ID);
            }
        }).setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        Dialogo.create();
        Dialogo.show();
    }

    public void evtIr(View view){
    	System.out.println("entrando al evt Ir");
        Intent intent = new Intent(this, MainActivityConsultar.class);
        System.out.println("Intent = new Intent");
        startActivity(intent);
        System.out.println("star Activity");
    }

    public void evtRestablecer(View view){
        AlertDialog.Builder Dialogo = new AlertDialog.Builder(this);
        Dialogo.setTitle("Eliminar Imagenes");
        Dialogo.setMessage("Deseas Eliminar Las Imagenes De La Base De Datos?");
        Dialogo.setPositiveButton("Si",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                SQLiteDatabase sqLiteDatabase = claseSQL.getWritableDatabase();
                claseSQL.EliminarTodo(sqLiteDatabase);
                MetodoDialogoInsert("Imagen","Se Han Eliminado Las Imagenes De La Base De Datos");

            }
        }).setNegativeButton("No",new DialogInterface.OnClickListener() {
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
