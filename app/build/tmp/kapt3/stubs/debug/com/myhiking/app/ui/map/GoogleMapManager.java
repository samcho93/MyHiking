package com.myhiking.app.ui.map;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0007\n\u0002\b\t\u0018\u0000 42\u00020\u0001:\u00014B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0014\u001a\u00020\bH\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0018\u001a\u00020\u0016H\u0002J\u0010\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u001bH\u0016J\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u000f2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0016\u0010\u001f\u001a\u00020\b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0016J \u0010#\u001a\u00020\b2\u0006\u0010$\u001a\u00020%2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\'H\u0016J \u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\u00072\u0006\u0010*\u001a\u00020\u00072\u0006\u0010+\u001a\u00020,H\u0016J\u0016\u0010-\u001a\u00020\b2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020\u000f0!H\u0016J\"\u0010/\u001a\u00020\b2\u0018\u00100\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0016J\u001c\u00101\u001a\u00020\b2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\b0\u000eH\u0016J\u001c\u00102\u001a\u00020\b2\u0012\u00100\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\b0\u000eH\u0016J\u0016\u00103\u001a\u00020\b2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\"0!H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u000f0\u0011X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0013\u001a\u0010\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\b\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00065"}, d2 = {"Lcom/myhiking/app/ui/map/GoogleMapManager;", "Lcom/myhiking/app/ui/map/MapManagerInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "emptyMapLongClickListener", "Lkotlin/Function2;", "", "", "googleMap", "Lcom/google/android/gms/maps/GoogleMap;", "mapFragment", "Lcom/google/android/gms/maps/SupportMapFragment;", "markerClickListener", "Lkotlin/Function1;", "Lcom/myhiking/app/data/model/MountainWithPhotos;", "markerDataMap", "", "", "markerLongClickListener", "clearMarkers", "createMarkerBitmap", "Landroid/graphics/Bitmap;", "mtn", "photoBitmap", "destroy", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "findNearestMarkerMountain", "tapLatLng", "Lcom/google/android/gms/maps/model/LatLng;", "fitBoundsToLocations", "photos", "", "Lcom/myhiking/app/data/model/DevicePhoto;", "initialize", "container", "Landroid/view/ViewGroup;", "callback", "Lcom/myhiking/app/ui/map/MapManagerInterface$OnMapReadyCallback;", "moveCamera", "lat", "lng", "zoom", "", "setMountainMarkers", "mountains", "setOnEmptyMapLongClickListener", "listener", "setOnMarkerClickListener", "setOnMarkerLongClickListener", "setPhotoLocationMarkers", "Companion", "app_debug"})
public final class GoogleMapManager implements com.myhiking.app.ui.map.MapManagerInterface {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.maps.GoogleMap googleMap;
    @org.jetbrains.annotations.Nullable()
    private com.google.android.gms.maps.SupportMapFragment mapFragment;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.MountainWithPhotos, kotlin.Unit> markerClickListener;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.MountainWithPhotos, kotlin.Unit> markerLongClickListener;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function2<? super java.lang.Double, ? super java.lang.Double, kotlin.Unit> emptyMapLongClickListener;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<java.lang.String, com.myhiking.app.data.model.MountainWithPhotos> markerDataMap = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String MAP_FRAGMENT_TAG = "google_map";
    @org.jetbrains.annotations.NotNull()
    public static final com.myhiking.app.ui.map.GoogleMapManager.Companion Companion = null;
    
    public GoogleMapManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    public void initialize(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup container, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager fragmentManager, @org.jetbrains.annotations.NotNull()
    com.myhiking.app.ui.map.MapManagerInterface.OnMapReadyCallback callback) {
    }
    
    @java.lang.Override()
    public void destroy(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager fragmentManager) {
    }
    
    @java.lang.Override()
    public void setMountainMarkers(@org.jetbrains.annotations.NotNull()
    java.util.List<com.myhiking.app.data.model.MountainWithPhotos> mountains) {
    }
    
    private final android.graphics.Bitmap createMarkerBitmap(com.myhiking.app.data.model.MountainWithPhotos mtn, android.graphics.Bitmap photoBitmap) {
        return null;
    }
    
    @java.lang.Override()
    public void setPhotoLocationMarkers(@org.jetbrains.annotations.NotNull()
    java.util.List<com.myhiking.app.data.model.DevicePhoto> photos) {
    }
    
    @java.lang.Override()
    public void clearMarkers() {
    }
    
    @java.lang.Override()
    public void moveCamera(double lat, double lng, float zoom) {
    }
    
    @java.lang.Override()
    public void fitBoundsToLocations(@org.jetbrains.annotations.NotNull()
    java.util.List<com.myhiking.app.data.model.DevicePhoto> photos) {
    }
    
    @java.lang.Override()
    public void setOnMarkerClickListener(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.MountainWithPhotos, kotlin.Unit> listener) {
    }
    
    @java.lang.Override()
    public void setOnMarkerLongClickListener(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.MountainWithPhotos, kotlin.Unit> listener) {
    }
    
    @java.lang.Override()
    public void setOnEmptyMapLongClickListener(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Double, ? super java.lang.Double, kotlin.Unit> listener) {
    }
    
    private final com.myhiking.app.data.model.MountainWithPhotos findNearestMarkerMountain(com.google.android.gms.maps.model.LatLng tapLatLng) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/myhiking/app/ui/map/GoogleMapManager$Companion;", "", "()V", "MAP_FRAGMENT_TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}