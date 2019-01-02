package com.znt.speaker.util;

import android.content.Context;
import android.location.LocationManager;

public class LocationUtils 
{
	/**
	 * Location service if enable
	 *
	 * @param context
	 * @return location is enable if return true, otherwise disable.
	 */
	public static final boolean isLocationEnable(Context context) {
	    LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
	    boolean networkProvider = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
	    boolean gpsProvider = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
	    if (networkProvider || gpsProvider) return true;
	    return false;
	}
}
