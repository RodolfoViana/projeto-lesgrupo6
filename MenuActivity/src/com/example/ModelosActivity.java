package com.example;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;


public class ModelosActivity extends Activity {
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_modelos);
        
        ListView lista = (ListView) findViewById(R.id.listView1);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        String[] values = new String[] { "Gol", "i30", "Uno",
        		  "Fiesta", "Golf", "Celta", "Corsa", "Palio",
        		  "Strada", "Fox" };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, android.R.id.text1, values);
        lista.setAdapter(adapter); 
        
        Button cancelar = (Button) findViewById(R.id.cancelarListaButton);
		cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
	}

}
