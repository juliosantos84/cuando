package com.everythingbiig.cuando.context;

import android.location.Location;
import android.os.Parcelable;

/**
 * Encapsulates the client context
 * TODO Make this parcelable so it can be passed around via IPC.
 * @author juliosantos
 *
 */
public interface Context extends Parcelable {

	Location getLastKnownLocation();
	
}
