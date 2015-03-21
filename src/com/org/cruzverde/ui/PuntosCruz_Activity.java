package com.org.cruzverde.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONException;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.org.cruzverde.R;
import com.org.cruzverde.R.layout;
import com.org.cruzverde.adapter.ListAdapter;
import com.org.cruzverde.parser.ParserLocales;
import com.org.cruzverde.vo.LocalesVO;


import android.app.Activity;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

public class PuntosCruz_Activity extends Activity 

{
	Spinner spinner_ciudad;
	List<LocalesVO> Listalocales;
	ListView list_puntos;
	ListAdapter Adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_puntos_cruz);


		spinner_ciudad = (Spinner) findViewById(R.id.spinner_ciudad);
		list_puntos = (ListView) findViewById(R.id.list_puntos);
		leer( "Sucursales.txt" );
		//ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, R.layout.item_spinner);
		// MyAdapter ap= new MyAdapter(this, R.layout.item_spinner,mydata1);
		//adapter.setDropDownViewResource(R.layout.checkedspinner);
		//spinner_ciudad.setAdapter(adapter);
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
			Adapter = new ListAdapter(this, Listalocales);
			list_puntos.setAdapter(Adapter);

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
}
