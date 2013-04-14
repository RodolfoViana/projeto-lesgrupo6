package com.example;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class ManualActivity extends TabActivity {
    
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manual);
        
        //TextView txtManual = (TextView) findViewById(R.id.textManual2);
        //txtManual.setText("Informaçoes do Manual");
        
        Button backButton = (Button) findViewById(R.id.voltarNoManual);
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
        
		Resources resources = getResources(); 
		TabHost tabHost = getTabHost(); 
 
		// Map tab
		Intent intentMap = new Intent().setClass(this, ManualMapActivity.class);
		TabSpec tabSpecMap = tabHost.newTabSpec("Marcar Ponto").setIndicator("Marcar Ponto", resources.getDrawable(R.drawable.icon_map)).setContent(intentMap);
		
		Intent intentGps = new Intent().setClass(this, ManualGpsActivity.class);
		TabSpec tabSpecGps = tabHost.newTabSpec("Localizar Ponto").setIndicator("Localizar Ponto", resources.getDrawable(R.drawable.icon_gps)).setContent(intentGps);
		
		Intent intentAdicionaCarro = new Intent().setClass(this, ManualAdicionaCarroActivity.class);
		TabSpec tabSpecAdicionaCarro = tabHost.newTabSpec("Gerenciar Carro").setIndicator("Gerenciar Carro", resources.getDrawable(R.drawable.icon_new_carro)).setContent(intentAdicionaCarro);
		
		tabHost.addTab(tabSpecMap);
		tabHost.addTab(tabSpecGps);
		tabHost.addTab(tabSpecAdicionaCarro);
		
    }
 
}