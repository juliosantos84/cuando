package com.everythingbiig.cuando.context;

import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.util.Log;

public class ContextAwarenessUtil {
	private static final String TAG = "ContextAwarenessUtil";
	
	public static Intent getAwarenessServiceIntent(Context context) {
		return new Intent(context, ContextAwarenessServiceImpl.class);
	}

	public static boolean startService(Context context) {
		if(context == null) {
			return false;
		}
		ComponentName name = context.startService(getAwarenessServiceIntent(context));
		if(name == null) {
			Log.d(TAG, "Could not start service");
			return false;
		}
		Log.d(TAG, "Started service: " + name);
		return true;
	}

	public static boolean bindToService(Context context, ServiceConnection serviceConnection) {
		Log.d(TAG, "Binding context = " + context + ", serviceConnection = " + serviceConnection);

		if(context == null || serviceConnection == null) {
			return false;
		}
		// Start the service
		startService(context);
		// Bind to the service
		boolean bound = context.bindService(getAwarenessServiceIntent(context), serviceConnection, Service.START_STICKY);
		if(!bound) {
			Log.d(TAG, "Could not bind to service");
		}
		Log.d(TAG, "Service bound: " + bound);
		return bound;
	}
}
