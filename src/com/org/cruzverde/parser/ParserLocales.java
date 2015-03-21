package com.org.cruzverde.parser;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

import com.org.cruzverde.vo.LocalesVO;

public class ParserLocales {
	
	
	public static  List<LocalesVO> LocalesParser(String json) throws JSONException
	{
		JSONArray jsonRes = new JSONArray(json);
		List<LocalesVO> categoriasList = new ArrayList<LocalesVO>();


		for(int i=0; i<jsonRes.length(); i++)
		{
			LocalesVO LibrosInfo = new LocalesVO();
			JSONObject teamObj = jsonRes.getJSONObject(i);

			String Departamento =teamObj.getString("Departamento");
			LibrosInfo.setDepartamento(Departamento);

			String Ciudad=teamObj.getString("Ciudad");
			LibrosInfo.setCiudad(Ciudad);

			String Dirección=teamObj.getString("Dirección");
			LibrosInfo.setDirección(Dirección);

			String Nombre_Drogueria=teamObj.getString("Nombre Drogueria");
			LibrosInfo.setNombre_Drogueria(Nombre_Drogueria);

			String Horario=teamObj.getString("Horario");
			LibrosInfo.setHorario(Horario);

			Double latitud=teamObj.getDouble("Latitud");
			LibrosInfo.setLatitud(latitud);

			Double longitud=teamObj.getDouble("Longitud");
			LibrosInfo.setLongitud(longitud);

			categoriasList.add(LibrosInfo);

		}
		Log.d("paso","terminoparseo");
		return categoriasList;

	}
}

