package com.AndroidMenuOpciones;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends Activity {

	private static final int MNU_OPC1 = 1;
	private static final int MNU_OPC2 = 2;
	private static final int MNU_OPC3 = 3;
	private static final int MNU_OPC4 = 4;

	private static final int SMNU_OPC1 = 31;
	private static final int SMNU_OPC2 = 32;

	private static final int GRUPO_MENU_1 = 101;

	private int opcionSeleccionada = 0;

	private TextView lblMensaje;
	private CheckBox chkMenuExtendido;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		lblMensaje = (TextView)findViewById(R.id.lblMensaje);
        chkMenuExtendido = (CheckBox)findViewById(R.id.ChkMenuExtendido);
		
	}
	
	@Override
    public boolean onPrepareOptionsMenu(Menu menu){
	    menu.clear();

	    if(chkMenuExtendido.isChecked()) 
	    	construirMenu(menu, true);
	    else 
	    	construirMenu(menu, false);

	    return super.onPrepareOptionsMenu(menu);
    }
	
	private void construirMenu(Menu menu, boolean extendido){
		//1) Método 1: Inflar menu XML
		//getMenuInflater().inflate(R.menu.activity_main, menu);

		//2) Método 2: Construir menu por código
        menu.add(Menu.NONE, MNU_OPC1, Menu.NONE, "Usuario").setIcon(R.drawable.usuario);
        
        menu.add(Menu.NONE, MNU_OPC2, Menu.NONE, "Agregar").setIcon(R.drawable.agregar);
        
        SubMenu smnu = menu.addSubMenu(Menu.NONE, MNU_OPC3, Menu.NONE, "Carrito").setIcon(R.drawable.carrito);
        smnu.add(GRUPO_MENU_1, SMNU_OPC1, Menu.NONE, "Comprar");
        smnu.add(GRUPO_MENU_1, SMNU_OPC2, Menu.NONE, "Eliminar");
        
        smnu.setGroupCheckable(GRUPO_MENU_1, true, true);
        
        if(extendido)
        	menu.add(Menu.NONE, MNU_OPC4, Menu.NONE, "Eliminar").setIcon(R.drawable.eliminar);
        
        if(opcionSeleccionada == 1)
        	smnu.getItem(0).setChecked(true);
        else if(opcionSeleccionada == 2)
        	smnu.getItem(1).setChecked(true);
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case MNU_OPC1:
            lblMensaje.setText("Opcion Usuarios pulsada!");
            return true;
        case MNU_OPC2:
        	lblMensaje.setText("Opcion Agregar pulsada!");;
            return true;
        case MNU_OPC3:
        	lblMensaje.setText("Opcion Carrito pulsada!");;
            return true;
        case SMNU_OPC1:
        	lblMensaje.setText("Opcion Comprar pulsada!");
        	opcionSeleccionada = 1;
        	item.setChecked(true);
            return true;
        case SMNU_OPC2:
        	lblMensaje.setText("Opcion Eliminar pulsada!");
        	opcionSeleccionada = 2;
        	item.setChecked(true);
            return true;
        case MNU_OPC4:
        	lblMensaje.setText("Opcion Eliminar pulsada!");
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
