package com.example;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InformacoesActivity extends Activity{
	
	
	double combustivelNecessario;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.info);
        
        
        
        String dist = (String) getIntent().getSerializableExtra("distancia");
        String combustivelPorKm = (String) getIntent().getSerializableExtra("combustivelPorKm");
        
        TextView text = (TextView) findViewById(R.id.distanciaText);
        TextView text2 = (TextView) findViewById(R.id.complementarTextInfo);
        
        
        
        if (dist == null) {
        	text.setText("Pontos não marcados");
        	text2.setText(" ");
        	
        } else {
        	text.setText(format(dist) + " m");
        	text2.setText("ou");
        	
        }
        
        TextView distEmKm = (TextView) findViewById(R.id.textDistEmKm);
        String distanciaEmKm = distanciaEmKm(dist);
        distEmKm.setText(distanciaEmKm);
        
        TextView textDesempenho = (TextView) findViewById(R.id.textDesempenhoCarro);
        if (combustivelPorKm == null) {
        	textDesempenho.setText("Modelo de carro não selecionado");
        } else {
        	textDesempenho.setText(combustivelPorKm + " km/l");
        }
        
        TextView textCombustivel = (TextView) findViewById(R.id.textCombustivelCarro);
        String comb = calcularCombustivelNecessário(combustivelPorKm, dist);
        textCombustivel.setText(comb);
        EditarInfoActivity editIndo = new EditarInfoActivity();
        TextView textGastoDeCombustivel = (TextView) findViewById(R.id.valorGastoDeCombustivel);
        textGastoDeCombustivel.setText(format(editIndo.getValorCombustivel()*getCombustivelNecessario()));
		
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
		return format(dist/1000) + " km";
		//return (String.valueOf(dist/1000) + " km");
	}
	
//	private String calculaGastoEmReais(){
//		double value = infoCarro.getValorCombustivel()*getCombustivelNecessario();
//		return value + "";
//	}
	
	private String calcularCombustivelNecessário(String combustivel, String distancia) {
		if ((distancia == null) || combustivel == null) {
			return "";
		}
		
		double dist = Double.parseDouble(distancia);
		double kmPorLitro = Double.parseDouble(combustivel);
		double distEmKm = dist/1000;
		combustivelNecessario = distEmKm/kmPorLitro;
		return format(distEmKm/kmPorLitro) + " l";
		//return (String.valueOf(distEmKm/kmPorLitro) + " l");
	}
	
	private double getCombustivelNecessario(){
		return combustivelNecessario;
	}
	
	@SuppressLint("DefaultLocale")
	private String format(double numero) {
		return String.format("%.3f", numero);
	}
	
	@SuppressLint("DefaultLocale")
	private String format(String numero) {
		return String.format("%.3f", Double.parseDouble(numero));
	}
	
}
