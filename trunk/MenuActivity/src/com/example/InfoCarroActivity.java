package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InfoCarroActivity extends Activity {
	
	private String combustivel;
	EditText modeloText;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.info_carro);

        
        Button okButton = (Button) findViewById(R.id.carOkButton);
        okButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	if (validaCamposDeTexto() == true) {
            		spinner();
            		finish();
            	}
            }
        });
		
		Button btMapButton = (Button) findViewById(R.id.carroCancelButton);
		btMapButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	menu();
                finish();
            }
        });
		
	}
	
	private void menu() {
		Intent m = new Intent(this, EditarInfoActivity.class);
        startActivity(m);
	}
	
	private void spinner() {
		Intent menu = new Intent(this, EditarInfoActivity.class);		
		calculaCombustivel();
		menu.putExtra("modeloCarro", String.valueOf(modeloText.getText().toString()));
		menu.putExtra("desempenho", this.combustivel);
        startActivity(menu);
	}
	
	private boolean validaCamposDeTexto() {
		modeloText = (EditText) findViewById(R.id.editTextValorComb);
		EditText tipoCombustivel = (EditText) findViewById(R.id.editText2);
		EditText kmInicial = (EditText) findViewById(R.id.editText3);
		EditText kmFinal = (EditText) findViewById(R.id.editText4);
		EditText combustivelL = (EditText) findViewById(R.id.editText5);
		
		
		if ((modeloText.getText().toString().equals(null)) || (tipoCombustivel.getText().toString().equals(null)) || (kmInicial.getText().toString().equals(null)) || (kmFinal.getText().toString().equals(null)) || (combustivelL.getText().toString().equals(null))) {
			Toast.makeText(InfoCarroActivity.this, "Todos os campos devem ser preenchidos.", Toast.LENGTH_LONG).show();
			return false;
		}
		if ((modeloText.getText().toString().equals("")) || (tipoCombustivel.getText().toString().equals("")) || (kmInicial.getText().toString().equals("")) || (kmFinal.getText().toString().equals("")) || (combustivelL.getText().toString().equals(""))) {
			Toast.makeText(InfoCarroActivity.this, "Todos os campos devem ser preenchidos.", Toast.LENGTH_LONG).show();
			return false;
		}
		return true;
	}
	
	private void calculaCombustivel() {
		
		EditText kmInicial = (EditText) findViewById(R.id.editText3);
		EditText kmFinal = (EditText) findViewById(R.id.editText4);
		EditText combustivelL = (EditText) findViewById(R.id.editText5);
		
		double kmFinalD = Double.parseDouble(kmFinal.getText().toString());
		double kmInicialD = Double.parseDouble(kmInicial.getText().toString());
		double combustivelLD = Double.parseDouble(combustivelL.getText().toString());
		
		
		double kms = (kmFinalD - kmInicialD);
		this.combustivel = String.valueOf((kms/combustivelLD));
	}
	
	
}
