package com.AndroidProgressCirculo;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}
	
	public void evtClick(View view){

        //final ProgressDialog Dialogo = ProgressDialog.show(this,"Espere","Cargando...",true);

        final ProgressDialog Dialogo = new ProgressDialog(this);

        Dialogo.setTitle("Progress Dialog");
        Dialogo.setMessage("Espere...");
        Dialogo.setCancelable(false);
        Dialogo.setButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Dialogo.setCancelable(true);
                Dialogo.dismiss();
                Toast.makeText(getApplicationContext(),"Ha Cancelado El Progreso",Toast.LENGTH_SHORT).show();
            }
        });
        Dialogo.show();

        new Thread(new Runnable() {
            @Override
            public void run() {
               try{
                Thread.sleep(5000);
                Dialogo.dismiss();
               }catch(Exception ex){}
                Dialogo.dismiss();
            }
        }).start();

    }


    public void evtClickDialog(View view){
      final ProgressDialog prog = new ProgressDialog(MainActivity.this);
      prog.setTitle("Progress Dialog");
      prog.setMessage("Mensaje");
      prog.setCancelable(false);
      prog.setButton("Mensaje", new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialogInterface, int i) {
              prog.dismiss();
              Toast.makeText(getApplicationContext(), "Mensaje", Toast.LENGTH_SHORT).show();
          }
      });
      prog.show();

      //prog.show(ActivityProgressCirculo.this, "ProgressDialog", "Ejemplo de progreso", true, true);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
