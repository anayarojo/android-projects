package com.BarCodeProductos;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Vibrator;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivityConsultas extends Activity {

	ClaseSQL claseSQL = new ClaseSQL(this);

    ListView List;
    ArrayList<String> arrayList;
    ArrayAdapter<String> arrayAdapter;

    Vibrator vibrator;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_consultas);
		
		vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);

        List = (ListView)findViewById(R.id.List);
        arrayList = new ArrayList<String>();
        arrayAdapter = new ArrayAdapter<String>(this,R.layout.layout_list,arrayList);
        List.setAdapter(arrayAdapter);
        ConsultaGeneral();
		
        List.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Producto = arrayList.get(i);
                Cursor cursor = claseSQL.ConsultaFiltroProducto(Producto);

                String Codigo="", Precio="";
                while(cursor.moveToNext()){
                 Codigo = cursor.getString(cursor.getColumnIndex("Codigo"));
                 Precio = cursor.getString(cursor.getColumnIndex("Precio"));
                }

                MetodoDialogoConsultar(Codigo,"Precio: "+Precio);
            }
        });

        List.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                String Producto = arrayList.get(i);
                Cursor cursor = claseSQL.ConsultaFiltroProducto(Producto);

                String Codigo="";
                while(cursor.moveToNext()){
                    Codigo = cursor.getString(cursor.getColumnIndex("Codigo"));
                }

                MetodoOpcionesProducto(Codigo);
                return false;
            }
        });
        
	}
	
	public void ConsultaGeneral(){
       try{
         Cursor cursor = claseSQL.ConsultaGeneral();
         while (cursor.moveToNext()){
           String Producto = cursor.getString(cursor.getColumnIndex("Producto"));
           arrayList.add(Producto);
           arrayAdapter.notifyDataSetChanged();
         }
       }catch(Exception ex){
         MetodoDialogoConsultar("Consultar","Error En La Consulta General");
       }
    }

    public void MetodoDialogoConsultar(String Titulo, String Msj){
        vibrator.vibrate(100);
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
    
    String Opcion, IDCodigo;
    public void MetodoOpcionesProducto(String Codigo){
        vibrator.vibrate(100);

        IDCodigo = Codigo;

        final String [] Opciones = {"Modificar Producto","Eliminar Producto","Volver"};

        AlertDialog.Builder Dialogo = new AlertDialog.Builder(this);
        Dialogo.setTitle(Codigo);

        Dialogo.setSingleChoiceItems(Opciones,2,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Opcion = Opciones[i];
            }
        }).setPositiveButton("OK",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                try{
                    if(Opcion.equals("Modificar Producto")){
                        ModificarProducto(IDCodigo);
                    }

                    if(Opcion.equals("Eliminar Producto")){
                        EliminarProducto(IDCodigo);
                    }
                }catch(NullPointerException ex){}
            }
        });

        Dialogo.create();
        Dialogo.show();
    }

    public void ModificarProducto(String Codigo){
       Intent intent = new Intent(this, MainActivityModificar.class);
       intent.putExtra("Codigo",Codigo);
       startActivity(intent);
    }

    String CodigoEliminar;
    public void EliminarProducto(String Codigo){

        CodigoEliminar = Codigo;

       AlertDialog.Builder Dialogo = new AlertDialog.Builder(this);
       Dialogo.setTitle("Eliminar");
       Dialogo.setMessage("Deseas Eliminar Producto?");

       Dialogo.setPositiveButton("Si", new DialogInterface.OnClickListener() {
           @Override
           public void onClick(DialogInterface dialogInterface, int i) {
               try{

               claseSQL.EliminarProducto(CodigoEliminar);

               Cursor cursor = claseSQL.ConsultaCount();
               cursor.moveToFirst();
               int Count = cursor.getInt(cursor.getColumnIndex("Cont"));

                if(Count > 0){
                   arrayList.clear();
                   ConsultaGeneral();
                }else{
                   arrayList.clear();
                   arrayAdapter.notifyDataSetChanged();
                }

               }catch(Exception ex){
                   MetodoDialogoConsultar("Consultar","Error En La Eliminacion Del Producto: "+ex.getMessage());
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

    public void evtBusqueda(View view){
        Intent intent = new Intent(this, MainActivityConsultaEspecifica.class);
        startActivity(intent);
    }

    public void evtVolver(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_activity_consultas, menu);
		return true;
	}

}
