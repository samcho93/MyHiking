package com.myhiking.app.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000 %2\u00020\u0001:\u0002%&B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0012\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002J\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0086@\u00a2\u0006\u0002\u0010\u000bJ \u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u000f0\u000e0\rH\u0086@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\u0010\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u00132\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0006H\u0002J\u0012\u0010\u0014\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u0087\u0001\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\u000e\b\u0002\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\t2\u0010\b\u0002\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\t2O\b\u0002\u0010\u0019\u001aI\u0012\u0013\u0012\u00110\u0006\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0013\u0012\u00110\u000f\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001e\u0012\u0013\u0012\u00110\u000f\u00a2\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001f\u0012\u0004\u0012\u00020 \u0018\u00010\u001aH\u0086@\u00a2\u0006\u0002\u0010!J\"\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00160\r2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0086@\u00a2\u0006\u0002\u0010$R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\'"}, d2 = {"Lcom/myhiking/app/data/repository/PhotoRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "extractFolder", "", "filePath", "getAllPhotoIds", "", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getPhotoFolders", "", "Lkotlin/Pair;", "", "getPhotoGeoInfo", "Lcom/myhiking/app/data/repository/PhotoRepository$PhotoGeoInfo;", "uri", "Landroid/net/Uri;", "readExifGps", "scanGeotaggedPhotos", "Lcom/myhiking/app/data/model/DevicePhoto;", "excludeIds", "allowedFolders", "onProgress", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "folder", "scanned", "found", "", "(Ljava/util/Set;Ljava/util/Set;Lkotlin/jvm/functions/Function3;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "scanPhotosByIds", "ids", "(Ljava/util/Set;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "PhotoGeoInfo", "app_debug"})
public final class PhotoRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TAG = "PhotoRepository";
    @org.jetbrains.annotations.NotNull()
    public static final com.myhiking.app.data.repository.PhotoRepository.Companion Companion = null;
    
    public PhotoRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    /**
     * 기기의 모든 사진 폴더 목록 조회 (폴더명 → 사진 수)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getPhotoFolders(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kotlin.Pair<java.lang.String, java.lang.Integer>>> $completion) {
        return null;
    }
    
    /**
     * 기기 사진 스캔. excludeIds에 있는 사진은 EXIF 파싱을 건너뜀 (증분 스캔)
     * @param excludeIds 이미 캐시된 사진 ID (EXIF 파싱 생략)
     * @param allowedFolders 스캔할 폴더 목록 (null이면 전체 스캔)
     * @param onProgress 스캔 진행 상태 콜백 (폴더명, 스캔 건수, GPS 발견 건수)
     * @return 새로 스캔된 사진만 반환 (캐시된 사진 제외)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object scanGeotaggedPhotos(@org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.Long> excludeIds, @org.jetbrains.annotations.Nullable()
    java.util.Set<java.lang.String> allowedFolders, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function3<? super java.lang.String, ? super java.lang.Integer, ? super java.lang.Integer, kotlin.Unit> onProgress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.myhiking.app.data.model.DevicePhoto>> $completion) {
        return null;
    }
    
    /**
     * MediaStore에 현재 존재하는 모든 사진 ID 반환 (삭제된 사진 감지용)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object getAllPhotoIds(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.Set<java.lang.Long>> $completion) {
        return null;
    }
    
    /**
     * 특정 사진 ID 목록의 메타데이터를 MediaStore에서 조회 (위치정보 없는 사진용)
     * GPS 데이터가 없어도 DevicePhoto로 반환 (lat=0, lng=0, alt=0)
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object scanPhotosByIds(@org.jetbrains.annotations.NotNull()
    java.util.Set<java.lang.Long> ids, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.myhiking.app.data.model.DevicePhoto>> $completion) {
        return null;
    }
    
    /**
     * 사진의 GPS 위치 정보를 EXIF에서 읽기 (3단계 폴백)
     *
     * 1단계: setRequireOriginal(uri) → 원본 EXIF 접근 (Android 10+ GPS 정밀 접근)
     * 2단계: 일반 content URI → 기기 촬영 사진은 GPS 포함 가능
     * 3단계: 파일 경로 직접 접근 → DATA 컬럼의 실제 파일 경로로 EXIF 읽기
     *
     * @param uri content:// URI
     * @param filePath MediaStore DATA 컬럼의 파일 경로 (nullable)
     */
    private final com.myhiking.app.data.repository.PhotoRepository.PhotoGeoInfo getPhotoGeoInfo(android.net.Uri uri, java.lang.String filePath) {
        return null;
    }
    
    /**
     * 파일 경로에서 상위 폴더명 추출
     * 예: /storage/emulated/0/DCIM/Camera/IMG.jpg → DCIM/Camera
     */
    private final java.lang.String extractFolder(java.lang.String filePath) {
        return null;
    }
    
    /**
     * URI에서 InputStream 열어 EXIF GPS 읽기
     */
    private final com.myhiking.app.data.repository.PhotoRepository.PhotoGeoInfo readExifGps(android.net.Uri uri) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/myhiking/app/data/repository/PhotoRepository$Companion;", "", "()V", "TAG", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0006\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0003H\u00c6\u0003J\'\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\b\u00a8\u0006\u0016"}, d2 = {"Lcom/myhiking/app/data/repository/PhotoRepository$PhotoGeoInfo;", "", "latitude", "", "longitude", "altitude", "(DDD)V", "getAltitude", "()D", "getLatitude", "getLongitude", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
    public static final class PhotoGeoInfo {
        private final double latitude = 0.0;
        private final double longitude = 0.0;
        private final double altitude = 0.0;
        
        public PhotoGeoInfo(double latitude, double longitude, double altitude) {
            super();
        }
        
        public final double getLatitude() {
            return 0.0;
        }
        
        public final double getLongitude() {
            return 0.0;
        }
        
        public final double getAltitude() {
            return 0.0;
        }
        
        public final double component1() {
            return 0.0;
        }
        
        public final double component2() {
            return 0.0;
        }
        
        public final double component3() {
            return 0.0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.myhiking.app.data.repository.PhotoRepository.PhotoGeoInfo copy(double latitude, double longitude, double altitude) {
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