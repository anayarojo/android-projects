package com.NavegadorAndroid;

import android.os.Bundle;
import android.app.Activity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends Activity {

	EditText txtURL;
    WebView Web;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtURL = (EditText)findViewById(R.id.txtURL);

        Web = (WebView)findViewById(R.id.Web);
        Web.getSettings().setJavaScriptEnabled(true);
        Web.setWebViewClient(new Cliente());
        Web.loadUrl("http://www.google.com.mx");
		
	}

	//Clase para determinar el cliente Web del navegador en sistema operativo android
    class Cliente extends WebViewClient {
        public boolean SobreEscribir(WebView wv, String url){
            Web.loadUrl(url);
            return true;
        }
    }
    
  //Evento para cuando se le de click en el boton de desvolver en la consola de android
    //el navegador retorne hacia la pagina anterior
    public boolean onKeyDown(int Codigo, KeyEvent evt){
        if(Codigo == KeyEvent.KEYCODE_BACK && Web.canGoBack()){
            Web.goBack();
            return true;
        }
        return super.onKeyDown(Codigo, evt);
    }

    //Evento del Boton Ir
    public void evtIr(View view){

        if(txtURL.getText().toString().contains("http://www.")){
            Web.loadUrl(txtURL.getText().toString());
            txtURL.setText(txtURL.getText().toString());
        }else{
            if(txtURL.getText().toString().contains("www.")){
                Web.loadUrl("http://"+txtURL.getText().toString());
                txtURL.setText("http://"+txtURL.getText().toString());
            }
        }
        if(txtURL.getText().toString().contains("http://www.")==false){
            Web.loadUrl("http://www."+txtURL.getText().toString());
            txtURL.setText("http://www."+txtURL.getText().toString());
        }
    }

    public void evtClickURL(View view){
        txtURL.setText("");
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
