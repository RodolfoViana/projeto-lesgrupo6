package com.example;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ManualActivity extends Activity {
    
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual);
        
        TextView txtManual = (TextView) findViewById(R.id.textManual2);
        txtManual.setText("Informaçoes do Manual");
        
        Button backButton = (Button) findViewById(R.id.voltarNoManual);
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//itemizedOverlay.onTouchEvent(event, mapView);
				finish();
			}
		});
        
        
    }
 
}