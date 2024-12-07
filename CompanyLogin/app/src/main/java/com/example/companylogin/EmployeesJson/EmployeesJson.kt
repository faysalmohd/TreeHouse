package com.example.companylogin.EmployeesJson

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

data class EmployeesJson(
    val name: String,
    val title: String,
    val email: String,
    val phone: String,
    val img: String,
    val position: String
)

fun getJsonDataFromAsset(
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

fun employeesList(context: Context, fileName: String): List<EmployeesJson> {
    val jsonFileString = getJsonDataFromAsset(context, fileName)
    return if (jsonFileString != null) {
        val type = object : TypeToken<List<EmployeesJson>>() {}.type
        Gson().fromJson(jsonFileString, type)
    } else {
        emptyList()
    }
}
