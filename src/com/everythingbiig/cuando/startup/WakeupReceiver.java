package com.everythingbiig.cuando.startup;

import com.everythingbiig.cuando.context.ContextAwarenessServiceImpl;
import com.everythingbiig.cuando.context.ContextAwarenessUtil;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * Performs operations on device wake up.
 * @author juliosantos
 *
 */
public class WakeupReceiver extends BroadcastReceiver {

	private static final String TAG = "WakeupReceiver";

	@Override
	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "Received intent: " + intent);
		startContextAwarenessService(context);
	}

	/**
	 * Start the context awareness service.
	 * @param context
	 */
	private void startContextAwarenessService(Context context) {
		Log.i(TAG, "Starting cuando services...");
		ContextAwarenessUtil.startService(context);
	}

}
