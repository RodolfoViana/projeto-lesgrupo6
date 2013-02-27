package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class EditarInfoActivity extends Activity{
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_info);
        
		Button modelosButton = (Button) findViewById(R.id.listaModelosButton);
		modelosButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                listaModelos();
            }
        });
		
		Button editarInfoButton = (Button) findViewById(R.id.editarInfoButton);
		editarInfoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                editarInfoCarro();
            }

        });
		
		Button cancelar = (Button) findViewById(R.id.cancelarButton);
		cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
		
	}
	
	private void listaModelos() {
		Intent lista = new Intent(this, ModelosActivity.class);
        startActivity(lista);
	}
	
	private void editarInfoCarro() {
		Intent carro = new Intent(this, InfoCarroActivity.class);
        startActivity(carro);
	}

}
