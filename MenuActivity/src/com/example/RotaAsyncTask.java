package com.example;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Message;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;

public class RotaAsyncTask extends AsyncTask<Double, Void, List<GeoPoint>>{

	private ProgressDialog dialog;
	private MapView mapView;
	
	public RotaAsyncTask(MapView mapa) {
		mapView = mapa;
	}
	
	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = ProgressDialog.show(mapView.getContext(), "Aguarde", "Calculando rota");
	}
	
	@Override
	protected List<GeoPoint> doInBackground(Double... params) {

		String urlRota = String.format(Locale.US,
				"http://maps.googleapis.com/maps/api/directions/xml" +
				"?origin=%f,%f&destination=%f,%f&sensor=true", 
				params[0], params[1], params[2], params[3]);
		
		String urlRota2 =  String.format(Locale.US,  
					"http://maps.googleapis.com/maps/api/"+  
							"directions/json?origin=%f,%f&"+  
							"destination=%f,%f&" +  
							"sensor=true&mode=driving", 
							params[0], params[1], params[2], params[3]);
		//System.out.println(urlRota);
		
		try {
			URL url = new URL(urlRota);
			HttpURLConnection conexao = (HttpURLConnection)
					url.openConnection();
			conexao.connect();
			
			InputStream is = conexao.getInputStream();
			return getGeoPoints(is);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(List<GeoPoint> result) {
		super.onPostExecute(result);
		if (result != null){
		//GeoPoint p1 = result.get(result.size()/2);
		
		RotaOverlay overlay = new RotaOverlay(result);
		mapView.getOverlays().add(overlay);
		//mapView.getController().animateTo(p1);
				
		}
		dialog.dismiss();
	}
	
	private List<GeoPoint> getGeoPoints(InputStream is){
		try {			
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document xmlDocument = builder.parse(is);
			
			//System.out.println(xmlDocument.toString());
			
			NodeList steps = xmlDocument.getElementsByTagName("step");
			
			List<GeoPoint> geoPoints = new ArrayList<GeoPoint>();
			
			for (int i = 0; i < steps.getLength(); i++) {
				Node stepNode = steps.item(i);
				NodeList stepChilds = stepNode.getChildNodes();
				
				double latOrg = 0, lngOrg = 0;
				for (int j = 0; j < stepChilds.getLength(); j++) {
					Node stepChild = stepChilds.item(j);
					if (stepChild.getNodeName().equals("start_location")){
						latOrg = Double.parseDouble(
								stepChild.getChildNodes().item(1).getTextContent());
						lngOrg = Double.parseDouble(
								stepChild.getChildNodes().item(3).getTextContent());
					} 
				}
				
				GeoPoint gp = new GeoPoint(
						(int)(latOrg * 1E6), 
						(int)(lngOrg * 1E6));
				geoPoints.add(gp);
			}
			return geoPoints;
			
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
