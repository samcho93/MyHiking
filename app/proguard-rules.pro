-keep public class * implements com.bumptech.glide.module.GlideModule
-keep class * extends com.bumptech.glide.module.AppGlideModule { <init>(...); }
-keep public enum com.bumptech.glide.load.ImageHeaderParser$** { **[] $VALUES; public *; }
-keep class com.bumptech.glide.load.data.ParcelFileDescriptorRewinder$InternalRewinder { *** rewind(); }

-keep class com.naver.maps.** { *; }
-dontwarn com.naver.maps.**

-keep class com.google.android.gms.maps.** { *; }
