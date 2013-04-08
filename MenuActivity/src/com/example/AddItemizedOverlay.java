package com.example;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.Vibrator;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.MapView;
import com.google.android.maps.OverlayItem;


public class AddItemizedOverlay extends ItemizedOverlay<OverlayItem> {

	private ArrayList<OverlayItem> mapOverlays = new ArrayList<OverlayItem>();
	private ArrayList<GeoPoint> geopointsList;
	private double lat;
	private double lon;
	private boolean rotaCalculada = false;
	private boolean marcarPonto;
	private String distancia;
	private Vibrator vibe;
	private Context context;

	public AddItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}

	public AddItemizedOverlay(Drawable defaultMarker, Context context, Vibrator vibe) {	
		this(defaultMarker);
		this.context = context;
		this.lat = -7.230556;
		this.lon = -35.881111;
		this.geopointsList = new ArrayList<GeoPoint>();
		this.vibe = vibe;
	}
	
	public void addGeoPointList(GeoPoint point){
		geopointsList.add(point);
	}

	public double getLatitude() {
		return lat;
	}

	public double getLongitude() {
		return lon;
	}

	public ArrayList<GeoPoint> getGeopointList() {
		return geopointsList;
	}

	public void apagarPontos() {
		 mapOverlays = new ArrayList<OverlayItem>();
	}
	
	public void removerItem(MapView v){
		for(int i = 0; i < mapOverlays.size();i++){
			mapOverlays.remove(i);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event, MapView mapView) {
		long aux, aux2, aux3, aux4;
		aux = 0L;
		aux2 = 0L;
		aux3 = 0L;
		aux4 = 550L;

		aux2 = event.getEventTime();
		aux = event.getDownTime();
		aux3 = aux2 - aux;

		boolean teste = aux3 > aux4;
		
		if (event.getAction() == 1) {
			if (teste) {
				vibe.vibrate(50);
				GeoPoint geopoint = mapView.getProjection().fromPixels((int) event.getX(), (int) event.getY());
				this.lat = geopoint.getLatitudeE6() / 1E6;
				this.lon = geopoint.getLongitudeE6() / 1E6;
				geopointsList.add(geopoint);

				// Dai eu so deixo marcar se o tempo for maior que 500L
				if (mapOverlays.size() < 2 /* && teste eh aqui que fica o erro =\ */) {
					OverlayItem overlayitem = new OverlayItem(geopoint,"Hello", "Sample Overlay item");
					this.addOverlay(overlayitem);
					rotaCalculada = false;
				}
				
				if (mapOverlays.size() == 2) {
					GeoPoint prev = geopointsList.get(0), current = geopointsList.get(1);
					float[] results = new float[3];
					Location.distanceBetween(prev.getLatitudeE6() / 1E6,
							prev.getLongitudeE6() / 1E6,
							current.getLatitudeE6() / 1E6,
							current.getLongitudeE6() / 1E6, results);

					distancia = Float.toString(results[0]);
					// distancia = format(results[0]);

					if (results[0] >= 1000) {
						Toast.makeText(context,results[0] / 1000 + " Kilometros ",Toast.LENGTH_SHORT).show();
					} else if (results[0] < 1000) {

						Toast.makeText(context, distancia + " metros ", Toast.LENGTH_SHORT).show();
					}

					if (!rotaCalculada) {
						new RotaAsyncTask(mapView).execute(
								// Latitude, Logintude de Origem
								prev.getLatitudeE6() / 1E6,
								prev.getLongitudeE6() / 1E6,
								// Latitude, Longitude de Destino
								current.getLatitudeE6() / 1E6,
								current.getLongitudeE6() / 1E6);
						rotaCalculada = true;

					}

				}
				return true;
			}
			return true;
			
		} else {
			return false;
		}
	}
	
	public String getDistancia() {
		return distancia;
	}

	@Override
	protected OverlayItem createItem(int i) {
		return mapOverlays.get(i);
	}

	@Override
	public int size() {
		return mapOverlays.size();
	}

	public void addOverlay(OverlayItem overlay) {
		mapOverlays.add(overlay);
		this.populate();
	}

	// Calculo da distancia
	public static Double distancia(Double lat1, Double lat2, Double lon1,
			Double lon2) {
		double raio = 3958.75;
		double dlat = ToRadians(lat2 - lat1);
		double dlon = ToRadians(lon2 - lon1);

		double a = Math.sin(dlat / 2) * Math.sin(dlat / 2)
				+ Math.cos(ToRadians(lat1)) * Math.cos(ToRadians(lat2))
				* Math.sin(dlon / 2) * Math.sin(dlon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		Double d = raio * c;

		double meterConversion = 1609.00;
		System.out.println("Teste: " + d * meterConversion);
		return d * meterConversion;
	}

	private static double ToRadians(double degrees) {
		double radians = degrees * 3.1415926535897932385 / 180;
		return radians;
	}

	public void removerOverlay() {
		this.mapOverlays = new ArrayList<OverlayItem>();
		this.populate();
		
	}
	
	public boolean pontoMarcado(){
		return this.marcarPonto;
	}
	
	public void pontoMarcado(boolean tipo){
		this.marcarPonto =  tipo;
	}
	
	@SuppressLint("DefaultLocale")
	private String format(double numero) {
		return String.format("%.3f", numero);
	}
}
