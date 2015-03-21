package com.org.cruzverde.ui;

import com.org.cruzverde.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Main_Activity extends Activity implements OnClickListener{

	LinearLayout droguerias_cercanas,puntos_verdes,ayuda, contacto,creditos	,salir;
	RelativeLayout fondo;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		droguerias_cercanas = (LinearLayout) findViewById(R.id.droguerias_cercanas);
		fondo = (RelativeLayout) findViewById(R.id.fondo);
		puntos_verdes = (LinearLayout) findViewById(R.id.puntos_cruzverde);
		ayuda = (LinearLayout) findViewById(R.id.ayuda);
		contacto = (LinearLayout) findViewById(R.id.contacto);
		creditos = (LinearLayout) findViewById(R.id.creditos);
		salir = (LinearLayout) findViewById(R.id.salir);
		fondo.setBackgroundColor(Color.rgb(242,242,242));

		droguerias_cercanas.setOnClickListener(this);
		puntos_verdes.setOnClickListener(this);
		ayuda.setOnClickListener(this);
		creditos.setOnClickListener(this);
		contacto.setOnClickListener(this);
		salir.setOnClickListener(this);

	}

	private void dialog(){

		final  Dialog dialog = new Dialog(Main_Activity.this,R.style.ThemeTransparent);

		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog.setCanceledOnTouchOutside(true);			
		dialog.setContentView(R.layout.dialogo_salir);
		dialog.getWindow().setLayout(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);			
		LinearLayout dialogButton = (LinearLayout) dialog.findViewById(R.id.si);	
		dialogButton.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				finish();					
			}
		});			
		LinearLayout dialogButton1 = (LinearLayout) dialog.findViewById(R.id.no);	
		dialogButton1.setOnClickListener(new OnClickListener() {				
			@Override
			public void onClick(View v) {
				dialog.dismiss();						
			}
		});		
		dialog.show();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch (arg0.getId()) {
		case R.id.droguerias_cercanas:
			startActivity(new Intent(this,DrogueriasCercanas_Activity.class));
			break;
		case R.id.puntos_cruzverde:
			startActivity(new Intent(this,PuntosCruz_Activity.class));
			break;
		case R.id.ayuda:
			startActivity(new Intent(this,Ayuda_Activity.class));
			break;
		case R.id.contacto:
			startActivity(new Intent(this,Contacto_Activity.class));
			break;
		case R.id.creditos:
			startActivity(new Intent(this,Creditos_Activity.class));
			break;
		case R.id.salir:
			dialog();
			break;
		}
	}

}
