package com.example;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class ModelosActivity extends Activity {
	
	private int checkedItem;
	private List<Double> desempenhos;
	String combustivelPorKm = null;
	String modelo = null;
	int cont;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.lista_modelos);
        
        combustivelPorKm = (String) getIntent().getSerializableExtra("desempenho");
        modelo = (String) getIntent().getSerializableExtra("modeloCarro");
        
        final ListView lista = (ListView) findViewById(R.id.listView1);
        lista.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
        
        final List<String> valuesList = new ArrayList<String>();
        valuesList.add("Celta");
        valuesList.add("Gol 1.0");
        valuesList.add("Gol 1.6");
        valuesList.add("Uno Mille");
        
//        final String[] values = new String[] { "Celta", "Gol 1.0", "Gol 1.6",
//        		  "Uno Mille", "Novo Palio 1.0", "Siena 1.4", "Fox 1.0", "Fox 1.6",
//        		  "Ford Ka", "Onix" };
        //cont = values.length;
        double[] desemp = new double[] {10.7, 9.8, 9.4, 12.0, 9.4, 12.0, 9.1, 9.4, 11.6, 10.6};
        desempenhos = new ArrayList<Double>();
        
        for (int i = 0; i < desemp.length; i++) {
			desempenhos.add(desemp[i]);
		}

        if ((combustivelPorKm != null) && (modelo != null)) {
        	//adapter.add(modelo);
        	//values[cont] = modelo;
        	valuesList.add(modelo.toString());
        	desempenhos.add(Double.parseDouble(combustivelPorKm.toString()));
        	//adapter.notifyDataSetChanged();
        }

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_single_choice, android.R.id.text1, values);
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_single_choice, android.R.id.text1, valuesList.toArray());      
        lista.setAdapter(adapter); 
        
        lista.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> myAdapter, View myView, int myItemInt, long mylng) {
              //String selectedFromList =(String) (lista.getItemAtPosition(myItemInt));
				checkedItem = lista.getCheckedItemPosition();
				//Toast.makeText(ModelosActivity.this, "Modelo: " + values[checkedItem] + " Desempenho: " + desempenhos.get(checkedItem), Toast.LENGTH_LONG).show();
				Toast.makeText(ModelosActivity.this, "Modelo: " + valuesList.get(checkedItem) + " Desempenho: " + desempenhos.get(checkedItem), Toast.LENGTH_LONG).show();
            } 
		});
        
        Button okBt = (Button) findViewById(R.id.okBtLista);
        okBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                menu();
            }
        });
        
        Button cancelar = (Button) findViewById(R.id.cancelarListaButton);
		cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
	}
	
	private void menu() {
		Intent menu = new Intent(this, MenuActivity.class);
		menu.putExtra("combustivelPorKm", String.valueOf(desempenhos.get(this.checkedItem)));
        startActivity(menu);
	}

}
