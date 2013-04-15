package com.example;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MenuActivity extends Activity{
	
	String combustivelPorKm;
	Button voltarDoManualButton;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        Button voltarDoManualButton = (Button) findViewById(R.id.voltarNoManual);
        Button btMapButton = (Button) findViewById(R.id.mapButton);
        Button manualButton = (Button) findViewById(R.id.button3);
        Button editarInfoButton = (Button) findViewById(R.id.menuEditarInfo);
        
        combustivelPorKm = (String) getIntent().getSerializableExtra("combustivelPorKm");
        
		
		btMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                telaMapa();
            }
        });
		
		
		manualButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                telaManual();
            	
            }
        });
		
		
		editarInfoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                telaInfo();
                finish();
            }
        });
	}
	
	private void telaMapa() {
		Intent mapa = new Intent(this, GMapsActivity.class);
		mapa.putExtra("combustivelPorKm", combustivelPorKm);
        startActivity(mapa);
	}
	
	private void telaInfo() {
		Intent info = new Intent(this, EditarInfoActivity.class);
        startActivity(info);
	}
	
	private void telaManual() {	
		Intent i = new Intent(this, ManualActivity.class);
        startActivity(i);
	}

}
