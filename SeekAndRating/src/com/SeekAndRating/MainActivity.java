package com.SeekAndRating;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity implements SeekBar.OnSeekBarChangeListener{

	SeekBar SeekBr;
    TextView lblNumero;
    ProgressBar Progress;

    //--------------------------------------------------
    RatingBar Rating;
    TextView lblNraiting;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		SeekBr = (SeekBar)findViewById(R.id.SeekBr);
        SeekBr.setOnSeekBarChangeListener(this);

        lblNumero = (TextView)findViewById(R.id.lblNumero);

        Progress = (ProgressBar)findViewById(R.id.Progress);

        //----------------------------------------------------------------

        Rating = (RatingBar)findViewById(R.id.Rating);
        lblNraiting = (TextView)findViewById(R.id.lblNraiting);
        this.MetodoRaiting();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onProgressChanged(SeekBar arg0, int i, boolean arg2) {
		lblNumero.setText(Integer.toString(i));
        Progress.setProgress(i);
	}

	@Override
	public void onStartTrackingTouch(SeekBar arg0) { }
	@Override
	public void onStopTrackingTouch(SeekBar arg0) {	}
	
	//------------------------------------------------
    public void MetodoRaiting(){
        //Metodo Override del Raiting
        Rating.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                lblNraiting.setText(String.valueOf(v));
            }
        });
    }

}
