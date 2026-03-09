package com.myhiking.app.ui.map;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\b\r\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001c\u0010\"\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u00072\u0006\u0010&\u001a\u00020\'J\u000e\u0010(\u001a\u00020#2\u0006\u0010)\u001a\u00020%J\u0014\u0010*\u001a\u00020#2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020%0\u0007J(\u0010+\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020-\u0012\u0004\u0012\u00020.0,0\u00072\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020.J\u0006\u00101\u001a\u00020#J\u0016\u00102\u001a\u00020#2\u0006\u00103\u001a\u00020\u000f2\u0006\u00104\u001a\u00020\'J\u0016\u00105\u001a\u00020#2\u0006\u0010)\u001a\u00020%2\u0006\u00104\u001a\u00020\'J\u000e\u00106\u001a\u00020#H\u0082@\u00a2\u0006\u0002\u00107J\u0010\u00108\u001a\u00020#2\b\u00109\u001a\u0004\u0018\u00010\u000fJ\u0006\u0010:\u001a\u00020#R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\n0\n0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001c\u0010\f\u001a\u0010\u0012\f\u0012\n \u000b*\u0004\u0018\u00010\r0\r0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001a\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\n0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0014R\u0017\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\r0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0014R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u001bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00070\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0014R\u000e\u0010\u001e\u001a\u00020\u001fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010 \u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\u0012\u00a2\u0006\b\n\u0000\u001a\u0004\b!\u0010\u0014\u00a8\u0006;"}, d2 = {"Lcom/myhiking/app/ui/map/MapViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "_allPhotos", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/myhiking/app/data/model/DevicePhoto;", "_currentProvider", "Lcom/myhiking/app/data/model/MapProvider;", "kotlin.jvm.PlatformType", "_isLoading", "", "_mountainsWithPhotos", "Lcom/myhiking/app/data/model/MountainWithPhotos;", "_selectedMountain", "allPhotos", "Landroidx/lifecycle/LiveData;", "getAllPhotos", "()Landroidx/lifecycle/LiveData;", "currentProvider", "getCurrentProvider", "isLoading", "matchingCache", "Lcom/myhiking/app/data/cache/MatchingCache;", "mountainRepository", "Lcom/myhiking/app/data/repository/MountainRepository;", "mountainsWithPhotos", "getMountainsWithPhotos", "photoRepository", "Lcom/myhiking/app/data/repository/PhotoRepository;", "selectedMountain", "getSelectedMountain", "addPhotosToMountain", "", "photoIds", "", "mountainId", "", "excludePhoto", "photoId", "excludePhotos", "findNearbyMountains", "Lkotlin/Pair;", "Lcom/myhiking/app/data/model/Mountain;", "", "lat", "lng", "loadPhotosAndMatch", "reassignAllPhotos", "source", "targetMountainId", "reassignSinglePhoto", "rematchAll", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "selectMountain", "mountain", "switchMapProvider", "app_debug"})
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