package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ManualAdicionaCarroActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        TextView textview = new TextView(this);
        textview.setText("Manual de como se seleciona e adiciona um novo modelo de carro");
        setContentView(textview);
    }

}
