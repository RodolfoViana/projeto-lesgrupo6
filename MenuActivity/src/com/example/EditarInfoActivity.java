package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class EditarInfoActivity extends Activity{
	
	private double desempenho;
	 private Button btnSubmit;
	 private TextView visuDesempenho;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_info);
        
        //visuDesempenho = (TextView) findViewById(R.id.VerDesempenho);
        
        final Spinner spinnerCarros = (Spinner) findViewById(R.id.spinner1);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.
        		createFromResource(this, R.array.lista_carros, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        
        spinnerCarros.setAdapter(adapter);
        
        spinnerCarros.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView parent, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				if(parent.getSelectedItem().toString().equals("Carros")){
					setConsumo(0.0);
				}else if(parent.getSelectedItem().toString().equals("Celta")){
					setConsumo(10.7);
					 verDesempenho(parent);
				}else if(parent.getSelectedItem().toString().equals("Gol 1.0")){
					setConsumo(9.8);
					verDesempenho(parent);
				}else if(parent.getSelectedItem().toString().equals("Gol 1.6")){
					setConsumo(9.4);
					verDesempenho(parent);
				}else if(parent.getSelectedItem().toString().equals("Uno Mille")){
					setConsumo(12.0);
					verDesempenho(parent);
				}else if(parent.getSelectedItem().toString().equals("Novo Palio 1.0")){
					setConsumo(9.4);
					verDesempenho(parent);
				}else if(parent.getSelectedItem().toString().equals("Siena 1.4")){
					setConsumo(12.0);
					verDesempenho(parent);
				}else if(parent.getSelectedItem().toString().equals("Fox 1.0")){
					setConsumo(9.1);
					verDesempenho(parent);
				}else if(parent.getSelectedItem().toString().equals("Fox 1.6")){
					setConsumo(9.4);
					verDesempenho(parent);
				}else if(parent.getSelectedItem().toString().equals("Ford Ka")){
					setConsumo(11.6);
					verDesempenho(parent);
				}else if(parent.getSelectedItem().toString().equals("Onix")){
					setConsumo(10.6);
					verDesempenho(parent);
				}
				
				 
				// visuDesempenho.setText("" + getDesempenho());
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
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
	
	public double getDesempenho(){
		return desempenho;
	}
	
	public void setConsumo(double consumo){
		this.desempenho = consumo;
	}
	
	public void verDesempenho(AdapterView parent){
		Toast.makeText(parent.getContext(), "Desempenho do seu carro na cidade é: " +
				 getDesempenho() + "Km/l", Toast.LENGTH_LONG).show();
	}
	

}
