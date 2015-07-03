package cl.pipec.applocalizar;


import java.text.SimpleDateFormat;
import java.util.Calendar;

import cl.pipec.applocalizar.clases.DataBase;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class BuscarFragment extends Fragment {
	DataBase db;
	Button btn_buscar;
	EditText fechaBuscar;
	private DatePickerDialog dpd_fecha;
	private SimpleDateFormat dateFormatter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_buscar, container,
				false);

		btn_buscar = (Button) rootView.findViewById(R.id.btn_buscar);
		fechaBuscar = (EditText) rootView.findViewById(R.id.etxt_fromdate);
		fechaBuscar.setInputType(InputType.TYPE_NULL);
		fechaBuscar.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dpd_fecha.show();
			}

		});
		ajustarFecha();
		return rootView;
	}

	private void ajustarFecha() {
		Calendar newCalendar = Calendar.getInstance();
		dpd_fecha = new DatePickerDialog(getActivity(),
				new OnDateSetListener() {

					@Override
					public void onDateSet(DatePicker view, int year,
							int monthOfYear, int dayOfMonth) {
						// TODO Auto-generated method stub
						Calendar newDate = Calendar.getInstance();
						newDate.set(year, monthOfYear, dayOfMonth);
						fechaBuscar.setText(dateFormatter.format(newDate.getTime()));
					}
				}, newCalendar.get(Calendar.YEAR),
				newCalendar.get(Calendar.MONTH),
				newCalendar.get(Calendar.DAY_OF_MONTH));

		

	}

}
