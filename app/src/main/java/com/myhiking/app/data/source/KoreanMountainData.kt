package com.myhiking.app.data.source

import android.content.Context
import com.myhiking.app.data.model.Mountain
import org.json.JSONArray

object KoreanMountainData {

    private var cachedMountains: List<Mountain>? = null

    fun getMountains(context: Context): List<Mountain> {
        cachedMountains?.let { return it }

        val mountains = loadFromAssets(context)
        cachedMountains = mountains
        return mountains
    }

    private fun loadFromAssets(context: Context): List<Mountain> {
        val json = context.assets.open("mountains.json")
            .bufferedReader()
            .use { it.readText() }

        val jsonArray = JSONArray(json)
        val mountains = mutableListOf<Mountain>()

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            mountains.add(
                Mountain(
                    id = i + 1,
                    name = obj.getString("name"),
                    nameEn = obj.optString("nameEn", ""),
                    latitude = obj.getDouble("lat"),
                    longitude = obj.getDouble("lng"),
                    altitude = obj.getInt("alt"),
                    region = obj.getString("region")
                )
            )
        }

        return mountains
    }
}
