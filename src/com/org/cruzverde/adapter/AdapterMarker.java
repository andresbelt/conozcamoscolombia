package com.org.cruzverde.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.org.cruzverde.R;
import com.org.cruzverde.util.MyTextView;


public class AdapterMarker implements InfoWindowAdapter {
	private View popup = null;
	private LayoutInflater inflater = null;


	public AdapterMarker(LayoutInflater inflater) {
		this.inflater = inflater;
	}

	@Override
	public View getInfoWindow(Marker marker) {


		if(marker.getTitle() != null){
			if (popup == null) {
				popup = inflater.inflate(R.layout.popup_marker, null);
			}
			MyTextView hora = (MyTextView) popup.findViewById(R.id.horario);
			MyTextView dir = (MyTextView) popup.findViewById(R.id.direc);
			MyTextView nom = (MyTextView) popup.findViewById(R.id.nombre);

			String[] n = marker.getTitle().split(",");

			hora.setText(n[1]);
			dir.setText(n[0]);
			nom.setText(marker.getSnippet());
		}else{

			popup=null;
		}

		return (popup);

	}

	@Override
	public View getInfoContents(Marker marker) {
		return (null);}
}