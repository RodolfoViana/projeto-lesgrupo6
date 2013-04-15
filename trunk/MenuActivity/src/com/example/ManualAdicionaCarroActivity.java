package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ScrollView;
import android.widget.TextView;

public class ManualAdicionaCarroActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		ScrollView scroll = new ScrollView(this);
		
		TextView textview = new TextView(this);
		textview.setGravity(Gravity.CENTER_HORIZONTAL);
		//textview.setText("Manual de como se seleciona e adiciona um novo modelo de carro");
		textview.setText("Para selecionar um carro ja cadastrado na lista, basta escolher " +
				"uma das opçoes presentes em 'Escolher modelo do Carro na lista'.\n\n Para" +
				"cadastrar um carro, basta clicar em 'Adicionar Carro'. Uma forma de " +
				"preencher o fomulário do novo carro é fazer um teste com seu veiculo. " +
				"Por exemplo, marcar no aplicativo a quilometragem atual, colocar uma" +
				" determinada quantidade de combustivel no carro e informar ao app. " +
				"Quando o combustivel acabar, marcar a quilometragem final. Com isso, o app" +
				"calculará qual o desempenho do seu carro por km.");
		
		scroll.addView(textview);
		setContentView(scroll);
	}

}
