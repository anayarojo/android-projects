package com.BarCodeScanner;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final String BS_PACKAGE = "com.google.zxing.client.android";
    public static final int REQUEST_CODE = 0x0000c0de;

    TextView lblCode;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lblCode = (TextView)this.findViewById(R.id.lblCode);
	}

	 public void evtScan(View view){
	        Intent intentScan = new Intent(BS_PACKAGE + ".SCAN");
	        intentScan.putExtra("PROMPT_MESSAGE", "Enfoque entre 9 y 11 cm.viendo sólo el código de barras");
	        startActivityForResult(intentScan, REQUEST_CODE);
	    }

	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        // TODO Auto-generated method stub
	        super.onActivityResult(requestCode, resultCode, data);

	        if(requestCode == REQUEST_CODE) {
	            if (resultCode == Activity.RESULT_OK) {
	                String contents = data.getStringExtra("SCAN_RESULT");
	                lblCode.setText(contents);
	            }
	        }
	    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
