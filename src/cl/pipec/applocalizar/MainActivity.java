package cl.pipec.applocalizar;

import java.util.Timer;
import java.util.TimerTask;

import cl.pipec.applocalizar.clases.DataBase;
import cl.pipec.applocalizar.clases.GPSTracker;
import cl.pipec.applocalizar.clases.GeoDatos;
import cl.pipec.applocalizar.clases.RedInfo;
import cl.pipec.applocalizar.clases.TabsPagerAdapter;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements
		ActionBar.TabListener {
	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	Timer timer;
	TimerTask timerTask;
	final Handler handler = new Handler();
	DataBase db;
	//private String TAG = MainActivity.class.getSimpleName();

	// Tab titles
	private String[] tabs = { "HOME", "Listar", "Buscar" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);		
		//
		startTimer();
		// inicialización de tabs
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		// Añadiendo Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
		}
		/**
		 * on swiping viewpager hacer respectiva tab seleccionada
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	//
	public void startTimer() {
		//establecer un nuevo temporizador
		timer = new Timer();
		// inicializar el trabajo del TimerTask
		initializeTimerTask();
		// programar el temporizador, después de los primeros 5000ms el TimerTask se ejecutará
		// cada 180000
		timer.schedule(timerTask, 5000, 180000); //180000 60000 10000
	}

	public void stoptimertask(View v) {
		// detener el temporizador, si no es null
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

	public void initializeTimerTask() {
		timerTask = new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				handler.post(new Runnable() {
					@Override
					public void run() {
						// TODO Auto-generated method stub
						Toast.makeText(getApplicationContext(),"Guardando tu Localización", Toast.LENGTH_SHORT).show();
						guardarGeo();
					}
				});
			}
		};
	}
	
	public void guardarGeo(){
		long geo_id = 0;
		RedInfo red = new RedInfo(MainActivity.this);
		String ipaddress = red.getWifiIpAddress(); 	
		db = new DataBase(MainActivity.this);
		GPSTracker gps = new GPSTracker(MainActivity.this);		
		GeoDatos geo = new GeoDatos(gps.getLatitude(), gps.getLongitude(),ipaddress);
		geo_id  = db.guardarPos(geo);
		Log.d("database", "guardar geo");
		db.closeDB();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			startActivity(new Intent(this,SettingsActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub
		viewPager.setCurrentItem(tab.getPosition());
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// TODO Auto-generated method stub

	}
}
