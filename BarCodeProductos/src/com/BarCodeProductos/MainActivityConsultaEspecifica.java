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
import android.widget.TextView;

public class MainActivityConsultaEspecifica extends Activity {

	private static final String BS_PACKAGE = "com.google.zxing.client.android";
    public static final int REQUEST_CODE = 0x0000c0de;

    ClaseSQL claseSQL = new ClaseSQL(this);

    TextView txtCodigo, txtProducto, txtPrecio;

    Vibrator vibrator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_consulta_especifica);
		
		txtCodigo = (TextView)findViewById(R.id.txtCodigo);
        txtProducto = (TextView)findViewById(R.id.txtProducto);
        txtPrecio = (TextView)findViewById(R.id.txtPrecio);

        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        MetodoScanner();
		
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
                MetodoConsultaEspecifica(codigo);
            }
        }
    }
    
    public void MetodoConsultaEspecifica(String Codigo){
        try{
          String IDCodigo="", Producto="", Precio="";

          txtCodigo.setText(Codigo);

          Cursor cursor = claseSQL.ConsultaFiltro(Codigo);
          while(cursor.moveToNext()){
              IDCodigo = cursor.getString(cursor.getColumnIndex("Codigo"));
              Producto = cursor.getString(cursor.getColumnIndex("Producto"));
              Precio = cursor.getString(cursor.getColumnIndex("Precio"));
          }

           if(Codigo.equals(IDCodigo)){
              txtProducto.setText(Producto);
              txtPrecio.setText(Precio);
           }else{
              vibrator.vibrate(100);
              AlertDialog.Builder Dialogo = new AlertDialog.Builder(this);
              Dialogo.setTitle("El Producto No Esta Registrado");
              Dialogo.setMessage("Desea Scannear Para Registrarlo?");
              Dialogo.setPositiveButton("Si",new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                    MetodoIrARegistrar();
                  }
              }).setNegativeButton("No",new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                  }
              });
              Dialogo.create();
              Dialogo.show();

           }

        }catch(Exception ex){
            MetodoDialogoConsultaEspecifica("Consulta Especifica", "Error En La Consulta Especifica");
        }
    }
    
    public void MetodoIrARegistrar(){
        Intent intent = new Intent(this, MainActivityScanner.class);
        startActivity(intent);
    }

    public void MetodoDialogoConsultaEspecifica(String Titulo, String Msj){
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

    public void evtScanner(View view){
     MetodoScanner();
    }
    
    String Opcion;
    public void evtVolver(View view){
        vibrator.vibrate(100);

        final String [] Opciones = {"Ir A Inicio","Ir A Consultar","Volver"};

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
                    if(Opcion.equals("Ir A Inicio")){
                        MetodoIrAInicio();
                    }

                    if(Opcion.equals("Ir A Consultar")){
                        MetodoIrAConsultar();
                    }
                }catch(NullPointerException ex){}
            }
        });

        Dialogo.create();
        Dialogo.show();
    }

    public void MetodoIrAInicio(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void MetodoIrAConsultar(){
        Intent intent = new Intent(this, MainActivityConsultas.class);
        startActivity(intent);
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_consulta_especifica,
				menu);
		return true;
	}

}
