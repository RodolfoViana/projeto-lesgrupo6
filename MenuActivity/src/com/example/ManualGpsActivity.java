package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ScrollView;
import android.widget.TextView;

public class ManualGpsActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        ScrollView scroll = new ScrollView(this);
        
        TextView textview = new TextView(this);
        textview.setGravity(Gravity.CENTER_HORIZONTAL);
        textview.setText("Para saber a quantidade de combustível necessário de um ponto a " +
        		"outro a partir de onde você está, é necessário marcar oo ponto de destino no mapa, " +
        		"já que o ponto de origem é definido pelo GPS. Para marca-lo basta tocar no " +
        		"mapa e segurar por alguns segundos. Também é possível apagá-lo clicando em " +
        		"'Apagar Pontos'\n\nÉ " +
        		"possivel ver as informações de distancia entre pontos, desempenho do carro, " +
        		"quantidade de combustível necessário para percorrer tal distancia e o valor " +
        		"em reais que será gasto para isso clicando no botão 'Informações' no mapa.\n\n" +
        		"Também é possível salvar pontos como favoritos, basta marca-los e clicar em " +
        		"'Salvar'. Para ver os pontos que já foram favoritados, clique em 'Pontos Salvos'.");
        
        scroll.addView(textview);
		setContentView(scroll);
    }

}
