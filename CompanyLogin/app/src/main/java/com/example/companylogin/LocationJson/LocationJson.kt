package com.example.companylogin.LocationJson

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

data class LocationJson(
    val name: String,
    val latitude: Double,
    val longitude: Double
)

fun getLocationJsonDataFromAsset(
    context: Context,
    fileName: String
): String? {
    return try {
        context.assets.open(fileName).bufferedReader().use { it.readText() }
    } catch (exp: IOException) {
        exp.printStackTrace()
        null
    }
}

fun officeList(context: Context, fileName: String): List<LocationJson> {
    val jsonFileString = getLocationJsonDataFromAsset(context, fileName)
    return if (jsonFileString != null) {
        val type = object : TypeToken<List<LocationJson>>() {}.type
        Gson().fromJson(jsonFileString, type)
    } else {
        emptyList()
    }
}
