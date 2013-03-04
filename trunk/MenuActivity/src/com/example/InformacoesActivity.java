package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InformacoesActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        
        String dist = (String) getIntent().getSerializableExtra("distancia");
        String combustivelPorKm = (String) getIntent().getSerializableExtra("combustivelPorKm");
        
        TextView text = (TextView) findViewById(R.id.distanciaText);
        if (dist == null) {
        	text.setText(" ");
        } else {
        	text.setText(dist + " m");
        }
        
        TextView distEmKm = (TextView) findViewById(R.id.textDistEmKm);
        String distanciaEmKm = distanciaEmKm(dist);
        distEmKm.setText(distanciaEmKm);
        
        TextView textDesempenho = (TextView) findViewById(R.id.textDesempenhoCarro);
        if (combustivelPorKm == null) {
        	textDesempenho.setText(" ");
        } else {
        	textDesempenho.setText(combustivelPorKm + " km/l");
        }
        
        TextView textCombustivel = (TextView) findViewById(R.id.textCombustivelCarro);
        String comb = calcularCombustivelNecessário(combustivelPorKm, dist);
        textCombustivel.setText(comb);
		
		Button infoButton = (Button) findViewById(R.id.infoBackButton);
		infoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
	}
	
	private String distanciaEmKm(String distancia) {
		if (distancia == null) {
			return "";
		}
		double dist = Double.parseDouble(distancia);
		return (String.valueOf(dist/1000) + " km");
	}
	
	private String calcularCombustivelNecessário(String combustivel, String distancia) {
		if ((distancia == null) || combustivel == null) {
			return "";
		}
		
		double dist = Double.parseDouble(distancia);
		double kmPorLitro = Double.parseDouble(combustivel);
		double distEmKm = dist/1000;
		
		return (String.valueOf(distEmKm/kmPorLitro) + " l");
	}
	
}
