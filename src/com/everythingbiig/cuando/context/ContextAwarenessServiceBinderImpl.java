package com.everythingbiig.cuando.context;

import java.io.FileDescriptor;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;

public class ContextAwarenessServiceBinderImpl extends Binder implements IContextAwarenessServiceBinder {
		private static final String TAG = "ContextAwarenessServiceBinder";

		/**
		 * Get the last known location
		 */
		public static final int ACTION_GET_CONTEXT = 0;
		public static final int ACTION_REGISTER_FOR_CONTEXT_UPDATES = 1;
		public static final String DATA_ICONTEXT_AWARE = "com.everythingbiig.cuando.location.CONTEXT_AWARE";
		public static final String DATA_LOCATION = "com.everythingbiig.cuando.location.LOCATION";
		public static final String DATA_CONTEXT = "";

		private ContextAwarenessService service;

		public ContextAwarenessServiceBinderImpl(ContextAwarenessService service) {
			Log.d(TAG, "ContextAwarenessServiceBinder, service=" + service);
			this.service= service;
		}

		@Override
		public String getInterfaceDescriptor() {
			Log.d(TAG, "getInterfaceDescriptor called");
			return ContextAwarenessServiceBinderImpl.class.getSimpleName();
		}

		@Override
		public boolean pingBinder() {
			boolean isAlive = (service != null);
			Log.d(TAG, "pingBinder, isAlive=" + isAlive);
			return isAlive;
		}

		@Override
		public boolean isBinderAlive() {
			boolean isAlive = (service != null);
			Log.d(TAG, "isBinderAlive, isAlive=" + isAlive);
			return isAlive;
		}

		@Override
		public IInterface queryLocalInterface(String descriptor) {
			Log.d(TAG, "queryLocalInterface descriptor=" + descriptor + "\n\tReturning=" + this);
			return this;
		}

		@Override
		public void dump(FileDescriptor fd, String[] args) {
			// TODO Auto-generated method stub
			Log.d(TAG, "dump called");
		}

		@Override
		public void dumpAsync(FileDescriptor fd, String[] args) {
			// TODO Auto-generated method stub
			Log.d(TAG, "dumpAsync called");
		}

		@Override
		public void linkToDeath(DeathRecipient recipient, int flags) {
			// TODO Auto-generated method stub
			Log.d(TAG, "Link to death called, recipient=" + recipient + ", flags=" + flags);
		}

		@Override
		public boolean unlinkToDeath(DeathRecipient recipient, int flags) {
			// TODO Auto-generated method stub
			Log.d(TAG, "Unlink to death called, recipient=" + recipient + ", flags=" + flags);
			return false;
		}

		@Override
		public IBinder asBinder() {
			Log.d(TAG, "asBinder, returning=" + this);
			return this;
		}
		@Override
		public void registerForContextUpdates(ContextAware contextAware) {
			service.registerForContextUpdates(contextAware);
		}
		@Override
		public void broadcastContextUpdates() {
			// Do not let clients use this
		}
		@Override
		public Context getContext() {
			return service.getContext();
		}
}
