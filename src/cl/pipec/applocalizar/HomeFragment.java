package cl.pipec.applocalizar;

import cl.pipec.applocalizar.clases.GPSTracker;
import cl.pipec.applocalizar.clases.RedInfo;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeFragment extends Fragment {
	// GPSTracker class
	GPSTracker gps;
	TextView nombre, lat, lon, ip;
	View rootView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_home, container, false);
		SharedPreferences mSettings = getActivity().getSharedPreferences("cl.pipec.applocalizar_preferences", 0);
		String prefName = mSettings.getString("example_text", "");
		
		nombre = (TextView) rootView.findViewById(R.id.tvw_home_nombre);
		nombre.setText(prefName);
		
		Button btn_local = (Button) rootView.findViewById(R.id.btn_localizar);
		btn_local.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				gps = new GPSTracker(getActivity());
				// comprobar si está activado el GPS
				if (gps.canGetLocation()) {
					RedInfo red = new RedInfo(getActivity());
					double latitude = gps.getLatitude();
					double longitude = gps.getLongitude();
					lat = (TextView) rootView.findViewById(R.id.tvw_home_lat);
					lon = (TextView) rootView.findViewById(R.id.tvw_home_lon);
					ip = (TextView) rootView.findViewById(R.id.tvw_home_ip);
					lat.setText(String.valueOf(latitude));
					lon.setText(String.valueOf(longitude));
					ip.setText(red.getWifiIpAddress());
					/*
					 * Toast.makeText(getActivity(),"Su ubicación es - \nLat: "
					 * + latitude + "\nLong: " + longitude,
					 * Toast.LENGTH_LONG).show();
					 */
				} else {
					// No se puede obtener la ubicación
					// GPS o la red no está habilitada
					// Preguntar al usuario para activar el GPS / red en
					// entornos
					gps.showSettingsAlert();
				}

			}
		});
		return rootView;
	}
}
