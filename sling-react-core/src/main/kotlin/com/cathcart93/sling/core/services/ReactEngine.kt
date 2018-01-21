package com.cathcart93.sling.core.services

interface ReactEngine {
    fun render(props: String, scriptPath: String): String
}