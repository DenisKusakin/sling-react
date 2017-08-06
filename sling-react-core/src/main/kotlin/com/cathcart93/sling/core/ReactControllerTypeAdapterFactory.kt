package com.cathcart93.sling.core

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.TypeAdapterFactory
import com.google.gson.reflect.TypeToken

class ReactControllerTypeAdapterFactory : TypeAdapterFactory {
    override fun <T : Any?> create(gson: Gson?, type: TypeToken<T>?): TypeAdapter<T>? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        val rawType = type!!.rawType as Class<T>
        if(!rawType.javaClass.isAnnotationPresent(ReactController::class.java)) {
            return null
        }

    }
}