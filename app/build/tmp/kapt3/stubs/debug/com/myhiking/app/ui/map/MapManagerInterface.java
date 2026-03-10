package com.myhiking.app.ui.map;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\bf\u0018\u00002\u00020\u0001:\u0002$%J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u0007\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&J \u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000fH&J \u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015H&J\u0016\u0010\u0016\u001a\u00020\u00032\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\tH&J\"\u0010\u0019\u001a\u00020\u00032\u0018\u0010\u001a\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u00030\u001bH&J\u001c\u0010\u001c\u001a\u00020\u00032\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00030\u001dH&J\u0012\u0010\u001e\u001a\u00020\u00032\b\u0010\u001a\u001a\u0004\u0018\u00010\u001fH&J\u001c\u0010 \u001a\u00020\u00032\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00030\u001dH&J\u0016\u0010!\u001a\u00020\u00032\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH&J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u0018H&\u00a8\u0006&"}, d2 = {"Lcom/myhiking/app/ui/map/MapManagerInterface;", "", "clearMarkers", "", "destroy", "fragmentManager", "Landroidx/fragment/app/FragmentManager;", "fitBoundsToLocations", "photos", "", "Lcom/myhiking/app/data/model/DevicePhoto;", "initialize", "container", "Landroid/view/ViewGroup;", "callback", "Lcom/myhiking/app/ui/map/MapManagerInterface$OnMapReadyCallback;", "moveCamera", "lat", "", "lng", "zoom", "", "setMountainMarkers", "mountains", "Lcom/myhiking/app/data/model/MountainWithPhotos;", "setOnEmptyMapLongClickListener", "listener", "Lkotlin/Function2;", "setOnMarkerClickListener", "Lkotlin/Function1;", "setOnMarkerDragListener", "Lcom/myhiking/app/ui/map/MapManagerInterface$OnMarkerDragResultListener;", "setOnMarkerLongClickListener", "setPhotoLocationMarkers", "snapMarkerBack", "mountain", "OnMapReadyCallback", "OnMarkerDragResultListener", "app_debug"})
public abstract interface MapManagerInterface {
    
    public abstract void initialize(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup container, @org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager fragmentManager, @org.jetbrains.annotations.NotNull()
    com.myhiking.app.ui.map.MapManagerInterface.OnMapReadyCallback callback);
    
    public abstract void destroy(@org.jetbrains.annotations.NotNull()
    androidx.fragment.app.FragmentManager fragmentManager);
    
    public abstract void setMountainMarkers(@org.jetbrains.annotations.NotNull()
    java.util.List<com.myhiking.app.data.model.MountainWithPhotos> mountains);
    
    public abstract void setPhotoLocationMarkers(@org.jetbrains.annotations.NotNull()
    java.util.List<com.myhiking.app.data.model.DevicePhoto> photos);
    
    public abstract void clearMarkers();
    
    public abstract void moveCamera(double lat, double lng, float zoom);
    
    public abstract void fitBoundsToLocations(@org.jetbrains.annotations.NotNull()
    java.util.List<com.myhiking.app.data.model.DevicePhoto> photos);
    
    public abstract void setOnMarkerClickListener(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.MountainWithPhotos, kotlin.Unit> listener);
    
    public abstract void setOnMarkerLongClickListener(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.MountainWithPhotos, kotlin.Unit> listener);
    
    /**
     * 마커가 없는 빈 지도 영역 롱프레스 시 호출 (lat, lng 전달)
     */
    public abstract void setOnEmptyMapLongClickListener(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Double, ? super java.lang.Double, kotlin.Unit> listener);
    
    /**
     * 마커 드래그 리스너 설정
     */
    public abstract void setOnMarkerDragListener(@org.jetbrains.annotations.Nullable()
    com.myhiking.app.ui.map.MapManagerInterface.OnMarkerDragResultListener listener);
    
    /**
     * 마커를 원래 위치로 되돌리기 (드래그 취소 시)
     */
    public abstract void snapMarkerBack(@org.jetbrains.annotations.NotNull()
    com.myhiking.app.data.model.MountainWithPhotos mountain);
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&\u00a8\u0006\u0004"}, d2 = {"Lcom/myhiking/app/ui/map/MapManagerInterface$OnMapReadyCallback;", "", "onMapReady", "", "app_debug"})
    public static abstract interface OnMapReadyCallback {
        
        public abstract void onMapReady();
    }
    
    /**
     * 마커 드래그 결과 리스너
     */
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&\u00a8\u0006\n"}, d2 = {"Lcom/myhiking/app/ui/map/MapManagerInterface$OnMarkerDragResultListener;", "", "onDragEnd", "", "mountain", "Lcom/myhiking/app/data/model/MountainWithPhotos;", "dropLat", "", "dropLng", "onDragStart", "app_debug"})
    public static abstract interface OnMarkerDragResultListener {
        
        public abstract void onDragStart(@org.jetbrains.annotations.NotNull()
        com.myhiking.app.data.model.MountainWithPhotos mountain);
        
        public abstract void onDragEnd(@org.jetbrains.annotations.NotNull()
        com.myhiking.app.data.model.MountainWithPhotos mountain, double dropLat, double dropLng);
    }
}