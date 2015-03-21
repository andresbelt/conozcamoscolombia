package com.org.cruzverde.services;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings.Secure;
import android.provider.Settings.SettingNotFoundException;
import android.text.TextUtils;
import android.util.Log;

public class GPSTracker extends Service implements LocationListener {


	private final Context mContext;
	boolean isGPSEnabled = false;
	boolean isNetworkEnabled = false;
	boolean canGetLocation = false;

	Location location;
	double latitude;
	double longitude;


	private static final long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;
	private static final long MIN_TIME_BW_UPDATES = 1000 * 60 * 1;
	protected LocationManager locationManager;

	public GPSTracker(Context context) {
		this.mContext = context;
		getLocation();
	}

	public static boolean isGpsEnabled(Context mContext) {

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
			String providers = Secure.getString(mContext.getContentResolver(),
					Secure.LOCATION_PROVIDERS_ALLOWED);
			if (TextUtils.isEmpty(providers)) {
				return false;
			}
			return providers.contains(LocationManager.GPS_PROVIDER);
		} else {
			final int locationMode;
			try {
				locationMode = Secure.getInt(mContext.getContentResolver(),
						Secure.LOCATION_MODE);
			} catch (SettingNotFoundException e) {
				e.printStackTrace();
				return false;
			}
			switch (locationMode) {

			case Secure.LOCATION_MODE_HIGH_ACCURACY:
			case Secure.LOCATION_MODE_SENSORS_ONLY:
				return true;
			case Secure.LOCATION_MODE_BATTERY_SAVING:
			case Secure.LOCATION_MODE_OFF:
			default:
				return false;
			}
		}
	}

	public Location getLocation() {

		try {
			locationManager = (LocationManager) mContext
					.getSystemService(LOCATION_SERVICE);

			isGPSEnabled = isGpsEnabled(mContext);

			// getting network status
			isNetworkEnabled = locationManager
			.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

			if (isGPSEnabled && isNetworkEnabled) {
				this.canGetLocation = true;
				if (isNetworkEnabled) {
					locationManager.requestLocationUpdates(
							LocationManager.NETWORK_PROVIDER,
							MIN_TIME_BW_UPDATES,
							MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
					Log.d("Network", "Network");
					if (locationManager != null) {
						location = locationManager
								.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
						if (location != null) {
							latitude = location.getLatitude();
							longitude = location.getLongitude();
						}
					}
				}
				// if GPS Enabled get lat/long using GPS Services
				if (isGPSEnabled) {
					if (location == null) {
						locationManager.requestLocationUpdates(
								LocationManager.GPS_PROVIDER,
								MIN_TIME_BW_UPDATES,
								MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
						Log.d("GPS Enabled", "GPS Enabled");
						if (locationManager != null) {
							location = locationManager
									.getLastKnownLocation(LocationManager.GPS_PROVIDER);
							if (location != null) {
								latitude = location.getLatitude();
								longitude = location.getLongitude();
							}
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return location;
	}

	/**
	 * Stop using GPS listener
	 * Calling this function will stop using GPS in your app
	 * */
	public void stopUsingGPS(){
		if(locationManager != null){
			locationManager.removeUpdates(GPSTracker.this);
		}		
	}

	/**
	 * Function to get latitude
	 * */
	public double getLatitude(){
		if(location != null){
			latitude = location.getLatitude();
		}

		// return latitude
		return latitude;
	}

	/**
	 * Function to get longitude
	 * */
	public double getLongitude(){
		if(location != null){
			longitude = location.getLongitude();
		}

		// return longitude
		return longitude;
	}

	/**
	 * Function to check GPS/wifi enabled
	 * @return boolean
	 * */
	public boolean canGetLocation() {
		return this.canGetLocation;
	}

	@Override
	public void onLocationChanged(Location location) {
	}

	@Override
	public void onProviderDisabled(String provider) {


	}

	@Override
	public void onProviderEnabled(String provider) {


	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
	}

	@Override
	public IBinder onBind(Intent arg0) {
		return null;
	}

}
