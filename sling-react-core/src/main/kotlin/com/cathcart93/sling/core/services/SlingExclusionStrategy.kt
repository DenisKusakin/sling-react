package com.cathcart93.sling.core.services

import com.cathcart93.sling.core.ExcludeFromSrialization
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes

class SlingExclusionStrategy(vararg private val classToExclude: Class<*>) : ExclusionStrategy {

    override fun shouldSkipClass(clazz: Class<*>): Boolean {
        return classToExclude.any {
            it == clazz
        }
    }

    override fun shouldSkipField(field: FieldAttributes): Boolean {
        return field.getAnnotation(ExcludeFromSrialization::class.java) != null
    }
}