package com.myhiking.app.util;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001d\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a2\u0006\u0002\u0010\t\u00a8\u0006\n"}, d2 = {"Lcom/myhiking/app/util/PhotoPickerHelper;", "", "()V", "getMediaStoreId", "", "context", "Landroid/content/Context;", "uri", "Landroid/net/Uri;", "(Landroid/content/Context;Landroid/net/Uri;)Ljava/lang/Long;", "app_debug"})
public final class PhotoPickerHelper {
    @org.jetbrains.annotations.NotNull()
    public static final com.myhiking.app.util.PhotoPickerHelper INSTANCE = null;
    
    private PhotoPickerHelper() {
        super();
    }
    
    /**
     * 갤러리 picker에서 반환된 URI를 MediaStore ID로 변환
     * content://media/... 또는 content://com.android.providers.media.photopicker/...
     */
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Long getMediaStoreId(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return null;
    }
}