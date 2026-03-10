package com.myhiking.app.ui.map;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u009a\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0013\u0018\u0000 E2\u00020\u0001:\u0002EFB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\b\u0010\u001e\u001a\u00020\fH\u0016J\u0018\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\b2\u0006\u0010\"\u001a\u00020 H\u0002J\u0010\u0010#\u001a\u00020\f2\u0006\u0010$\u001a\u00020%H\u0016J\b\u0010&\u001a\u00020\fH\u0002J\u001a\u0010\'\u001a\u0004\u0018\u00010(2\u0006\u0010)\u001a\u00020\u00192\u0006\u0010*\u001a\u00020+H\u0002J\u0016\u0010,\u001a\u00020\f2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.H\u0016J \u00100\u001a\u00020\f2\u0006\u00101\u001a\u0002022\u0006\u0010$\u001a\u00020%2\u0006\u00103\u001a\u000204H\u0017J\u0018\u00105\u001a\u00020\f2\u0006\u0010!\u001a\u00020\b2\u0006\u00106\u001a\u00020\u0006H\u0002J \u00107\u001a\u00020\f2\u0006\u00108\u001a\u00020\u000b2\u0006\u00109\u001a\u00020\u000b2\u0006\u0010:\u001a\u00020+H\u0016J\u0016\u0010;\u001a\u00020\f2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020\b0.H\u0016J\"\u0010=\u001a\u00020\f2\u0018\u0010>\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\nH\u0016J\u001c\u0010?\u001a\u00020\f2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u0012H\u0016J\u0012\u0010@\u001a\u00020\f2\b\u0010>\u001a\u0004\u0018\u00010\u0014H\u0016J\u001c\u0010A\u001a\u00020\f2\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f0\u0012H\u0016J\u0016\u0010B\u001a\u00020\f2\f\u0010-\u001a\b\u0012\u0004\u0012\u00020/0.H\u0016J\u0010\u0010C\u001a\u00020\f2\u0006\u0010D\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\"\u0010\t\u001a\u0016\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f\u0018\u00010\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\f\u0018\u00010\u0012X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\b0\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00190\u0017X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006G"}, d2 = {"Lcom/myhiking/app/ui/map/NaverMapManager;", "Lcom/myhiking/app/ui/map/MapManagerInterface;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "dragMarker", "Lcom/naver/maps/map/overlay/Marker;", "dragMountain", "Lcom/myhiking/app/data/model/MountainWithPhotos;", "emptyMapLongClickListener", "Lkotlin/Function2;", "", "", "isDragMode", "", "mapFragment", "Lcom/naver/maps/map/MapFragment;", "markerClickListener", "Lkotlin/Function1;", "markerDragListener", "Lcom/myhiking/app/ui/map/MapManagerInterface$OnMarkerDragResultListener;", "markerLongClickListener", "markerMountainMap", "", "markerOriginalPositions", "Lcom/naver/maps/geometry/LatLng;", "markers", "", "naverMap", "Lcom/naver/maps/map/NaverMap;", "clearMarkers", "createMarkerBitmap", "Landroid/graphics/Bitmap;", "mtn", "photoBitmap", "destroy", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "endDragMode", "findNearestMarkerInfo", "Lcom/myhiking/app/ui/map/NaverMapManager$NearestMarkerInfo;", "tapLatLng", "thresholdPx", "", "fitBoundsToLocations", "photos", "", "Lcom/myhiking/app/data/model/DevicePhoto;", "initialize", "container", "Landroid/view/ViewGroup;", "callback", "Lcom/myhiking/app/ui/map/MapManagerInterface$OnMapReadyCallback;", "loadMarkerThumbnail", "marker", "moveCamera", "lat", "lng", "zoom", "setMountainMarkers", "mountains", "setOnEmptyMapLongClickListener", "listener", "setOnMarkerClickListener", "setOnMarkerDragListener", "setOnMarkerLongClickListener", "setPhotoLocationMarkers", "snapMarkerBack", "mountain", "Companion", "NearestMarkerInfo", "app_debug"})
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
    @org.jetbrains.annotations.Nullable()
    private com.myhiking.app.ui.map.MapManagerInterface.OnMarkerDragResultListener markerDragListener;
    @org.jetbrains.annotations.NotNull()
    private final java.util.Map<com.naver.maps.map.overlay.Marker, com.naver.maps.geometry.LatLng> markerOriginalPositions = null;
    private boolean isDragMode = false;
    @org.jetbrains.annotations.Nullable()
    private com.naver.maps.map.overlay.Marker dragMarker;
    @org.jetbrains.annotations.Nullable()
    private com.myhiking.app.data.model.MountainWithPhotos dragMountain;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String MAP_FRAGMENT_TAG = "naver_map";
    @org.jetbrains.annotations.NotNull()
    private static final com.naver.maps.geometry.LatLng KOREA_CENTER = null;
    private static final float DEFAULT_ZOOM = 7.0F;
    @org.jetbrains.annotations.NotNull()
    private static final com.naver.maps.geometry.LatLngBounds KOREA_BOUNDS = null;
    
    /**
     * 마커 직접 터치 판정 (dp) — 이 범위 내 롱프레스 시 드래그 시작
     */
    private static final float DRAG_DIRECT_DP = 30.0F;
    
    /**
     * 마커 근처 터치 판정 (dp) — 이 범위 내 롱프레스 시 액션 메뉴
     */
    private static final float MARKER_NEAR_DP = 50.0F;
    @org.jetbrains.annotations.NotNull()
    public static final com.myhiking.app.ui.map.NaverMapManager.Companion Companion = null;
    
    public NaverMapManager(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @java.lang.Override()
    @android.annotation.SuppressLint(value = {"ClickableViewAccessibility"})
    public void initialize(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup container, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager fragmentManager, @org.jetbrains.annotations.NotNull()
    com.myhiking.app.ui.map.MapManagerInterface.OnMapReadyCallback callback) {
    }
    
    /**
     * 드래그 모드 종료 — 지도 제스처 복원
     */
    private final void endDragMode() {
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
    
    @java.lang.Override()
    public void setOnMarkerDragListener(@org.jetbrains.annotations.Nullable()
    com.myhiking.app.ui.map.MapManagerInterface.OnMarkerDragResultListener listener) {
    }
    
    @java.lang.Override()
    public void snapMarkerBack(@org.jetbrains.annotations.NotNull()
    com.myhiking.app.data.model.MountainWithPhotos mountain) {
    }
    
    /**
     * 지도 좌표에서 가장 가까운 마커 검색
     * @param thresholdPx 이 거리(px) 이내의 마커만 대상
     */
    private final com.myhiking.app.ui.map.NaverMapManager.NearestMarkerInfo findNearestMarkerInfo(com.naver.maps.geometry.LatLng tapLatLng, float thresholdPx) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\r"}, d2 = {"Lcom/myhiking/app/ui/map/NaverMapManager$Companion;", "", "()V", "DEFAULT_ZOOM", "", "DRAG_DIRECT_DP", "KOREA_BOUNDS", "Lcom/naver/maps/geometry/LatLngBounds;", "KOREA_CENTER", "Lcom/naver/maps/geometry/LatLng;", "MAP_FRAGMENT_TAG", "", "MARKER_NEAR_DP", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    /**
     * 가장 가까운 마커 정보 반환 (마커, 산 데이터, 화면 거리 px)
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0017H\u00d6\u0001J\t\u0010\u0018\u001a\u00020\u0019H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u001a"}, d2 = {"Lcom/myhiking/app/ui/map/NaverMapManager$NearestMarkerInfo;", "", "marker", "Lcom/naver/maps/map/overlay/Marker;", "mountain", "Lcom/myhiking/app/data/model/MountainWithPhotos;", "distPx", "", "(Lcom/naver/maps/map/overlay/Marker;Lcom/myhiking/app/data/model/MountainWithPhotos;F)V", "getDistPx", "()F", "getMarker", "()Lcom/naver/maps/map/overlay/Marker;", "getMountain", "()Lcom/myhiking/app/data/model/MountainWithPhotos;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
    static final class NearestMarkerInfo {
        @org.jetbrains.annotations.NotNull()
        private final com.naver.maps.map.overlay.Marker marker = null;
        @org.jetbrains.annotations.NotNull()
        private final com.myhiking.app.data.model.MountainWithPhotos mountain = null;
        private final float distPx = 0.0F;
        
        public NearestMarkerInfo(@org.jetbrains.annotations.NotNull()
        com.naver.maps.map.overlay.Marker marker, @org.jetbrains.annotations.NotNull()
        com.myhiking.app.data.model.MountainWithPhotos mountain, float distPx) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.naver.maps.map.overlay.Marker getMarker() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.myhiking.app.data.model.MountainWithPhotos getMountain() {
            return null;
        }
        
        public final float getDistPx() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.naver.maps.map.overlay.Marker component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.myhiking.app.data.model.MountainWithPhotos component2() {
            return null;
        }
        
        public final float component3() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.myhiking.app.ui.map.NaverMapManager.NearestMarkerInfo copy(@org.jetbrains.annotations.NotNull()
        com.naver.maps.map.overlay.Marker marker, @org.jetbrains.annotations.NotNull()
        com.myhiking.app.data.model.MountainWithPhotos mountain, float distPx) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}