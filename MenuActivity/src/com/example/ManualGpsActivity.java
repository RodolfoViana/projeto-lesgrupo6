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
        textview.setText("Como usar o marcar pontos com gps");
        
        scroll.addView(textview);
		setContentView(scroll);
    }

}
