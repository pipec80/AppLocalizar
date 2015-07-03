package cl.pipec.applocalizar.clases;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DataBase extends SQLiteOpenHelper {

	private static final String LOG = "DataBase";
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "db_geo.db";
	private static final String TABLE_NAME = "lugares";

	// Sentencia SQL para la creación de una tabla
	private static final String CREATE_TABLA_LUGAR = "CREATE TABLE lugares (id INTEGER PRIMARY KEY AUTOINCREMENT, latitude REAL, longitude REAL, ip REAL, fecha DATETIME)";

	public DataBase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		if (db.isReadOnly()) {
			db = getWritableDatabase();
		}
		db.execSQL(CREATE_TABLA_LUGAR);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}

	/*********CRUD**********/
	public long guardarPos(GeoDatos data) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put("latitude", data.getLatitude());
		values.put("longitude", data.getLongitude());
		values.put("ip", data.getIp());
		values.put("fecha", getDateTime());
		// insert row
		long tag_id = db.insert(TABLE_NAME, null, values);
		closeDB();
		return tag_id;
	}

	public ArrayList<GeoDatos> listarLugares() {
		ArrayList<GeoDatos> listado = new ArrayList<GeoDatos>();
		// 1. construir la consulta
		String query = "SELECT * FROM " + TABLE_NAME + " ORDER BY id DESC";
		Log.e(LOG, query);
		// 2. obtener referencia a escribible DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		// 3. repasar cada fila, construir el objeto y agregarlo a la lista
		GeoDatos data = null;
		if (cursor.moveToFirst()) {
			do {
				data = new GeoDatos();
				data.setId(Integer.parseInt(cursor.getString(0)));
				data.setLatitude(Double.parseDouble(cursor.getString(1))); 
				data.setLongitude(Double.parseDouble(cursor.getString(2)));
				data.setIp(cursor.getString(3));
				data.setFecha(cursor.getString(4));
				// Add data a listado
				listado.add(data);

			} while (cursor.moveToNext());
		}
		Log.d("listarLugares()", listado.toString());		 
        // return lugares
        return listado;

	}

	
	public ArrayList<GeoDatos> BuscarLugaresFecha(String aFecha) {
		ArrayList<GeoDatos> listado = new ArrayList<GeoDatos>();
		// 1. construir la consulta
		String query = "SELECT * FROM " + TABLE_NAME + " WHERE DATE(fecha) LIKE '"+aFecha+"' ORDER BY id DESC";
		Log.e(LOG, query);
		// 2. obtener referencia a escribible DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		// 3. repasar cada fila, construir el objeto y agregarlo a la lista
		GeoDatos data = null;
		if (cursor.moveToFirst()) {
			do {
				data = new GeoDatos();
				data.setId(Integer.parseInt(cursor.getString(0)));
				data.setLatitude(Double.parseDouble(cursor.getString(1))); 
				data.setLongitude(Double.parseDouble(cursor.getString(2)));
				data.setIp(cursor.getString(3));
				data.setFecha(cursor.getString(4));
				// Add data a listado
				listado.add(data);

			} while (cursor.moveToNext());
		}
		Log.d("listarLugares()", listado.toString());		 
        // return lugares
        return listado;

	}


	// closing database
	public void closeDB() {
		SQLiteDatabase db = this.getReadableDatabase();
		if (db != null && db.isOpen())
			db.close();
	}

	/**
	 * get datetime
	 * */
	private String getDateTime() {
		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		Date date = new Date();
		return dateFormat.format(date);
	}

}
