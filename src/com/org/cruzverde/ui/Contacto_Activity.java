package com.org.cruzverde.ui;

import com.org.cruzverde.R;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class Contacto_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contacto);
		RelativeLayout fondo = (RelativeLayout) findViewById(R.id.fondo);
		fondo.setBackgroundColor(Color.rgb(242,242,242));
		LinearLayout volver = (LinearLayout) findViewById(R.id.btn_volver);
		volver.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}
}
