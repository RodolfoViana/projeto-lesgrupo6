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
        double combustivelPorKmD = Double.parseDouble(combustivelPorKm);
        
        TextView text = (TextView) findViewById(R.id.distanciaText);
        text.setText(dist + " m");
		
		Button infoButton = (Button) findViewById(R.id.infoBackButton);
		infoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
	}
	
}
