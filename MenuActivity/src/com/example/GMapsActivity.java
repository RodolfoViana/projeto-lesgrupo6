package com.example;

import java.util.List;


import android.R.drawable;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

public class GMapsActivity extends MapActivity {

	private MapView mapView;
	private AddItemizedOverlay itemizedOverlay;
	MapController mc = mapView.getController();
	private int contMarcador = 0;
	String combustivelPorKm;
	
	//GPS
	private LocationManager locationManager;
	private LocationListener locationListener;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final Drawable drawable = this.getResources().getDrawable(
				R.drawable.ic_launcher);
		
		combustivelPorKm = (String) getIntent().getSerializableExtra("combustivelPorKm");

		Button bt = (Button) findViewById(R.id.button1);
		bt.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
//				// itemizedOverlay = new AddItemizedOverlay(drawable,
//				// GMapsActivity.this);
//
//				mapView.getOverlays().clear();
//				mapView.invalidate();
//				// mapView.getOverlays().notify();
//				mapView.setClickable(true);
//				// mapView.getOverlays().add(itemizedOverlay);
//				// mapView.;

//				itemizedOverlay.removerOverlay();
//				mapOverlays = mapView.getOverlays()
//				finish();
				telaMapa(drawable);
			}
		});
		
		Button backButton = (Button) findViewById(R.id.voltarButton);
		backButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//itemizedOverlay.onTouchEvent(event, mapView);
				finish();
			}
		});
		
		Button infoButton = (Button) findViewById(R.id.infoButton);
		infoButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				info();
			}
		});
		
//		mapView = (MapView) findViewById(R.id.map_view);
//		mapView.setBuiltInZoomControls(true);
//		mapView.setSatellite(true); // Satellite View
//		mapView.setStreetView(true); // Street View
//		mapView.setTraffic(true); // Traffic View
//
//		itemizedOverlay = new AddItemizedOverlay(drawable, this);
//
//		List<Overlay> mapOverlays = mapView.getOverlays();
//
//		MapController mc = mapView.getController();
//		double lat = itemizedOverlay.getLatitude();
//		double lon = itemizedOverlay.getLongitude();
//		GeoPoint geoPoint = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
//		mc.animateTo(geoPoint);
//		mc.setZoom(15);
//		// mapView.invalidate();
//		mapView.setSatellite(true);
//
//		// OverlayItem overlayitem = new OverlayItem(geoPoint, "Hello",
//		// "Sample Overlay item");
//		//
//		// itemizedOverlay.addOverlay(overlayitem);
//
//		mapOverlays.add(itemizedOverlay);
		
		telaMapa(drawable);
	}

	@Override
	protected boolean isRouteDisplayed() {
		return false;
	}

	public boolean onKeyDown() {
		return true;
	}
	
	private void info() {
		Intent info = new Intent(this, InformacoesActivity.class);
		info.putExtra("distancia", itemizedOverlay.getDistancia());
		info.putExtra("combustivelPorKm", combustivelPorKm);
		startActivity(info);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.menu.menu_mapa, menu);
		return true;
	}

	@SuppressWarnings("deprecation")
	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{

		case R.id.normal:

			Toast.makeText(GMapsActivity.this, "Vista Normal ",Toast.LENGTH_SHORT).show();
			if (mapView.isSatellite() == true) {
				mapView.setSatellite(false);
			}
			return true;

		case R.id.satelite:
			Toast.makeText(GMapsActivity.this, "Vista Satélite",Toast.LENGTH_SHORT).show();

			if (mapView.isSatellite() == false) {
				mapView.setSatellite(true);
			}
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	// public void apagarPontosOnClick(View view) {
	// Toast.makeText(this, "apagar pontos", Toast.LENGTH_SHORT).show();
	// //itemizedOverlay.apagarPontos(mapView);
	// }

	public void telaMapa(Drawable drawable) {
		mapView = (MapView) findViewById(R.id.map_view);
		mapView.getOverlays().clear();
		mapView.invalidate();
		mapView.setBuiltInZoomControls(true);
		mapView.setSatellite(true); // Satellite View
		mapView.setStreetView(true); // Street View
		mapView.setTraffic(true); // Traffic View

		itemizedOverlay = new AddItemizedOverlay(drawable, this);

		List<Overlay> mapOverlays = mapView.getOverlays();

		
		double lat = itemizedOverlay.getLatitude();
		double lon = itemizedOverlay.getLongitude();
		GeoPoint geoPoint = new GeoPoint((int) (lat * 1E6), (int) (lon * 1E6));
		mc.animateTo(geoPoint);
		mc.setZoom(15);
		// mapView.invalidate();
		mapView.setSatellite(true);
		
		
		//GPS
//		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//		locationListener = new NovaLocalizacao();
//		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

		// OverlayItem overlayitem = new OverlayItem(geoPoint, "Hello",
		// "Sample Overlay item");
		//
		// itemizedOverlay.addOverlay(overlayitem);

		mapOverlays.add(itemizedOverlay);
		if(contMarcador<3){			
			contMarcador += 1;
		}
//		new RotaAsyncTask(mapView).execute(
//				// Latitude, Logintude de Origem
//				-7.22274,-35.91235,  
//				// Latitude, Longitude de Destino
//				-7.22284,-35.87790);
	}
	
	class NovaLocalizacao implements LocationListener{

		@Override
		public void onLocationChanged(Location location) {
			Log.i("onLocationChaged Rodolfo", "OnLoationChanged GPS");
//			GeoPoint geoPoint = new GeoPoint((int) (location.getLatitude()), (int) (location.getLongitude()));
//			mc.animateTo(geoPoint);
//			Toast.makeText(getBaseContext(), (int) location.getLatitude(), Toast.LENGTH_LONG).show();
			
		}

		@Override
		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub
			
		}
		
	}
}
