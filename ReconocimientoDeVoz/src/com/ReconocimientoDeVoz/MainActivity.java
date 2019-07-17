package com.ReconocimientoDeVoz;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends Activity{

	private static final int VOICE_RECOGNITION_REQUEST_CODE = 1234;
	private ListView mList;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    // Inflate our UI from its XML layout description.
	    setContentView(R.layout.activity_main);

	    // Get display items for later interaction
	    Button speakButton = (Button) findViewById(R.id.speakButton);
	    mList = (ListView) findViewById(R.id.list);

	    // Check to see if a recognition activity is present
	    PackageManager pm = getPackageManager();
	    List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
	}

	public void evtReconocer(View view){
		startVoiceRecognitionActivity();
	}
	
	private void startVoiceRecognitionActivity() {
	    Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
	    intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
	    intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Reconocimiento de Voz");
	    startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	    if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
	        // Fill the list view with the strings the recognizer thought it could have heard
	        ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
	        mList.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, matches));
	    }

	    super.onActivityResult(requestCode, resultCode, data);
	}	

}