package io.github.backjeff.chucknorrisjokes.data_local.extension

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

fun Any.toJson(): String = Gson().toJson(this)

fun <T> String.fromJson(): T? {
    val type: Type = object : TypeToken<T>() {}.rawType
    return try {
        Gson().fromJson(this, type)
    } catch (e: RuntimeException) {
        null
    }
}