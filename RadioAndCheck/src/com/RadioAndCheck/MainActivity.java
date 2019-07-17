package com.RadioAndCheck;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	 private RadioButton Radio;
	    private RadioGroup RGrupo;
	    private TextView lblMensaje, lblClick;

	    private CheckBox Check1, Check2, Check3;
	    private TextView lbl1, lbl2, lbl3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		RGrupo = (RadioGroup)findViewById(R.id.RGrupo);

        lblMensaje = (TextView)findViewById(R.id.lblMensaje);
        lblClick = (TextView)findViewById(R.id.lblClick);

        lblClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                 int RadioID = RGrupo.getCheckedRadioButtonId();
                 Radio = (RadioButton)findViewById(RadioID);
                 lblMensaje.setText("Yo Estoy "+Radio.getText().toString());
                }catch(Exception ex){}
            }
        });

        Check1 = (CheckBox)findViewById(R.id.Check1);
        Check2 = (CheckBox)findViewById(R.id.Check2);
        Check3 = (CheckBox)findViewById(R.id.Check3);
        lbl1 = (TextView)findViewById(R.id.lbl1);
        lbl2 = (TextView)findViewById(R.id.lbl2);
        lbl3 = (TextView)findViewById(R.id.lbl3);
		
	}

	public void evtCheck(View view){
        if(view.getId() == Check1.getId()){
            if(Check1.isChecked()){
                lbl1.setText("Activado");
            }else{
                lbl1.setText("Desactivado");
            }
        }

        if(view.getId() == Check2.getId()){
            if(Check2.isChecked()){
                lbl2.setText("Activado");
            }else{
                lbl2.setText("Desactivado");
            }
        }

        if(view.getId() == Check3.getId()){
            if(Check3.isChecked()){
                lbl3.setText("Activado");
            }else{
                lbl3.setText("Desactivado");
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
