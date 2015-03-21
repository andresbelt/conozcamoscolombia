package com.org.cruzverde.adapter;

import java.util.List;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import com.org.cruzverde.R;
import com.org.cruzverde.util.MyTextView;
import com.org.cruzverde.vo.LocalesVO;


/**
 * adapter dibuja los elementos de la lista 
 * 
 * @author 
 *
 */
public class ListAdapter extends ArrayAdapter<LocalesVO> {

	// contexto
	private final Context context;
	// lista de valores
	private final List<LocalesVO> listalocales;

	public ListAdapter(Context context, List<LocalesVO> smsList) {
		super(context, R.layout.activity_main, smsList);
		this.context = context;
		this.listalocales = smsList;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.item_list_puntos, parent, false);

		MyTextView titulo = (MyTextView) rowView.findViewById(R.id.titulo);
		titulo.setText(listalocales.get(position).getNombre_Drogueria());

		MyTextView direccion = (MyTextView) rowView.findViewById(R.id.direccion);
		direccion.setText(Html.fromHtml("<b>" + "Dirección: "  + "</b>"+ listalocales.get(position).getDirección()));

		MyTextView horario = (MyTextView) rowView.findViewById(R.id.horario);
		horario.setText(Html.fromHtml("<b>" + "Horario: "  + "</b>"+ listalocales.get(position).getHorario()));


		return rowView;
	}

}
