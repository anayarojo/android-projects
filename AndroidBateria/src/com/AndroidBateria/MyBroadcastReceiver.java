package com.AndroidBateria;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver{
	
	public String Power;
	
	IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
	
	@Override
	public void onReceive(Context context, Intent intent) {
		
		String action = intent.getAction();

	    if(action.equals(Intent.ACTION_POWER_CONNECTED)) {
	    	Toast.makeText(context, "Conectado", Toast.LENGTH_LONG).show();
	    }
	    else if(action.equals(Intent.ACTION_POWER_DISCONNECTED)) {
	    	Toast.makeText(context, "Desconectado", Toast.LENGTH_LONG).show();
	    }
	        
	    
	    Intent batteryStatus = context.registerReceiver(null, ifilter);
	    
	    // Are we charging / charged?
	    int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
	    boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
	                         status == BatteryManager.BATTERY_STATUS_FULL;
	    
        Toast.makeText(context, "Cargando... "+isCharging, Toast.LENGTH_LONG).show();
	    	    
	    // How are we charging?
	    int chargePlug = batteryStatus.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
	    boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
	    
        Toast.makeText(context, "Cargando por USB... "+usbCharge, Toast.LENGTH_LONG).show();
	    
	    
	    boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;
        Toast.makeText(context, "Cargando por C-A... "+acCharge, Toast.LENGTH_LONG).show();
	    
	    
	    int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
	    int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

	    float batteryPct = level / (float)scale;
        Toast.makeText(context, "Nivel de Bateria: "+batteryPct+"%", Toast.LENGTH_LONG).show();
	    
	}
	

}
