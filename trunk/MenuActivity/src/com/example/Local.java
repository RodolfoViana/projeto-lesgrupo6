package com.example;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class Local  extends Overlay{
	private GeoPoint geoPoint;
	private Paint paint = new Paint();
	private int cor;
	
	public Local (GeoPoint geoPoint, int cor){
		this.geoPoint = geoPoint;
		this.cor = cor;
	}
	
	@Override
	public void draw(Canvas canvas, MapView mapView, boolean shadow){
		{
			super.draw(canvas, mapView, shadow);
			if (geoPoint != null){
				paint.setColor(cor);
				//converter coordenadas para pixel
				Point point = mapView.getProjection().toPixels(geoPoint, null);
				//desenhando o circulo no mapa
				canvas.drawCircle(point.x, point.y, 6, paint);
				
			}
			
		}
		
	}
}
