package com.myhiking.app.ui.map;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\t\u0018\u0000 82\u00020\u0001:\u00018B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u0016\u001a\u00020\bH\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u0018H\u0002J\u0010\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0012\u0010\u001e\u001a\u0004\u0018\u00010\r2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0016\u0010!\u001a\u00020\b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0016J \u0010%\u001a\u00020\b2\u0006\u0010&\u001a\u00020\'2\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010(\u001a\u00020)H\u0016J\u0018\u0010*\u001a\u00020\b2\u0006\u0010\u0019\u001a\u00020\r2\u0006\u0010+\u001a\u00020\u0011H\u0002J \u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\u00072\u0006\u0010.\u001a\u00020\u00072\u0006\u0010/\u001a\u000200H\u0016J\u0016\u00101\u001a\u00020\b2\f\u00102\u001a\b\u0012\u0004\u0012\u00020\r0#H\u0016J\"\u00103\u001a\u00020\b2\u0018\u00104\u001a\u0014\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u0006H\u0016J\u001c\u00105\u001a\u00020\b2\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\b0\fH\u0016J\u001c\u00106\u001a\u00020\b2\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\b0\fH\u0016J\u0016\u00107\u001a\u00020\b2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#H\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\"\u0010\u0005\u001a\u0016\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000b\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\b\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\b\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\r0\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00069"}, d2 = {"Lcom/myhiking/app/ui/map/NaverMapManager;", "Lcom/myhiking/app/ui/map/MapManagerInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "emptyMapLongClickListener", "Lkotlin/Function2;", "", "", "mapFragment", "Lcom/naver/maps/map/MapFragment;", "markerClickListener", "Lkotlin/Function1;", "Lcom/myhiking/app/data/model/MountainWithPhotos;", "markerLongClickListener", "markerMountainMap", "", "Lcom/naver/maps/map/overlay/Marker;", "markers", "", "naverMap", "Lcom/naver/maps/map/NaverMap;", "clearMarkers", "createMarkerBitmap", "Landroid/graphics/Bitmap;", "mtn", "photoBitmap", "destroy", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "findNearestMarkerMountain", "tapLatLng", "Lcom/naver/maps/geometry/LatLng;", "fitBoundsToLocations", "photos", "", "Lcom/myhiking/app/data/model/DevicePhoto;", "initialize", "container", "Landroid/view/ViewGroup;", "callback", "Lcom/myhiking/app/ui/map/MapManagerInterface$OnMapReadyCallback;", "loadMarkerThumbnail", "marker", "moveCamera", "lat", "lng", "zoom", "", "setMountainMarkers", "mountains", "setOnEmptyMapLongClickListener", "listener", "setOnMarkerClickListener", "setOnMarkerLongClickListener", "setPhotoLocationMarkers", "Companion", "app_debug"})
public final class NaverMapManager implements com.myhiking.app.ui.map.MapManagerInterface {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.Nullable()
    private com.naver.maps.map.NaverMap naverMap;
    @org.jetbrains.annotations.Nullable()
    private com.naver.maps.map.MapFragment mapFragment;
    @org.jetbrains.annotations.NotNull()
    private final java.util.List<com.naver.maps.map.overlay.Marker> markers = null;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.MountainWithPhotos, kotlin.Unit> markerClickListener;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.MountainWithPhotos, kotlin.Unit> markerLongClickListener;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function2<? super java.lang.Double, ? super java.lang.Double, kotlin.Unit> emptyMapLongClickListener;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<com.naver.maps.map.overlay.Marker, com.myhiking.app.data.model.MountainWithPhotos> markerMountainMap = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String MAP_FRAGMENT_TAG = "naver_map";
    @org.jetbrains.annotations.NotNull()
    private static final com.naver.maps.geometry.LatLng KOREA_CENTER = null;
    private static final float DEFAULT_ZOOM = 7.0F;
    @org.jetbrains.annotations.NotNull()
    private static final com.naver.maps.geometry.LatLngBounds KOREA_BOUNDS = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.myhiking.app.ui.map.NaverMapManager.Companion Companion = null;
    
    public NaverMapManager(@org.jetbrains.annotations.NotNull()
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
    
    private final void loadMarkerThumbnail(com.myhiking.app.data.model.MountainWithPhotos mtn, com.naver.maps.map.overlay.Marker marker) {
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
    
    /**
     * 지도 좌표에서 가장 가까운 마커의 산 정보 반환 (long-press 감지용)
     * 화면상 50dp 이내의 마커만 대상
     */
    private final com.myhiking.app.data.model.MountainWithPhotos findNearestMarkerMountain(com.naver.maps.geometry.LatLng tapLatLng) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/myhiking/app/ui/map/NaverMapManager$Companion;", "", "()V", "DEFAULT_ZOOM", "", "KOREA_BOUNDS", "Lcom/naver/maps/geometry/LatLngBounds;", "KOREA_CENTER", "Lcom/naver/maps/geometry/LatLng;", "MAP_FRAGMENT_TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}