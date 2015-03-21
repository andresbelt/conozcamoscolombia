package com.org.cruzverde.ui;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;

import com.org.cruzverde.R;
import com.org.cruzverde.parser.ParserLocales;
import com.org.cruzverde.sharedpreferences.SharedPreferencesInfo;


import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class Ayuda_Activity extends Activity implements OnClickListener {

	LinearLayout terminos,nosotros,funciona,pedir_dom;
	String archivo ;
	Map<String, String> map;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ayuda);


		terminos = (LinearLayout) findViewById(R.id.terminos);
		nosotros= (LinearLayout) findViewById(R.id.nosotros);
		funciona= (LinearLayout) findViewById(R.id.funciona);
		pedir_dom= (LinearLayout) findViewById(R.id.pedirdomicilio);
		terminos.setOnClickListener(this);
		nosotros.setOnClickListener(this);
		funciona.setOnClickListener(this);
		pedir_dom.setOnClickListener(this);
		
		LinearLayout volver = (LinearLayout) findViewById(R.id.btn_volver);
		volver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.terminos:

			archivo = leer( "terminos.txt" );

			map = new HashMap<String, String>();
			map.put("Info","Términos legales-"+ archivo);
			SharedPreferencesInfo.setInfo(
					getApplicationContext(), map);
			startActivity(new Intent(Ayuda_Activity.this,Ayuda_detalle_activity.class));

			break;
		case R.id.nosotros:

			archivo = leer( "nosotros.txt" );
			map = new HashMap<String, String>();
			map.put("Info",	"Nosotros-"+ archivo);
			SharedPreferencesInfo.setInfo(
					getApplicationContext(), map);
			startActivity(new Intent(Ayuda_Activity.this,Ayuda_detalle_activity.class));

			break;

		case R.id.funciona:

			archivo = leer( "funciona.txt" );
			map = new HashMap<String, String>();
			map.put("Info","¿Cómo funciona?-"+ archivo);
			SharedPreferencesInfo.setInfo(
					getApplicationContext(), map);
			startActivity(new Intent(Ayuda_Activity.this, Ayuda_detalle_activity.class));

			break;

		case R.id.pedirdomicilio:

			archivo = leer( "domicilios.txt" );
			map = new HashMap<String, String>();
			map.put("Info","¿Cómo pedir un domicilio?-"+ archivo);
			SharedPreferencesInfo.setInfo(
					getApplicationContext(), map);
			startActivity(new Intent(Ayuda_Activity.this,Ayuda_detalle_activity.class));
			break;


		default:
			break;
		}


	}

	/** Metodo para leer archivo de texto 
	 * @param archivo Ruta del archivo de texto en la carpeta ASSETS
	 * */
	public String leer( String archivo )

	{
		String text = "";
		AssetManager assetManager = getAssets();
		InputStream inputStream = null;
		try {

			inputStream = assetManager.open( archivo );
			text = btoString( inputStream );

		} catch (IOException ex) {
			//com.org.cruzverde.util.MyTextView.setText( ex.getMessage() );
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
		return text;
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
