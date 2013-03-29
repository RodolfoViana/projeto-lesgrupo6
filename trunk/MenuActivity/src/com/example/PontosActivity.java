package com.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class PontosActivity extends Activity{
	
	String mostrarPontos;
	Button voltarDoManualButton;
	HashSet<String> stringGeoPoint = new HashSet<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.mostrar_pontos);

//        ArrayList<GeoPoint> geopointsList = (ArrayList<GeoPoint>) getIntent().getSerializableExtra("geopointsList");
        mostrarPontos = (String) getIntent().getSerializableExtra("mostrarPontos");
        
        String aux = mostrarPontos;
        
        for ( int i= 0; i < aux.split("\t").length;  i++){
        	stringGeoPoint.add(mostrarPontos.split("\t")[i]);
        }
        
        String aux2 = ""; 
        int cont = 1;
        Iterator<String> it = stringGeoPoint.iterator();
        while (it.hasNext()){
        	aux2 += cont + "\t" + it.next() + "\n";
        	cont++;
        }
        
        TextView text = (TextView) findViewById(R.id.pontosSalvos);
        text.setText(aux2);
        
        Button btMapButton = (Button) findViewById(R.id.voltarPontos);
		btMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
		
	}

}
