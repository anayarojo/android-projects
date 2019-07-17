package com.ReconocimientoDeVoz;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.provider.Settings.System;
import android.speech.RecognizerIntent;
import android.widget.Toast;

public class SpeechRecognitionHelper {
	
	public static void run(Activity ownerActivity) {
		// check if there is recognition Activity
		if (isSpeechRecognitionActivityPresented(ownerActivity) == true) {
			// if yes – running recognition
			startRecognitionActivity(ownerActivity);
		} else {
			// if no, then showing notification to install Voice Search
			Toast.makeText(ownerActivity, "Activa speech recognition \"Google Voice Search\"", Toast.LENGTH_LONG).show();
			// start installing process
			installGoogleVoiceSearch(ownerActivity);
		}			
	}
	
	private static boolean isSpeechRecognitionActivityPresented(Activity ownerActivity) {
		try {
			// getting an instance of package manager
			PackageManager pm = ownerActivity.getPackageManager();
			// a list of activities, which can process speech recognition Intent
			List<ResolveInfo> activities = pm.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
			
			if (activities.size() != 0) {	// if list not empty
				return true;				// then we can recognize the speech
			}
		} catch (Exception e) {
			
		}
		
		return false; // we have no activities to recognize the speech
	}
	
	private static void startRecognitionActivity(Activity ownerActivity) {
		
		// creating an Intent with “RecognizerIntent.ACTION_RECOGNIZE_SPEECH” action
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		
		// giving additional parameters:
		// user hint
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Seleeciona la Aplicacion"); 
        
        // setting recognition model, optimized for short phrases – search queries
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);	

        // quantity of results we want to receive hoosing only 1st -  the most relevant 
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);	
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ru-RU");
        
        int VOICE_RECOGNITION_REQUEST_CODE = 1234;
		ownerActivity.startActivityForResult(intent, VOICE_RECOGNITION_REQUEST_CODE );
	}
	
	private static void installGoogleVoiceSearch(final Activity ownerActivity) {
		
		// creating a dialog asking user if he want
		// to install the Voice Search
		Dialog dialog = new AlertDialog.Builder(ownerActivity)
			.setMessage("Para el Reconocimiento es necesario Instalar \"Google Voice Search\"")
			.setTitle("Instalacion de Google Voice Search")
			.setPositiveButton("Instalar", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {	
					try {
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.google.android.voicesearch"));
						intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
						ownerActivity.startActivity(intent);
					 } catch (Exception ex) {
					 }					
				}})
				
			.setNegativeButton("Cancelar", null)
			.create();
		
		dialog.show();		 
	}	
}
