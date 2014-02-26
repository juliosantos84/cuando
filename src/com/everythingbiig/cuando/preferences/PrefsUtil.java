package com.everythingbiig.cuando.preferences;

/**
 * Util for getting/setting common preferences
 * TODO Use android preferences to persist prefs
 * @author juliosantos
 *
 */
public class PrefsUtil {

	private static final float LOCATION_DISTANCE_INTERVAL = 5F;

	private static final long LOCATION_REFRESH_INTERVAL = 10000L;

	public static long getLocationRefreshInterval() {
		return LOCATION_REFRESH_INTERVAL;
	}

	public static float getLocationDistanceInterval() {
		return LOCATION_DISTANCE_INTERVAL;
	}

}
