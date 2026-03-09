package com.myhiking.app.util

import android.content.ContentUris
import android.content.Context
import android.net.Uri
import android.provider.MediaStore

object PhotoPickerHelper {

    /**
     * 갤러리 picker에서 반환된 URI를 MediaStore ID로 변환
     * content://media/... 또는 content://com.android.providers.media.photopicker/...
     */
    fun getMediaStoreId(context: Context, uri: Uri): Long? {
        // 1. content://media/external/images/media/123 형식 → 직접 ID 추출
        try {
            val id = ContentUris.parseId(uri)
            if (id > 0) return id
        } catch (_: Exception) {}

        // 2. Photo Picker URI (Android 13+) → MediaStore 쿼리로 ID 찾기
        try {
            context.contentResolver.query(
                uri,
                arrayOf(MediaStore.Images.Media._ID),
                null, null, null
            )?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val idCol = cursor.getColumnIndex(MediaStore.Images.Media._ID)
                    if (idCol >= 0) {
                        return cursor.getLong(idCol)
                    }
                }
            }
        } catch (_: Exception) {}

        // 3. URI의 마지막 path segment를 ID로 시도
        try {
            val lastSegment = uri.lastPathSegment
            if (lastSegment != null) {
                val id = lastSegment.toLongOrNull()
                if (id != null && id > 0) return id
            }
        } catch (_: Exception) {}

        return null
    }
}
