package com.myhiking.app.ui.map;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J \u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\"H\u0002J\u0010\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&H\u0002J\u0018\u0010\'\u001a\u00020\u001c2\u0006\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020\u0011H\u0002J\u0012\u0010*\u001a\u00020\u001c2\b\u0010+\u001a\u0004\u0018\u00010,H\u0014J\b\u0010-\u001a\u00020\u001cH\u0002J\b\u0010.\u001a\u00020\u001cH\u0002J\b\u0010/\u001a\u00020\u001cH\u0002J\u0018\u00100\u001a\u00020\u001c2\u0006\u00101\u001a\u00020\"2\u0006\u00102\u001a\u00020\"H\u0002J\b\u00103\u001a\u00020\u001cH\u0002J\u0010\u00104\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u00105\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0018\u00106\u001a\u00020\u001c2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u0011H\u0002J\u0010\u0010:\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002J\u0010\u0010;\u001a\u00020\u001c2\u0006\u0010<\u001a\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0012\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e\u00a2\u0006\u0004\n\u0002\u0010\u000fR\u0010\u0010\u0010\u001a\u0004\u0018\u00010\u0011X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\f\u0012\n \u0014*\u0004\u0018\u00010\u00110\u00110\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006="}, d2 = {"Lcom/myhiking/app/ui/map/MapActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "binding", "Lcom/myhiking/app/databinding/ActivityMapBinding;", "currentMapManager", "Lcom/myhiking/app/ui/map/MapManagerInterface;", "googleMapManager", "Lcom/myhiking/app/ui/map/GoogleMapManager;", "isLocationViewMode", "", "naverMapManager", "Lcom/myhiking/app/ui/map/NaverMapManager;", "pendingAddPhotoMountainId", "", "Ljava/lang/Integer;", "pendingAddPhotoMountainName", "", "photoPickerLauncher", "Landroidx/activity/result/ActivityResultLauncher;", "kotlin.jvm.PlatformType", "viewModel", "Lcom/myhiking/app/ui/map/MapViewModel;", "getViewModel", "()Lcom/myhiking/app/ui/map/MapViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "confirmExcludeAllPhotos", "", "mountain", "Lcom/myhiking/app/data/model/MountainWithPhotos;", "handleMarkerDrop", "source", "dropLat", "", "dropLng", "initializeMap", "provider", "Lcom/myhiking/app/data/model/MapProvider;", "launchPhotoPicker", "mountainId", "mountainName", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupClickListeners", "setupMarkerClickListener", "setupObservers", "showEmptyMapLongPressDialog", "lat", "lng", "showFolderSelectDialog", "showMarkerLongPressDialog", "showPhotoList", "showPhotoReassign", "photo", "Lcom/myhiking/app/data/model/DevicePhoto;", "currentMountainName", "showReassignSheet", "switchMap", "newProvider", "app_debug"})
public final class MapActivity extends androidx.appcompat.app.AppCompatActivity {
    private com.myhiking.app.databinding.ActivityMapBinding binding;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy viewModel$delegate = null;
    @org.jetbrains.annotations.Nullable()
    private com.myhiking.app.ui.map.MapManagerInterface currentMapManager;
    @org.jetbrains.annotations.Nullable()
    private com.myhiking.app.ui.map.NaverMapManager naverMapManager;
    @org.jetbrains.annotations.Nullable()
    private com.myhiking.app.ui.map.GoogleMapManager googleMapManager;
    private boolean isLocationViewMode = false;
    @org.jetbrains.annotations.Nullable()
    private java.lang.Integer pendingAddPhotoMountainId;
    @org.jetbrains.annotations.Nullable()
    private java.lang.String pendingAddPhotoMountainName;
    
    /**
     * 갤러리에서 사진 선택 결과 처리
     */
    @org.jetbrains.annotations.NotNull()
    private final androidx.activity.result.ActivityResultLauncher<java.lang.String> photoPickerLauncher = null;
    
    public MapActivity() {
        super();
    }
    
    private final com.myhiking.app.ui.map.MapViewModel getViewModel() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void setupObservers() {
    }
    
    private final void setupClickListeners() {
    }
    
    private final void initializeMap(com.myhiking.app.data.model.MapProvider provider) {
    }
    
    private final void switchMap(com.myhiking.app.data.model.MapProvider newProvider) {
    }
    
    private final void setupMarkerClickListener() {
    }
    
    /**
     * 마커 길게 누르기 → 선택 다이얼로그 (전체 이동 / 전체 제외 / 사진 추가)
     */
    private final void showMarkerLongPressDialog(com.myhiking.app.data.model.MountainWithPhotos mountain) {
    }
    
    /**
     * 전체 사진 제외 확인 다이얼로그
     */
    private final void confirmExcludeAllPhotos(com.myhiking.app.data.model.MountainWithPhotos mountain) {
    }
    
    private final void showReassignSheet(com.myhiking.app.data.model.MountainWithPhotos mountain) {
    }
    
    private final void showPhotoList(com.myhiking.app.data.model.MountainWithPhotos mountain) {
    }
    
    /**
     * 개별 사진을 다른 산으로 이동하는 바텀시트 표시
     */
    private final void showPhotoReassign(com.myhiking.app.data.model.DevicePhoto photo, java.lang.String currentMountainName) {
    }
    
    /**
     * 빈 지도 영역 길게 누르기 → 근처 산 DB에서 검색 → 산 선택 → 사진 추가
     * 마커가 없는 산봉우리/산 위치에서도 사진을 추가할 수 있음
     */
    private final void showEmptyMapLongPressDialog(double lat, double lng) {
    }
    
    /**
     * 마커 드래그 드롭 처리: 드롭 위치 근처의 산을 찾아 재배치
     */
    private final void handleMarkerDrop(com.myhiking.app.data.model.MountainWithPhotos source, double dropLat, double dropLng) {
    }
    
    /**
     * 스캔 폴더 선택 다이얼로그 표시
     */
    private final void showFolderSelectDialog() {
    }
    
    /**
     * 갤러리에서 사진 선택 (위치정보 없는 사진을 산에 추가)
     */
    private final void launchPhotoPicker(int mountainId, java.lang.String mountainName) {
    }
}