package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ManualMapActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        TextView textview = new TextView(this);
        textview.setText("Explica como marcar o ponto de origem e destino no mapa");
        setContentView(textview);
    }

}
