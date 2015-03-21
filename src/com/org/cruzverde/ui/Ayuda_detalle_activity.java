package com.org.cruzverde.ui;

import java.util.Map;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.org.cruzverde.R;
import com.org.cruzverde.R.id;
import com.org.cruzverde.R.layout;
import com.org.cruzverde.sharedpreferences.SharedPreferencesInfo;
import com.org.cruzverde.util.MyTextView;

public class Ayuda_detalle_activity extends Activity {

	MyTextView titulo,contenido;
	Map<String, String> mInfoAlert;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ayuda_detalle);

		titulo = (MyTextView) findViewById(R.id.titulo);
		contenido = (MyTextView) findViewById(R.id.contenido);

		LinearLayout volver = (LinearLayout) findViewById(R.id.btn_volver);
		volver.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		mInfoAlert = SharedPreferencesInfo.getInfo(this);
		String[] i = mInfoAlert.get("Info").split("-");

		titulo.setText(i[0]);
		contenido.setText(i[1]);

	}
}
