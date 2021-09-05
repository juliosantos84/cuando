package com.everythingbiig.cuando;

import android.app.Activity;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

import com.everythingbiig.cuando.context.Context;
import com.everythingbiig.cuando.context.ContextAware;
import com.everythingbiig.cuando.context.ContextAwarenessServiceBinderImpl;
import com.everythingbiig.cuando.context.ContextAwarenessUtil;

public abstract class BaseContextAwareActivity extends Activity implements ContextAware {

	private static final String TAG = "BaseContextAwareActivity";

	ContextAwarenessServiceBinderImpl serviceBinder;
	Context context;

	protected ServiceConnection serviceConnection = new ServiceConnection() {
		public void onServiceConnected(ComponentName className, IBinder service) {
			Log.d("ServiceConnection", "Connected to service with binder: " + service);
			serviceBinder = (ContextAwarenessServiceBinderImpl)service;
			Log.d("ServiceConnection", "Service binder: " + serviceBinder);
			serviceBinder.registerForContextUpdates(BaseContextAwareActivity.this);
			Log.d(TAG, "Registered for updates");
			Context context = serviceBinder.getContext();
			Log.d(TAG, "Got context=" + context);
		}

		public void onServiceDisconnected(ComponentName className) {
			Log.d(TAG, "Disconnected from service");
			serviceBinder = null;
		}
	};

	@Override
	public String getName() {
		return CuandoLocationActivity.class.getSimpleName();
	}

	@Override
	public abstract void onReceiveContext();

	@Override
	public boolean bindToContextAwarenessService() {
		//TODO Don't do this if youre already bound
		boolean bound = ContextAwarenessUtil.bindToService(getApplicationContext(), serviceConnection);
		return bound;
	}

	@Override
	public void receiveUpdate(Context context) {
		Log.d(TAG, "Receiving update");
		this.context = context;
		Log.d(TAG, "finished receiving update");
	}
}
