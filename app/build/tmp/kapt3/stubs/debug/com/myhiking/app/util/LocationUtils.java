package com.myhiking.app.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J&\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004J.\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/myhiking/app/util/LocationUtils;", "", "()V", "EARTH_RADIUS_METERS", "", "distanceInMeters", "lat1", "lng1", "lat2", "lng2", "isWithinRadius", "", "radiusMeters", "app_debug"})
public final class LocationUtils {
    private static final double EARTH_RADIUS_METERS = 6371000.0;
    @org.jetbrains.annotations.NotNull()
    public static final com.myhiking.app.util.LocationUtils INSTANCE = null;
    
    private LocationUtils() {
        super();
    }
    
    public final double distanceInMeters(double lat1, double lng1, double lat2, double lng2) {
        return 0.0;
    }
    
    public final boolean isWithinRadius(double lat1, double lng1, double lat2, double lng2, double radiusMeters) {
        return false;
    }
}