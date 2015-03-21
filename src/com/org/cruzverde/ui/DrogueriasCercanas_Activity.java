package com.org.cruzverde.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import com.org.cruzverde.R;
import com.org.cruzverde.R.id;
import com.org.cruzverde.R.layout;
import com.org.cruzverde.adapter.AdapterMarker;
import com.org.cruzverde.parser.ParserLocales;
import com.org.cruzverde.services.FusedLocationService;
import com.org.cruzverde.services.GPSTracker;
import com.org.cruzverde.vo.LocalesVO;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.Toast;

public class DrogueriasCercanas_Activity extends Activity implements GoogleMap.OnInfoWindowClickListener  {

	public static GoogleMap map;
	double latitud,longitud;
	List<LocalesVO> Listalocales;
	Bitmap atmPic = null;
	private LinearLayout btn_volver;
	GPSTracker gps;
	List<Marker> markers= new ArrayList<Marker>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_droguerias_cercanas);
	
		btn_volver = (LinearLayout) findViewById(R.id.btn_volver);
		btn_volver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		gps = new GPSTracker(this);

		

	}

	private void gps() {

		// check if GPS enabled
		if (gps.canGetLocation()) {
			latitud = gps.getLatitude();
			longitud = gps.getLongitude();
			ubicacion();

		} else {
			showSettingsAlert();
		}
	}

	public void showSettingsAlert() {

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		// Setting Dialog Title
		alertDialog.setTitle(this.getResources().getString(R.string.app_name));
		// Setting Dialog Message
		alertDialog.setMessage("GPS no esta activado desea activarlo?");
		// On pressing Settings button
		alertDialog.setPositiveButton("Activarlo", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {

				Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				startActivity(intent);

			}
		});
		// on pressing cancel button
		alertDialog.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				Toast.makeText(getApplicationContext(), "Por favor active el gps", Toast.LENGTH_SHORT).show();
			}
		});
		// Showing Alert Message
		alertDialog.show();
	}


	/** Metodo para leer archivo de texto 
	 * @param archivo Ruta del archivo de texto en la carpeta ASSETS
	 * */
	public void leer( String archivo )
	{  
		AssetManager assetManager = getAssets();
		InputStream inputStream = null;
		try {
			inputStream = assetManager.open( archivo );
			String text = btoString( inputStream );
			Listalocales= ParserLocales.LocalesParser(text);
			mostrarlista();

		} catch (IOException ex) {
			//com.org.cruzverde.util.MyTextView.setText( ex.getMessage() );
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			Log.e("",e.getMessage());
			e.printStackTrace();
		}
		finally{
			if( inputStream != null )
			{
				try{
					inputStream.close();
				}
				catch( IOException ex )
				{
					//com.org.cruzverde.util.MyTextView.setText( ex.getMessage() );
				}
			}
		}
	}

	/** Convierte bytes en texto
	 * @param inputStream de tipo InputStream
	 * */
	public String btoString( InputStream inputStream ) throws IOException
	{  
		ByteArrayOutputStream b = new ByteArrayOutputStream();
		byte[] bytes = new byte[4096];
		int len=0;  
		while ( (len=inputStream.read(bytes))>0 )
		{
			b.write(bytes,0,len);   
		}
		return new String( b.toByteArray(),"UTF8");
	} 



	public void mostrarlista() {

		if (map != null) {
			if (Listalocales != null && Listalocales.size() > 0) {

				try {
					for (int i = 0; i < Listalocales.size(); i++) {

						if (atmPic == null)
							atmPic = BitmapFactory.decodeResource(getResources(), R.drawable.pin);


						double Latitud = Listalocales.get(i).getLatitud();
						double Longitud = Listalocales.get(i).getLongitud();

						Marker marker = map.addMarker(new MarkerOptions().position(new LatLng(Latitud, Longitud)).title(Listalocales.get(i).getDirección() + "," + Listalocales.get(i).getHorario())
								.icon(BitmapDescriptorFactory.fromBitmap(atmPic)).snippet(Listalocales.get(i).getNombre_Drogueria()));

						markers.add(marker);
					}
					//					LatLngBounds.Builder builder = new LatLngBounds.Builder();
					//					for (Marker marker : markers) {
					//						builder.include(marker.getPosition());
					//					}
					//					
					//					LatLngBounds bounds = builder.build();
					//					int padding = 10; // offset from edges of the map in pixels
					//					CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, padding);
					//					map.animateCamera(cu);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub

		map = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
				.getMap();

		map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
		map.getUiSettings().setZoomControlsEnabled(true);
		map.getUiSettings().setCompassEnabled(true);
		// Setting click event handler for InfoWIndow
		map.setOnInfoWindowClickListener(this);
		map.setInfoWindowAdapter(new AdapterMarker(getLayoutInflater()));

		leer( "Sucursales.txt" );
		gps();
		super.onResume();

	}

	public void ubicacion() {

		if (map != null) {

			LatLng latLng1 = new LatLng(latitud, longitud);
			MarkerOptions mk1 = new MarkerOptions()
			.draggable(false)
			.position(latLng1);
			map.addMarker(mk1);
			map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng1, 13));

		}
	}


	@Override
	public void onInfoWindowClick(Marker arg0) {
		// TODO Auto-generated method stub

	}
}
