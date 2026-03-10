package com.myhiking.app.ui.map;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010*\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u00072\u0006\u0010.\u001a\u00020\u0011J\u0006\u0010/\u001a\u00020+J\u000e\u00100\u001a\u00020+2\u0006\u00101\u001a\u00020-J\u0014\u00102\u001a\u00020+2\f\u0010,\u001a\b\u0012\u0004\u0012\u00020-0\u0007J(\u00103\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u000206040\u00072\u0006\u00107\u001a\u0002062\u0006\u00108\u001a\u000206J,\u00109\u001a\u00020+2$\u0010:\u001a \u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u0011040\u0007\u0012\u0004\u0012\u00020+0;J\u0006\u0010<\u001a\u00020+J\"\u0010=\u001a\u00020+2\u001a\u0010:\u001a\u0016\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010>\u0012\u0004\u0012\u00020+0;J\u0016\u0010?\u001a\u00020+2\u0006\u0010@\u001a\u00020\u000f2\u0006\u0010A\u001a\u00020\u0011J\u0016\u0010B\u001a\u00020+2\u0006\u00101\u001a\u00020-2\u0006\u0010A\u001a\u00020\u0011J\u000e\u0010C\u001a\u00020+H\u0082@\u00a2\u0006\u0002\u0010DJ\u0006\u0010E\u001a\u00020+J\u0014\u0010F\u001a\u00020+2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u00130>J\u0010\u0010H\u001a\u00020+2\b\u0010I\u001a\u0004\u0018\u00010\u000fJ\u0006\u0010J\u001a\u00020+R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\r0\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0012\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0017\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\n0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\r0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0018R\u000e\u0010\u001c\u001a\u00020\u001dX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0018R\u000e\u0010\"\u001a\u00020#X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010$\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b%\u0010\u0018R\u0019\u0010&\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\'\u0010\u0018R\u0019\u0010(\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010\u0018\u00a8\u0006K"}, d2 = {"Lcom/myhiking/app/ui/map/MapViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_allPhotos", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/myhiking/app/data/model/DevicePhoto;", "_currentProvider", "Lcom/myhiking/app/data/model/MapProvider;", "kotlin.jvm.PlatformType", "_isLoading", "", "_mountainsWithPhotos", "Lcom/myhiking/app/data/model/MountainWithPhotos;", "_rescanResult", "", "_scanStatus", "", "_selectedMountain", "allPhotos", "Landroidx/lifecycle/LiveData;", "getAllPhotos", "()Landroidx/lifecycle/LiveData;", "currentProvider", "getCurrentProvider", "isLoading", "matchingCache", "Lcom/myhiking/app/data/cache/MatchingCache;", "mountainRepository", "Lcom/myhiking/app/data/repository/MountainRepository;", "mountainsWithPhotos", "getMountainsWithPhotos", "photoRepository", "Lcom/myhiking/app/data/repository/PhotoRepository;", "rescanResult", "getRescanResult", "scanStatus", "getScanStatus", "selectedMountain", "getSelectedMountain", "addPhotosToMountain", "", "photoIds", "", "mountainId", "clearRescanResult", "excludePhoto", "photoId", "excludePhotos", "findNearbyMountains", "Lkotlin/Pair;", "Lcom/myhiking/app/data/model/Mountain;", "", "lat", "lng", "loadPhotoFolders", "callback", "Lkotlin/Function1;", "loadPhotosAndMatch", "loadScanFolders", "", "reassignAllPhotos", "source", "targetMountainId", "reassignSinglePhoto", "rematchAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "rescanPhotos", "saveScanFoldersAndRescan", "folders", "selectMountain", "mountain", "switchMapProvider", "app_debug"})
public final class MapViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.myhiking.app.data.repository.PhotoRepository photoRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myhiking.app.data.repository.MountainRepository mountainRepository = null;
    @org.jetbrains.annotations.NotNull()
    private final com.myhiking.app.data.cache.MatchingCache matchingCache = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.myhiking.app.data.model.MountainWithPhotos>> _mountainsWithPhotos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.myhiking.app.data.model.MountainWithPhotos>> mountainsWithPhotos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.util.List<com.myhiking.app.data.model.DevicePhoto>> _allPhotos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.myhiking.app.data.model.DevicePhoto>> allPhotos = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.myhiking.app.data.model.MapProvider> _currentProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.myhiking.app.data.model.MapProvider> currentProvider = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Boolean> _isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<com.myhiking.app.data.model.MountainWithPhotos> _selectedMountain = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<com.myhiking.app.data.model.MountainWithPhotos> selectedMountain = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.Integer> _rescanResult = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.Integer> rescanResult = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<java.lang.String> _scanStatus = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.lang.String> scanStatus = null;
    
    public MapViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.myhiking.app.data.model.MountainWithPhotos>> getMountainsWithPhotos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.myhiking.app.data.model.DevicePhoto>> getAllPhotos() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.myhiking.app.data.model.MapProvider> getCurrentProvider() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Boolean> isLoading() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<com.myhiking.app.data.model.MountainWithPhotos> getSelectedMountain() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.Integer> getRescanResult() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.lang.String> getScanStatus() {
        return null;
    }
    
    public final void loadPhotosAndMatch() {
    }
    
    /**
     * 마커 전체(모든 사진)를 다른 산으로 일괄 이동
     */
    public final void reassignAllPhotos(@org.jetbrains.annotations.NotNull()
    com.myhiking.app.data.model.MountainWithPhotos source, int targetMountainId) {
    }
    
    /**
     * 개별 사진 한 장을 다른 산으로 이동
     */
    public final void reassignSinglePhoto(long photoId, int targetMountainId) {
    }
    
    /**
     * 사진을 목록에서 제외 (실제 삭제 아님, 썸네일만 숨김)
     */
    public final void excludePhoto(long photoId) {
    }
    
    /**
     * 여러 사진을 한꺼번에 제외
     */
    public final void excludePhotos(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> photoIds) {
    }
    
    /**
     * 위치정보 없는 사진을 특정 산에 추가 (사용자 오버라이드로 저장)
     * @param photoIds 추가할 사진 ID 목록
     * @param mountainId 대상 산 ID
     */
    public final void addPhotosToMountain(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> photoIds, int mountainId) {
    }
    
    private final java.lang.Object rematchAll(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    /**
     * 새 사진 다시 스캔: 이전 캐시 이후 추가된 사진만 증분 스캔하여 추가
     */
    public final void rescanPhotos() {
    }
    
    public final void clearRescanResult() {
    }
    
    /**
     * 기기의 모든 사진 폴더 목록 조회 (폴더명, 사진 수)
     */
    public final void loadPhotoFolders(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.List<kotlin.Pair<java.lang.String, java.lang.Integer>>, kotlin.Unit> callback) {
    }
    
    /**
     * 현재 저장된 스캔 폴더 설정 로드
     */
    public final void loadScanFolders(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.util.Set<java.lang.String>, kotlin.Unit> callback) {
    }
    
    /**
     * 스캔 대상 폴더 저장 후 캐시 초기화 및 전체 재스캔
     */
    public final void saveScanFoldersAndRescan(@org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.String> folders) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<kotlin.Pair<com.myhiking.app.data.model.Mountain, java.lang.Double>> findNearbyMountains(double lat, double lng) {
        return null;
    }
    
    public final void switchMapProvider() {
    }
    
    public final void selectMountain(@org.jetbrains.annotations.Nullable()
    com.myhiking.app.data.model.MountainWithPhotos mountain) {
    }
}