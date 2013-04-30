package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class PontosActivity extends Activity{
	
	String mostrarPontos;
	Button voltarDoManualButton;
	private Spinner spinnerCarros;
	private int checkedItem;
	HashSet<String> stringGeoPoint = new HashSet<String>();
	private List<String> valuesList = new ArrayList<String>();
	HashMap<String, String> mapa;
	String pontoEscolhido = "";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_pontos);
        
        mapa = new HashMap<String, String>();

//        ArrayList<GeoPoint> geopointsList = (ArrayList<GeoPoint>) getIntent().getSerializableExtra("geopointsList");
        mostrarPontos = (String) getIntent().getSerializableExtra("mostrarPontos");
        
        String aux = mostrarPontos;
        
        for ( int i= 0; i < aux.split("\t").length;  i++){     	
        	String a = mostrarPontos.split("\t")[i].split("/")[1];
        	String b = mostrarPontos.split("\t")[i].split("/")[0];
        	stringGeoPoint.add(b);
        	mapa.put(b, a);
        }
        
        
        valuesList.add("Pontos Marcados");
        String aux2 = ""; 
        String aux3;
        int cont = 1;
        Iterator<String> it = stringGeoPoint.iterator();
        while (it.hasNext()){
        	aux3 = it.next();
        	aux2 += cont + "\t" + aux3 + "\n";
        	valuesList.add(aux3);
        	cont++;
        }
        
        
        spinnerCarros = (Spinner) findViewById(R.id.pontosSpinner);
        
        
        
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valuesList.toArray());      
//        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(PontosActivity.this, 0);   
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarros.setAdapter(adapter);
        
        spinnerCarros.setOnItemSelectedListener(new OnItemSelectedListener() {
        	
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				checkedItem = spinnerCarros.getSelectedItemPosition();
				if (checkedItem != 0) {
					pontoEscolhido = valuesList.get(checkedItem);
					Toast.makeText( PontosActivity.this, valuesList.get(checkedItem), Toast.LENGTH_LONG).show();
//					Toast.makeText(EditarInfoActivity.this, "Modelo: " + valuesList.get(checkedItem) + " Desempenho: " + desempenhoList.get(checkedItem) + "km/l", Toast.LENGTH_LONG).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
        
        
//        TextView text = (TextView) findViewById(R.id.pontosSalvos);
//        text.setText(aux2);
        
        Button btMapButton = (Button) findViewById(R.id.voltarPontosFavoritos);
		btMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	salvarPontos(pontoEscolhido);
            }
        });
		
	}
	
	private void salvarPontos(String pontosSalvos) {
		Intent pontoSalvo = new Intent(this, GMapsActivity.class);
			String aux;
			String resp = "";
			
			if (mapa.keySet().size() != 0){	
				Iterator<String> it = mapa.keySet().iterator();
				while(it.hasNext()){
					aux = it.next();
					resp += aux + "/" + mapa.get(aux) + "\t";
				}
				
			}
			
			if (checkedItem != 0){
				resp += 1 + ":" +pontoEscolhido + "/" + mapa.get(pontoEscolhido) + "\t";
			} else {
				resp += 0 + ":" +pontoEscolhido + "/" + mapa.get(pontoEscolhido) + "\t";
			}
			
			
			
		pontoSalvo.putExtra("pontoEscolhido", resp);
//		pontoSalvo.putExtra("pontoEscolhido", valuesList.get(checkedItem));
		startActivity(pontoSalvo);
		finish();
	}

}
