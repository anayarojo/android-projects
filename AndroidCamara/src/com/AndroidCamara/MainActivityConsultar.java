package com.AndroidCamara;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivityConsultar extends Activity {

	ClaseSQL claseSQL = new ClaseSQL(this);

    ImageView Image;
    EditText txtID;
    TextView lblBytes;
    Button btnConsultar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.consulta_image);
		
		Image = (ImageView)findViewById(R.id.Image);
        txtID = (EditText)findViewById(R.id.txtID);
        lblBytes = (TextView)findViewById(R.id.lblBytes);
        btnConsultar = (Button)findViewById(R.id.btnConsultar);

        btnConsultar.setEnabled(false);

        txtID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {}
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String Texto = txtID.getText().toString();
                if(Texto.length() > 0){
                    btnConsultar.setEnabled(true);
                }else{
                    btnConsultar.setEnabled(false);
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {}
        });
		
	}
	
	public void evtConsultar(View view){
        try{
           String ID = txtID.getText().toString();
           Cursor cursor = claseSQL.ConsultaID(ID);

            String Identificacion="";
            while(cursor.moveToNext()){
                Identificacion = cursor.getString(cursor.getColumnIndex("ID"));
            }

            if(ID.equals(Identificacion)){
               MetodoMostrarImagen(ID);
            }else{
               Image.setImageBitmap(null);
               MetodoDialogoConsultar("Consultar","La Imagen Con ID: "+ID+" No Esta Registrada");
               txtID.requestFocus();
            }
        }catch(Exception ex){
          MetodoDialogoConsultar("Consultar","Error De: "+ex.getMessage());
        }
    }

    byte[] blob;
    public void MetodoMostrarImagen(String ID){

        Bitmap bmp;

        try{
          Cursor cursor = claseSQL.FiltroImagen(ID);

           while(cursor.moveToNext()){
             blob = cursor.getBlob(cursor.getColumnIndex("Image"));
           }

            bmp=BitmapFactory.decodeByteArray(blob,0,blob.length);
            Image.setImageBitmap(bmp);

            lblBytes.setText("");

        }catch(Exception ex){
            MetodoDialogoConsultar("Consultar","Error De: "+ex.getMessage());
        }
    }

    public void MetodoDialogoConsultar(String Titulo, String Msj){
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
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_consultar, menu);
		return true;
	}

}
