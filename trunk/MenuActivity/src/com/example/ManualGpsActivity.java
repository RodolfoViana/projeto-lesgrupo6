package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ManualGpsActivity extends Activity {
	
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        TextView textview = new TextView(this);
        textview.setText("Como usar o marcar pontos com gps");
        setContentView(textview);
    }

}
