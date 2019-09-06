package com.cathcart93.sling.components.v2

import org.apache.sling.api.resource.ValueMap

fun ValueMap.asBoolean(name: String): Boolean? {
    return this.get(name, java.lang.Boolean::class.java) as Boolean?
}

fun ValueMap.asString(name: String): String? {
    return this.get(name, java.lang.String::class.java) as String?
}

fun ValueMap.asInt(name: String): Int? {
    return this.get(name, java.lang.Integer::class.java) as Int?
}