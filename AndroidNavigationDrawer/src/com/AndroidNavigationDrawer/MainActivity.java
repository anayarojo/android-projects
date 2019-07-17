package com.AndroidNavigationDrawer;


import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.support.v4.app.FragmentActivity;

public class MainActivity extends FragmentActivity {

private ListView navList;
	
	String[] names;
	ArrayAdapter<String> adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.navList = (ListView) findViewById(R.id.left_drawer);
		 
        // Load an array of options names
        names = getResources().getStringArray(R.array.nav_options);
 
        // Set previous array as adapter of the list
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names);
        navList.setAdapter(adapter);
        
        ClickList();
		
	}

	public void ClickList(){
		navList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int iPosicion, long l) {

                Toast.makeText(getApplicationContext(),names[iPosicion]+" - "+iPosicion,Toast.LENGTH_SHORT).show();

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
