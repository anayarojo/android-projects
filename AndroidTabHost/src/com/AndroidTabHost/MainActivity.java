package com.AndroidTabHost;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.view.Menu;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.content.Intent;

public class MainActivity extends TabActivity {

	TabHost tabHost;

    //Buen Aporte si se requiere ver detalladamente como se desarrollo la aplicacion
    //tienen que ir al siguiente enlace:

    //41 Android Tutorial Working with TabHost 1
    //http://www.youtube.com/watch?v=1-u3toC6ctY

    //41 Android Tutorial Working with TabHost 2
    //http://www.youtube.com/watch?v=6A-rdTKK90M
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		tabHost = getTabHost();

        TabSpec PrimerSpec = tabHost.newTabSpec("Primer");
        PrimerSpec.setIndicator("Primer Indicador",null);
        Intent PrimerIntent = new Intent(this, TabActivity1.class);
        PrimerSpec.setContent(PrimerIntent);

        TabSpec SegundoSpec = tabHost.newTabSpec("Segundo");
        SegundoSpec.setIndicator("Segundo Indicador",null);
        Intent SegundoIntent = new Intent(this, TabActivity2.class);
        SegundoSpec.setContent(SegundoIntent);

        TabSpec TercerSpec = tabHost.newTabSpec("Tercero");
        TercerSpec.setIndicator("Tercer Indicador",null);
        Intent TercerIntent = new Intent(this, TabActivity3.class);
        TercerSpec.setContent(TercerIntent);

        tabHost.addTab(PrimerSpec);
        tabHost.addTab(SegundoSpec);
        tabHost.addTab(TercerSpec);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
