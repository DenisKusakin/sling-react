package com.cathcart93.sling.components.models.spectacle.impl.builder.react.v2

fun <T> createContext(value: T): Context<T> {
    TODO("")
}

fun <T> createContext(): Context<T> {
    return ContextImpl()
}