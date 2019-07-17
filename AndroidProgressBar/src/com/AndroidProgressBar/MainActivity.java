package com.AndroidProgressBar;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

public class MainActivity extends Activity {

	private ProgressBar Progress;
    int Contador=0;

    ClaseRunable ClRun = new ClaseRunable();
    Handler Han = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Progress = (ProgressBar)findViewById(R.id.Progress);
		
	}
	
	Hilo Hl = new Hilo();
    public void evtIniciar(View view){
        Hl = new Hilo();
        Hl.start();
    }

    public void evtDetener(View view){
        Hl.interrupt();
    }

    public void evtReiniciar(View view){
        Hl.interrupt();
        Contador=0;
        Progress.setProgress(0);
        Hl = new Hilo();
        Hl.start();
    }


    public class Hilo extends Thread{
        public void run(){
            try{
              while(Contador<=99){
                  sleep(30);
                  Contador++;
                  Han.post(ClRun);
              }
            }catch(InterruptedException ex){}
        }
    }

    public class ClaseRunable implements Runnable{
        public void run(){
            Progress.setProgress(Contador);
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
