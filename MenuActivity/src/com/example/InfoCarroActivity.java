package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InfoCarroActivity extends Activity {
	
	//private String combustivel = "0.0";
	private String combustivel;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.info_carro);
        
        Button okButton = (Button) findViewById(R.id.carOkButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	//verDesempenho();
            	menu();
            }
        });
		
		Button btMapButton = (Button) findViewById(R.id.carroCancelButton);
		btMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
		
	}
	
	private void menu() {
		Intent menu = new Intent(this, MenuActivity.class);
		calculaCombustivel();
		menu.putExtra("combustivelPorKm", this.combustivel);
        startActivity(menu);
	}
	
	private void calculaCombustivel() {
		//EditText modeloText = (EditText) findViewById(R.id.editText1);
		//EditText tipoCombustivel = (EditText) findViewById(R.id.editText2);
		EditText kmInicial = (EditText) findViewById(R.id.editText3);
		EditText kmFinal = (EditText) findViewById(R.id.editText4);
		EditText combustivelL = (EditText) findViewById(R.id.editText5);
		
		double kmFinalD = Double.parseDouble(kmFinal.getText().toString());
		double kmInicialD = Double.parseDouble(kmInicial.getText().toString());
		double combustivelLD = Double.parseDouble(combustivelL.getText().toString());
		
		double kms = (kmFinalD - kmInicialD);
		this.combustivel = String.valueOf((kms/combustivelLD));
	}
	
	private void verDesempenho(){
		Toast.makeText(InfoCarroActivity.this, "Desempenho do seu carro �: " + this.combustivel + "Km/l", Toast.LENGTH_LONG).show();
	}

}
