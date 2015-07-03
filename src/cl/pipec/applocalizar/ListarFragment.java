package cl.pipec.applocalizar;

import java.util.ArrayList;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import cl.pipec.applocalizar.clases.DataBase;
import cl.pipec.applocalizar.clases.GeoDatos;
import cl.pipec.applocalizar.clases.ListaGeoAdapter;

public class ListarFragment extends Fragment {
	private ArrayAdapter<GeoDatos> adapter;
	Context thiscontext;
	//private String TAG = ListarFragment.class.getSimpleName();
	SwipeRefreshLayout mSwipeRefreshLayout;
	ListView lv_listado;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		//vista y contexto
		View rootView = inflater.inflate(R.layout.fragment_listar, container,false);
		thiscontext = container.getContext();
		lv_listado = (ListView) rootView.findViewById(R.id.lv_listadoLugares);
		//database
		DataBase db = new DataBase(getActivity());	
		ArrayList<GeoDatos> listado = db.listarLugares();
		
		/*adapter = new ArrayAdapter<GeoDatos>(getActivity(),
				android.R.layout.activity_list_item,
				android.R.id.text1, listado);
		lv_listado.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		*/	
		lv_listado.setAdapter(new ListaGeoAdapter(getActivity(), listado));
		//refrescar la vista pull down
		mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_refresh_layout);
		mSwipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				refreshContent();
			}
		});
		//retorno la vista fragment
		return rootView;
	}

	//metodo para cargar la data 
	private void refreshContent() {
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				DataBase db = new DataBase(getActivity());
				ArrayList<GeoDatos> listado = db.listarLugares();
				/*adapter = new ArrayAdapter<GeoDatos>(getActivity(),
						android.R.layout.activity_list_item,
						android.R.id.text1, listado);
				lv_listado.setAdapter(adapter);
				adapter.notifyDataSetChanged();*/
				lv_listado.setAdapter(new ListaGeoAdapter(getActivity(), listado));			
				mSwipeRefreshLayout.setRefreshing(false);
			}
		}, 2000);
	}
}
