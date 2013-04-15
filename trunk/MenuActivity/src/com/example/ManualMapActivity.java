package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.ScrollView;
import android.widget.TextView;

public class ManualMapActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        ScrollView scroll = new ScrollView(this);
 
        TextView textview = new TextView(this);
        textview.setGravity(Gravity.CENTER_HORIZONTAL);
        textview.setText("Para saber a quantidade de combust�vel necess�rio de um ponto a " +
        		"outro, � necess�rio marcar os pontos no mapa. Para marca-los basta tocar no " +
        		"mapa e segurar por alguns segundos, tanto para o ponto de origem, quanto para" +
        		" o de destino. Tamb�m � poss�vel apag�-los clicando em 'Apagar Pontos'\n\n� " +
        		"possivel ver as informa��es de distancia entre pontos, desempenho do carro, " +
        		"quantidade de combust�vel necess�rio para percorrer tal distancia e o valor " +
        		"em reais que ser� gasto para isso clicando no bot�o 'Informa��es' no mapa.\n\n" +
        		"Tamb�m � poss�vel salvar pontos como favoritos, basta marca-los e clicar em " +
        		"'Salvar'. Para ver os pontos que j� foram favoritados, clique em 'Pontos Salvos'.");
        
        scroll.addView(textview);
		setContentView(scroll);        
    }

}
