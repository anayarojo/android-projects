package com.NotificacionAndroid;

import android.os.Bundle;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText txtEnviar;
	
	Notification notifi;
    Intent intentNotificacion;	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtEnviar = (EditText)findViewById(R.id.txtEnviar);
		
	}
	
	public void evtEnviar(View view){
        String texto = txtEnviar.getText().toString();
        int icono = R.drawable.ic_launcher;

        String TextoNotificacion = "Haz Mandado Un Mensaje!";

        notifi = new Notification(icono,TextoNotificacion,System.currentTimeMillis());

        intentNotificacion = new Intent(this, MainActivity2.class);
        intentNotificacion.putExtra("Msj",texto);
        PendingIntent IntentPendiente = PendingIntent.getActivity(this, 0, intentNotificacion, Intent.FLAG_ACTIVITY_NEW_TASK);

        notifi.setLatestEventInfo(getApplicationContext(), "Notificacion", "Mensaje Parametro", IntentPendiente);
        notifi.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager NotifiMng = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotifiMng.notify(1, notifi);
    }
	
	public void evtNotificacion2(View view){
        String texto = txtEnviar.getText().toString();
        int icono = R.drawable.ic_launcher;

        String TextoNotificacion = "Haz Solicitado Otra Notificacion!";

        notifi = new Notification(icono,TextoNotificacion,System.currentTimeMillis());

        intentNotificacion = new Intent(this, MainActivity3.class);
        PendingIntent IntentPendiente = PendingIntent.getActivity(this, 0, intentNotificacion, Intent.FLAG_ACTIVITY_NEW_TASK);

        notifi.setLatestEventInfo(getApplicationContext(), "Notificacion2", "Mensaje Solicitud", IntentPendiente);
        notifi.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager NotifiMng = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        NotifiMng.notify(1, notifi);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
