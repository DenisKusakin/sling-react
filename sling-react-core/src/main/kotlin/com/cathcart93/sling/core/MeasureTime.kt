package com.cathcart93.sling.core

inline fun <T>measureExecTime(function: () -> T): Pair<T, Long> {
    val start = System.currentTimeMillis()
    val res = function()
    return Pair(res, System.currentTimeMillis() - start)
}