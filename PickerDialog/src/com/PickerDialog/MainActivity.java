package com.PickerDialog;

import java.util.Calendar;

import android.os.Bundle;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.view.Menu;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends Activity {

	TextView lblPickerDate, lblPickerTime;

    private Calendar cal = Calendar.getInstance();
    private int year;
    private int mes;
    private int dia;
    private int Hora;
    private int Minutos;

    private int AmPm;

    static final int DATE_DIALOG_ID = 0;
    static final int TIME_DIALOG_ID = 1;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lblPickerDate = (TextView)findViewById(R.id.lblPickerDate);
        lblPickerTime = (TextView)findViewById(R.id.lblPickerTime);

        year = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH)+1;
        dia = cal.get(Calendar.DAY_OF_MONTH);
        Hora = cal.get(Calendar.HOUR);
        Minutos = cal.get(Calendar.MINUTE);

        AmPm = cal.get(Calendar.AM_PM);
		
	}
	
	public void evtPickerDate(View view){
        showDialog(DATE_DIALOG_ID);
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int selectedYear, int selectedMonth, int selectedDay) {
            year = selectedYear;
            mes = selectedMonth + 1;
            dia = selectedDay;

            String Ndia;
            if(dia < 10){
                Ndia = "0"+Integer.toString(dia);
            }else{
                Ndia =  Integer.toString(dia);
            }

            String Nmes;
            if(mes < 10 ){
                Nmes = "0"+Integer.toString(mes);
            }else{
                Nmes = Integer.toString(mes);
            }

            lblPickerDate.setText(new StringBuilder().append(Ndia).append("-").append(Nmes).append("-").append(year).append(" "));
        }
    };


    //------------------------------------------------------------------------------------------------------------------

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this, datePickerListener, year, mes, dia);

            case TIME_DIALOG_ID:
                return new TimePickerDialog(this, timePickerListener, Hora, Minutos, false);
        }
        return null;
    }

    //------------------------------------------------------------------------------------------------------------------


    public void evtPickerTime(View view){
        showDialog(TIME_DIALOG_ID);
    }

    private TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker timePicker, int selectedHora, int selectedMinutos) {
           Hora = selectedHora;
           Minutos = selectedMinutos;

            String Nhora;
            if(Hora < 10){
                Nhora = "0"+Integer.toString(Hora);
            }else{
                Nhora =  Integer.toString(Hora);
            }

            String Nminutos;
            if(Minutos < 10 ){
                Nminutos = "0"+Integer.toString(Minutos);
            }else{
                Nminutos = Integer.toString(Minutos);
            }

            Calendar setCalTime = Calendar.getInstance();
            setCalTime.set(Calendar.HOUR,Hora);
            setCalTime.set(Calendar.MINUTE,Minutos);

            String AmPm="";
            if (setCalTime.get(Calendar.AM_PM) == Calendar.PM){
                AmPm = "am";
            }

            if (setCalTime.get(Calendar.AM_PM) == Calendar.AM){
                AmPm = "pm";
            }

            lblPickerTime.setText(new StringBuilder().append(Nhora).append("-").append(Nminutos).append(" ").append(AmPm));

        }
    };

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
