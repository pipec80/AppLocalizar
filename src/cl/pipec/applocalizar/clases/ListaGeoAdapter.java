package cl.pipec.applocalizar.clases;

import java.util.ArrayList;

import cl.pipec.applocalizar.R;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListaGeoAdapter extends BaseAdapter{
	private ArrayList<GeoDatos> listData;
	private LayoutInflater layoutInflater;

	
	public ListaGeoAdapter(Context aContext, ArrayList<GeoDatos> listData) {
		super();
		this.listData = listData;
		this.layoutInflater = LayoutInflater.from(aContext);
	}

	@Override
	public int getCount() {
		return listData.size();
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;

		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.lista_geo, null);
			holder = new ViewHolder();
			holder.tvw_id = (TextView) convertView.findViewById(R.id.tvw_id);
			holder.tvw_lat = (TextView) convertView.findViewById(R.id.tvw_lat);
			holder.tvw_long = (TextView) convertView.findViewById(R.id.tvw_long);
			holder.tvw_fecha = (TextView) convertView.findViewById(R.id.tvw_fecha);
			holder.tvw_ip = (TextView) convertView.findViewById(R.id.tvw_ip);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.tvw_id.setText(String.valueOf(listData.get(position).getId()));
		holder.tvw_lat.setText(String.valueOf(listData.get(position).getLatitude()));
		holder.tvw_long.setText(String.valueOf(listData.get(position).getLongitude()));
		holder.tvw_fecha.setText(String.valueOf(listData.get(position).getFecha()));
		holder.tvw_ip.setText(String.valueOf(listData.get(position).getIp()));
		return convertView;
	}
	static class ViewHolder {
		TextView tvw_id;
		TextView tvw_lat;
		TextView tvw_long;
		TextView tvw_fecha;
		TextView tvw_ip;
	}

}
