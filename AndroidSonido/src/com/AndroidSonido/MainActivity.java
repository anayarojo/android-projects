package com.AndroidSonido;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener{

	MediaPlayer mp;
	
	Button btnStop, btnStart;
	
	AudioManager Am;

	SeekBar seek;
	
	//int duracionTotal, duracion, contador=0;
	
	/*clRunable clR = new clRunable();
	Handler hdl = new Handler();
	Hilo hl = new Hilo();*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		btnStop = (Button)findViewById(R.id.btnStop);
		btnStart = (Button)findViewById(R.id.btnSonido);	
		
		seek = (SeekBar)findViewById(R.id.seek);
		
		Am = (AudioManager)getSystemService(Context.AUDIO_SERVICE);
        int MaxVolumen = Am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int VolActual = Am.getStreamVolume(AudioManager.STREAM_MUSIC);
        
        seek.setMax(MaxVolumen);
        seek.setProgress(VolActual);

        seek.setOnSeekBarChangeListener(this);
		
	}

	int numero = 0;
	public void evtSonido(View view){
				
		numero++;
		
		if(numero<=1){
			mp = MediaPlayer.create(this, R.raw.joe);	
			
			/*duracionTotal = mp.getDuration() / 1000;
			Toast.makeText(getApplicationContext(), "Total: "+duracionTotal, Toast.LENGTH_SHORT).show();*/
			
			
			
			/*hl = new Hilo();
			hl.start();*/
		}
		
		
		if(numero % 2 == 0){	
			mp.pause();
			btnStart.setText("Start");
			
			//hl.interrupt();
			
		}else{
	        mp.start();		
			btnStart.setText("Pause");
			
			/*hl = new Hilo();
			hl.start();*/
			
		}
		
		
	}
	
	public void evtStop(View view){
		mp.stop();
		numero = 0;
		btnStart.setText("Start");
		//contador = 0;
		//hl.interrupt();
	}
		
	/*public class Hilo extends Thread{
		public void run(){
			try{
		      while(contador <= duracionTotal){
		    	  sleep(1000);
		    	  contador++;
		    	  hdl.post(clR);
		      }
			}catch(InterruptedException ex){}
		}
	}
	
	public class clRunable implements Runnable{
		public void run() {
			duracion = mp.getCurrentPosition() / 1000;
			txtDuracion.setText("Duracion: "+Integer.toString(duracion));
		}		
	}*/
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		Am.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {}

}
