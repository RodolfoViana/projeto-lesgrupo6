package com.example;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class EditarInfoActivity extends Activity{
	
	private double desempenho;
	private String combustivelPorKm;
	private String modelo;
	private List<String> valuesList;
	private List<Double> desempenhoList;
	private Spinner spinnerCarros;
	private int checkedItem;
	private static double valorCombustivelLD = 0.0;
	Persistencia persistencia = new Persistencia();
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_info);

        combustivelPorKm = (String) getIntent().getSerializableExtra("desempenho");
        modelo = (String) getIntent().getSerializableExtra("modeloCarro");
        
        try {
        	valuesList = persistencia.recuperarDadosString("modelo.xml");
        } catch (Exception e) {
        	povoaLista();
        }
        
        try {
        	desempenhoList = persistencia.recuperarDadosDouble("desempenho.xml");
        } catch (Exception e) {
        	desempenhos();
        }
        
        if ((combustivelPorKm != null) && (modelo != null)) {
        	addModeloDesempenho();
        	persistencia.salvarDadosString(valuesList, "modelo.xml");
        	persistencia.salvarDadosDouble(desempenhoList, "desempenho.xml");
        }
        
        spinnerCarros = (Spinner) findViewById(R.id.spinner1);
     
        ArrayAdapter<Object> adapter = new ArrayAdapter<Object>(this, android.R.layout.simple_list_item_1, android.R.id.text1, valuesList.toArray());      
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCarros.setAdapter(adapter);
        
        spinnerCarros.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				checkedItem = spinnerCarros.getSelectedItemPosition();
				if (checkedItem != 0) {
					Toast.makeText(EditarInfoActivity.this, "Modelo: " + valuesList.get(checkedItem) + " Desempenho: " + desempenhoList.get(checkedItem) + "km/l", Toast.LENGTH_LONG).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				
			}
		});
        
		Button editarInfoButton = (Button) findViewById(R.id.editarInfoButton);
		editarInfoButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                editarInfoCarro();
                finish();
            }

        });
		
		Button okBt = (Button) findViewById(R.id.editarInfoOkButton);
		okBt.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
            	
            	EditText valorCombustivelL = (EditText) findViewById(R.id.editTextValorComb);
            	if (!valorCombustivelL.getText().toString().equals("")) {
            		valorCombustivelLD = Double.parseDouble(valorCombustivelL.getText().toString());
            	}
            	menuPrincipal();
            	finish();
            }
        });
		
		Button cancelar = (Button) findViewById(R.id.cancelarButton);
		cancelar.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                finish();
            }
        });
		
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
	
	@SuppressWarnings("rawtypes")
	public void verDesempenho(AdapterView parent){
		Toast.makeText(parent.getContext(), "Desempenho do seu carro na cidade é: " +
				 getDesempenho() + "Km/l", Toast.LENGTH_LONG).show();
	}
	
	private void menuPrincipal() {
		Intent menu = new Intent(this, MenuActivity.class);
		menu.putExtra("combustivelPorKm", String.valueOf(desempenhoList.get(this.checkedItem)));
        startActivity(menu);
	}
	
	private void povoaLista() {
		this.valuesList = new ArrayList<String>();
        this.valuesList.add("Carros");
		this.valuesList.add("Celta");
        this.valuesList.add("Gol 1.0");
        this.valuesList.add("Gol 1.6");
        this.valuesList.add("Uno Mille");
        this.valuesList.add("Novo Palio 1.0");
        this.valuesList.add("Siena 1.4");
        this.valuesList.add("Fox 1.0");
        this.valuesList.add("Fox 1.6");
        this.valuesList.add("Ford Ka");
        this.valuesList.add("Onix");
	}
	
	private void desempenhos() {
		this.desempenhoList = new ArrayList<Double>();
		this.desempenhoList.add(0.0);
		this.desempenhoList.add(10.7);
		this.desempenhoList.add(9.8);
		this.desempenhoList.add(9.4);
		this.desempenhoList.add(12.0);
		this.desempenhoList.add(9.4);
		this.desempenhoList.add(12.0);
		this.desempenhoList.add(9.1);
		this.desempenhoList.add(9.4);
		this.desempenhoList.add(11.6);
		this.desempenhoList.add(10.6);
	}
	
	private void addModeloDesempenho() {
        valuesList.add(modelo.toString());
        this.desempenhoList.add(Double.parseDouble(combustivelPorKm));
	}
	
	public static double getValorCombustivel(){
		return valorCombustivelLD;
	}
	
}
