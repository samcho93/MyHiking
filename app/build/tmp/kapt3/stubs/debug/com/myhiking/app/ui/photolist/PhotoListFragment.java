package com.myhiking.app.ui.photolist;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u0000 \"2\u00020\u0001:\u0001\"B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J$\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\b\u0010\u001c\u001a\u00020\u000eH\u0016J\u001a\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\u00172\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J\u0010\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u0011H\u0002J\u0010\u0010!\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u0011H\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\u00048BX\u0082\u0004\u00a2\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0010X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006#"}, d2 = {"Lcom/myhiking/app/ui/photolist/PhotoListFragment;", "Lcom/google/android/material/bottomsheet/BottomSheetDialogFragment;", "()V", "_binding", "Lcom/myhiking/app/databinding/FragmentPhotoListBinding;", "adapter", "Lcom/myhiking/app/ui/photolist/PhotoListAdapter;", "binding", "getBinding", "()Lcom/myhiking/app/databinding/FragmentPhotoListBinding;", "mountainWithPhotos", "Lcom/myhiking/app/data/model/MountainWithPhotos;", "onAddPhoto", "Lkotlin/Function0;", "", "onPhotoExclude", "Lkotlin/Function1;", "Lcom/myhiking/app/data/model/DevicePhoto;", "onPhotoReassign", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDestroyView", "onViewCreated", "view", "showPhotoActionDialog", "photo", "showPhotoViewer", "Companion", "app_debug"})
public final class PhotoListFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment {
    @org.jetbrains.annotations.Nullable()
    private com.myhiking.app.databinding.FragmentPhotoListBinding _binding;
    @org.jetbrains.annotations.Nullable()
    private com.myhiking.app.data.model.MountainWithPhotos mountainWithPhotos;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.DevicePhoto, kotlin.Unit> onPhotoReassign;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.DevicePhoto, kotlin.Unit> onPhotoExclude;
    @org.jetbrains.annotations.Nullable()
    private kotlin.jvm.functions.Function0<kotlin.Unit> onAddPhoto;
    private com.myhiking.app.ui.photolist.PhotoListAdapter adapter;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TAG = "PhotoListFragment";
    @org.jetbrains.annotations.Nullable()
    private static com.myhiking.app.data.model.MountainWithPhotos pendingData;
    @org.jetbrains.annotations.Nullable()
    private static kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.DevicePhoto, kotlin.Unit> pendingReassignCallback;
    @org.jetbrains.annotations.Nullable()
    private static kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.DevicePhoto, kotlin.Unit> pendingExcludeCallback;
    @org.jetbrains.annotations.Nullable()
    private static kotlin.jvm.functions.Function0<kotlin.Unit> pendingAddPhotoCallback;
    @org.jetbrains.annotations.NotNull()
    public static final com.myhiking.app.ui.photolist.PhotoListFragment.Companion Companion = null;
    
    public PhotoListFragment() {
        super();
    }
    
    private final com.myhiking.app.databinding.FragmentPhotoListBinding getBinding() {
        return null;
    }
    
    @java.lang.Override()
    public void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public android.view.View onCreateView(@org.jetbrains.annotations.NotNull()
    android.view.LayoutInflater inflater, @org.jetbrains.annotations.Nullable()
    android.view.ViewGroup container, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
        return null;
    }
    
    @java.lang.Override()
    public void onViewCreated(@org.jetbrains.annotations.NotNull()
    android.view.View view, @org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    /**
     * 사진 길게 눌렀을 때: 이동 / 제외 선택 다이얼로그
     */
    private final void showPhotoActionDialog(com.myhiking.app.data.model.DevicePhoto photo) {
    }
    
    private final void showPhotoViewer(com.myhiking.app.data.model.DevicePhoto photo) {
    }
    
    @java.lang.Override()
    public void onDestroyView() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002JP\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0016\b\u0002\u0010\u0011\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000b2\u0016\b\u0002\u0010\u0012\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000b2\u0010\b\u0002\u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001c\u0010\r\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u000bX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0014"}, d2 = {"Lcom/myhiking/app/ui/photolist/PhotoListFragment$Companion;", "", "()V", "TAG", "", "pendingAddPhotoCallback", "Lkotlin/Function0;", "", "pendingData", "Lcom/myhiking/app/data/model/MountainWithPhotos;", "pendingExcludeCallback", "Lkotlin/Function1;", "Lcom/myhiking/app/data/model/DevicePhoto;", "pendingReassignCallback", "newInstance", "Lcom/myhiking/app/ui/photolist/PhotoListFragment;", "mountain", "onPhotoReassign", "onPhotoExclude", "onAddPhoto", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.myhiking.app.ui.photolist.PhotoListFragment newInstance(@org.jetbrains.annotations.NotNull()
        com.myhiking.app.data.model.MountainWithPhotos mountain, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.DevicePhoto, kotlin.Unit> onPhotoReassign, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function1<? super com.myhiking.app.data.model.DevicePhoto, kotlin.Unit> onPhotoExclude, @org.jetbrains.annotations.Nullable()
        kotlin.jvm.functions.Function0<kotlin.Unit> onAddPhoto) {
            return null;
        }
    }
}