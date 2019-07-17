package com.AndroidText;

import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

	private EditText txtTexto, txtPassword, txtDate, txtEmail, txtTelefono, txtTime;
	private TextView lblText, lblFecha, lblEmail, lblTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		txtTexto = (EditText)findViewById(R.id.txtTexto);
        txtPassword = (EditText)findViewById(R.id.txtPassword);
        txtDate = (EditText)findViewById(R.id.txtDate);
        txtEmail = (EditText)findViewById(R.id.txtMail);
        txtTelefono = (EditText)findViewById(R.id.txtTelefono);
        txtTime = (EditText)findViewById(R.id.txtTime);

        lblText = (TextView)findViewById(R.id.lblPlaintext);
        lblFecha = (TextView)findViewById(R.id.lblDate);
        lblEmail = (TextView)findViewById(R.id.lblEmail);
        lblTime = (TextView)findViewById(R.id.lblTime);

        //Evento addTextChangedListener: Sirve para cuando tu ejecutas el evento en el EditText
        //Ya sea para cuando escribes sobre el, despues o antes.

        //Solo Letras en el PlainText tambien se puede utilizar la misma funcion en el PersonName
        txtTexto.addTextChangedListener(new TextWatcher() {
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                String Texto = txtTexto.getText().toString();

                if (!Texto.toString().matches("[a-zA-Z ]+")) {

                    txtTexto.setError("Acepta Solo Letras.");

                } else {

                    lblText.setText("Plain Text Aceptado");

                }

            }
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
            public void afterTextChanged(Editable editable) {
            }
        });


        //Validando la entrada de la Fecha
        txtDate.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                try{

                String Fecha = txtDate.getText().toString();

                String FormatoFecha = "yyyy-M-d";
                SimpleDateFormat Df = new SimpleDateFormat(FormatoFecha);
                Date date = Df.parse(Fecha);

                    lblFecha.setText("Date. Fecha Valida");

                }catch(Exception ex){
                    lblFecha.setText("Date");
                    txtDate.setError("Fecha Invalida");
                }
            }
            public void afterTextChanged(Editable editable) {
            }
        });


        //Validando E-Mail de 2 maneras
        txtEmail.addTextChangedListener(new TextWatcher(){
            public void afterTextChanged(Editable s){
            }
            public void beforeTextChanged(CharSequence s, int start, int count, int after){
            }
            public void onTextChanged(CharSequence s, int start, int before, int count){
                String email = txtEmail.getText().toString();
                //String SintaxisEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                String Servicio = "hotmail";

                String SintaxisEmail = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@["+Servicio+"]+(\\.[c]+[o]+[m])$";
                if (email.matches(SintaxisEmail)) {
                    lblEmail.setText("E-Mail. Correcto");
                } else {
                    txtEmail.setError("E-Mail. Incorrecto");
                }
            }
        });
        
        
      //Validando la captura del Time
        txtTime.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {

                String stringTime = txtTime.getText().toString();
                String Time = "(1[012]|[1-9]):[0-5][0-9]:[0-5][0-9](\\s)?(?i)(am|pm)";

                if (stringTime.matches(Time)) {
                    lblTime.setText("Time. Correcto");
                } else {
                    txtTime.setError("Time. Incorrecto");
                }

            }
            public void afterTextChanged(Editable editable) {
            }
        });
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
