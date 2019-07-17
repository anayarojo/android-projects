package com.Androidflipper;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {

	ViewFlipper Vf;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Vf = (ViewFlipper)findViewById(R.id.viewFlipper);
		Vf.setOnTouchListener(new ListenerTouchViewFlipper());
		
		
	}

	public class ListenerTouchViewFlipper implements View.OnTouchListener{

        float init_x;

        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {

            switch (motionEvent.getAction()){

                case MotionEvent.ACTION_DOWN:
                    init_x = motionEvent.getX();
                    return true;

                case MotionEvent.ACTION_UP:
                    float distancia = init_x - motionEvent.getX();

                    if(distancia > 0){
                        Vf.setAnimation(inFromRightAnimation());
                        Vf.setOutAnimation(outToLeftAnimation());
                        Vf.showPrevious();
                    }

                    if(distancia < 0){
                        Vf.setAnimation(inFromLeftAnimation());
                        Vf.setAnimation(outToRightAnimation());
                        Vf.showNext();
                    }

                    default: break;

            }

            return false;
        }
    }
	
	
	private Animation inFromRightAnimation(){
        Animation inFromRight = new TranslateAnimation(
          Animation.RELATIVE_TO_PARENT, +1.0f, Animation.RELATIVE_TO_PARENT, 0.0f,
          Animation.RELATIVE_TO_PARENT, 0.0f, Animation.RELATIVE_TO_PARENT, 0.0f
        );

        inFromRight.setDuration(100);
        inFromRight.setInterpolator(new AccelerateInterpolator());

        return inFromRight;
    }

    private Animation outToLeftAnimation(){
        Animation outtoLeft = new TranslateAnimation(
          Animation.RELATIVE_TO_PARENT, 0.0f,
          Animation.RELATIVE_TO_PARENT, -1.0f,
          Animation.RELATIVE_TO_PARENT, 0.0f,
          Animation.RELATIVE_TO_PARENT, 0.0f);

        outtoLeft.setDuration(100);
        outtoLeft.setInterpolator(new AccelerateInterpolator()) ;

        return outtoLeft ;
    }

    private Animation inFromLeftAnimation(){
        Animation inFromLeft = new TranslateAnimation(
          Animation.RELATIVE_TO_PARENT, -1.0f,
          Animation.RELATIVE_TO_PARENT, 0.0f,
          Animation.RELATIVE_TO_PARENT, 0.0f,
          Animation.RELATIVE_TO_PARENT, 0.0f);

        inFromLeft.setDuration(100);
        inFromLeft.setInterpolator(new AccelerateInterpolator());

        return inFromLeft;
    }

    private Animation outToRightAnimation(){
        Animation outtoRight = new TranslateAnimation(
          Animation.RELATIVE_TO_PARENT, 0.0f,
          Animation.RELATIVE_TO_PARENT, +1.0f,
          Animation.RELATIVE_TO_PARENT, 0.0f,
          Animation.RELATIVE_TO_PARENT, 0.0f);

        outtoRight.setDuration(100);
        outtoRight.setInterpolator(new AccelerateInterpolator());

        return outtoRight;
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
