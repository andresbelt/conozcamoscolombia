package com.org.cruzverde.ui;

import com.org.cruzverde.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;

public class Splash_Activity extends Activity {

	private final int SPLASH_DISPLAY_LENGHT = 1000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent mainIntent = null;


				mainIntent = new Intent(Splash_Activity.this, Main_Activity.class);

				Splash_Activity.this.startActivity(mainIntent);
				Splash_Activity.this.finish();
			}
		}, SPLASH_DISPLAY_LENGHT);
	}
}
