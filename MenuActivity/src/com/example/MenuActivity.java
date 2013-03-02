package com.example;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity{
	
	String combustivelPorKm;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        
        combustivelPorKm = (String) getIntent().getSerializableExtra("combustivelPorKm");
        if (this.combustivelPorKm == null) {
        	combustivelPorKm = "0.0";
        }
        
		//TESTANDO O COMITT !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		Button btMapButton = (Button) findViewById(R.id.mapButton);
		btMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                telaMapa();
            }
        });
		
		Button editarInfoButton = (Button) findViewById(R.id.menuEditarInfo);
		editarInfoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                telaInfo();
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

}
