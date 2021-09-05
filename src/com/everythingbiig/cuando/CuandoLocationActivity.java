package com.everythingbiig.cuando;

import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;

public class CuandoLocationActivity extends BaseContextAwareActivity {

	private static final String TAG = "CuandoLocationActivity";	

	private final float ZOOM = 15f;
	
	MapFragment mapFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// Make sure we are bound to teh service
		bindToContextAwarenessService();
		setContentView(R.layout.activity_cuando_location);
		mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map_fragment);
		setupMap();
		registerForMapTouchEvents();
	}

	private void setupMap() {
		mapFragment.getMap().setMyLocationEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cuando_location, menu);
		return true;
	}

	private void refreshMapView() {
		Log.d(TAG, "Refreshing map view");
		if(context == null || context.getLastKnownLocation() == null) {
			Log.d(TAG, "Context or last location null, could not refresh map");
			return;
		}
		double latitude = context.getLastKnownLocation().getLatitude();
		double longitude = context.getLastKnownLocation().getLongitude();
		LatLng myLatLng = new LatLng(latitude, longitude);
		mapFragment.getMap().moveCamera(
				CameraUpdateFactory.newCameraPosition(CameraPosition
						.fromLatLngZoom(myLatLng, ZOOM)));

	}

	private void registerForMapTouchEvents() {
		GoogleMap map = mapFragment.getMap();
		if(map != null) {
			map.setOnMapClickListener(new OnMapClickListener() {
				@Override
				public void onMapClick(LatLng point) {
					Location location = new Location(LocationManager.PASSIVE_PROVIDER);//TODO IDK if this is correct to use
					location.setLatitude(point.latitude);
					location.setLongitude(point.longitude);
					updateLocationSearchText(location);
				}
			});
		}
		//TODO Long Click Listener
	}

	private void updateLocationSearchText(Location location) {
		//TODO http://developer.android.com/training/location/display-address.html
	}
	
	@Override
	public void onReceiveContext() {
		Log.d(TAG, "onReceiveContext start");
		refreshMapView();
	}
}
