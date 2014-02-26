package com.everythingbiig.cuando.context;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.everythingbiig.cuando.R;
import com.everythingbiig.cuando.notifications.NotificationUtil;
import com.everythingbiig.cuando.preferences.PrefsUtil;

/**
 * A service that listens for and tracks the different contexts (locations) that
 * the device user is in.
 * 
 * @author juliosantos
 * 
 */
//TODO Need to keep this service alive.
public class ContextAwarenessServiceImpl extends Service implements ContextAwarenessService {

	private Map<String, ContextAware> listeners;

	private final static String TAG = "ContextAwarenessService";

	private Location lastKnownLocation;

	public ContextAwarenessServiceImpl () {
		
	}
	
	@Override
	public void onCreate() {
		Log.d(TAG, "onCreate start");
		super.onCreate();
		this.listeners = new HashMap<String, ContextAware> ();
		// Register for location updates
		LocationManager service = (LocationManager) getSystemService(LOCATION_SERVICE);
		service.requestLocationUpdates(LocationManager.GPS_PROVIDER, PrefsUtil.getLocationRefreshInterval(), PrefsUtil.getLocationDistanceInterval(), new LocationListener() {
			@Override
			public void onLocationChanged(Location location) {
				lastKnownLocation = location;
				broadcastContextUpdates();
			}
			@Override
			public void onProviderDisabled(String provider) {
//				Toast.makeText(getApplicationContext(), "Provider disabled: " + provider, Toast.LENGTH_LONG).show();
				Log.d(TAG, "Provider disabled: " + provider);
			}
			@Override
			public void onProviderEnabled(String provider) {
				//Toast.makeText(getApplicationContext(), "Provider enabled: " + provider, Toast.LENGTH_LONG).show();
				Log.d(TAG, "Provider enabled: " + provider);
			}
			@Override
			public void onStatusChanged(String provider, int status, Bundle extras) {
				//Toast.makeText(getApplicationContext(), "Provider status changed: " + provider + "/" + status, Toast.LENGTH_LONG).show();
				Log.d(TAG, "Provider status changed: " + provider + "/" + status);
			}
		});

		NotificationUtil.sendNotification(this, 
				android.R.drawable.ic_menu_compass,
				R.string.notification_awarenessservice_ticker,
				R.string.notification_awenessservicestarted_title,
				R.string.notification_awarenessservicestarted_text, 
				null, true);
		Log.d(TAG, "onCreate end");
	}

	

	public Location getLastKnownLocation() {
		return this.lastKnownLocation;
	}

	@Override
	public void registerForContextUpdates(ContextAware contextAware) {
		if(contextAware == null) {
			Log.d(TAG, "Tried to register a null intent");
			return;
		}
		Log.d(TAG, "Registering a new context for updates: " + contextAware.getName());
		this.listeners.put(contextAware.getName(), contextAware);
	}

	@Override
	public void broadcastContextUpdates() {
//		sendNotification();//TODO
		Log.d(TAG, "Broadcasting updates");
		if(this.listeners == null || this.listeners.isEmpty()) {
			Log.d(TAG, "No listeners...");
			return;
		}
		Context context = getContext();
		for(ContextAware c : this.listeners.values()) {
			try {
				c.receiveUpdate(context);
				c.onReceiveContext();
			} catch (Exception e) {
				Log.d(TAG, "Error broadcasting context updates");
			}
		}
	}

	@Override
	public Context getContext() {
		return new Context() {
			@Override
			public Location getLastKnownLocation() {
				return ContextAwarenessServiceImpl.this.lastKnownLocation;
			}

			@Override
			public int describeContents() {
				return 0;
			}

			@Override
			public void writeToParcel(Parcel dest, int flags) {
				dest.writeParcelable(ContextAwarenessServiceImpl.this.lastKnownLocation, 0);
			}
		};
	}

	@Override
    public IBinder onBind(Intent intent) {
		Log.d(TAG, "onBind: " + intent);
		return new ContextAwarenessServiceBinderImpl(this);
	}
	
}
