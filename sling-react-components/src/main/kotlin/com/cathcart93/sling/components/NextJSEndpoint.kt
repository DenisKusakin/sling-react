package com.cathcart93.sling.components

interface NextJSEndpoint {
    fun renderUrl(): String
    fun baseUrl(): String
}