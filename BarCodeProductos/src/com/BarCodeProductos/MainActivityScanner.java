package com.BarCodeProductos;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivityScanner extends Activity {

	private static final String BS_PACKAGE = "com.google.zxing.client.android";
    public static final int REQUEST_CODE = 0x0000c0de;

    EditText txtCodigo, txtProducto, txtPrecio;

    ClaseSQL claseSQL = new ClaseSQL(this);

    Vibrator vibrator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_scanner);
		
		vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        txtCodigo = (EditText)findViewById(R.id.txtCodigo);
        txtProducto = (EditText)findViewById(R.id.txtProducto);
        txtPrecio = (EditText)findViewById(R.id.txtPrecio);

        this.MetodoScanner();
		
	}

	public void MetodoScanner(){
        Intent intentScan = new Intent(BS_PACKAGE + ".SCAN");
        intentScan.putExtra("PROMPT_MESSAGE", "Enfoque entre 9 y 11 cm. Viendo solo el codigo de barras");
        startActivityForResult(intentScan, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                String codigo = data.getStringExtra("SCAN_RESULT");
                txtCodigo.setText(codigo);
                txtProducto.requestFocus();
            }
        }
    }

    public void evtScanner(View view){
        this.MetodoScanner();
    }

    public void evtVolver(View view){
       vibrator.vibrate(100);
       Intent intent = new Intent(this, MainActivity.class);
       startActivity(intent);
    }
    
    /*String Opcion;
    public void evtOp(View view){
    	vibrator.vibrate(100);
        final String [] Opciones = {"Restablecer Campos","Ir A Consultas","Volver"};
    	
        AlertDialog.Builder Dialogo = new AlertDialog.Builder(this);
        Dialogo.setTitle("Opciones");
        
    	Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_SHORT).show();
    }*/
    
    String Opcion;
    public void evtOpciones(View view){
        vibrator.vibrate(100);
        final String [] Opciones = {"Restablecer Campos","Ir A Consultas","Volver"};
        
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
                   if(Opcion.equals("Restablecer Campos")){
                     txtCodigo.setText("");
                     txtProducto.setText("");
                     txtPrecio.setText("");
                     txtCodigo.requestFocus();
                   }

                   if(Opcion.equals("Ir A Consultas")){
                    MetodoIrAConsultas();
                   }
                 }catch(NullPointerException ex){}
             }
         });
         
        Dialogo.create();
        Dialogo.show();
    }

    public void MetodoIrAConsultas(){
        Intent intent = new Intent(this, MainActivityConsultas.class);
        startActivity(intent);
    }

    public void evtRegistrar(View view){
        vibrator.vibrate(100);

        AlertDialog.Builder Dialogo = new AlertDialog.Builder(this);
        Dialogo.setTitle("Registrar");
        Dialogo.setMessage("Desea Registrar El Producto?");

        Dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
               MetodoValidarInsert();
            }
        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });

        Dialogo.create();
        Dialogo.show();
    }

    public void MetodoValidarInsert(){
        String Codigo = txtCodigo.getText().toString();
        if(Codigo.equals("")){
            vibrator.vibrate(100);
            MetodoDialogoRegistrar("Debes Capturar Codigo Del Producto");
        }else{
            try{

              String Dato = "";

              Cursor cursor = claseSQL.ConsultaFiltro(Codigo);
              while(cursor.moveToNext()){
               cursor.moveToFirst();
               Dato = cursor.getString(cursor.getColumnIndex("Codigo"));
              }

               if(Dato.equals(Codigo)){
                vibrator.vibrate(100);
                MetodoDialogoRegistrar("El Producto Ya Esta Registrado");
               }else{
                MetodoRegistrar();
               }

            }catch(Exception ex){
               Toast.makeText(getApplicationContext(),ex.getMessage(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void MetodoRegistrar(){
        String Codigo = txtCodigo.getText().toString();
        String Producto = txtProducto.getText().toString();
        String Precio = txtPrecio.getText().toString();

        try{
         claseSQL.Insert(Codigo, Producto, Precio);
         MetodoDialogoRegistrar("Producto Registrado");
          txtCodigo.setText("");
          txtProducto.setText("");
          txtPrecio.setText("");
          txtCodigo.requestFocus();
        }catch(Exception ex){
         vibrator.vibrate(100);
         MetodoDialogoRegistrar("Error En El Registro");
        }
    }

    public void MetodoDialogoRegistrar(String Msj){
        AlertDialog.Builder Dialogo = new AlertDialog.Builder(this);
        Dialogo.setTitle("Registrar");
        Dialogo.setMessage(Msj);

        Dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
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
		getMenuInflater().inflate(R.menu.main_activity_scanner, menu);
		return true;
	}

}
