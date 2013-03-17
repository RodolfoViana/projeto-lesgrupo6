package com.example;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class TelaApresentacaoActivity extends Activity implements Runnable{

	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);


		Handler h = new Handler();
		h.postDelayed(this, 3000);
	}
	
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		startActivity(new Intent(this, MenuActivity.class));
		finish();
	}

}
