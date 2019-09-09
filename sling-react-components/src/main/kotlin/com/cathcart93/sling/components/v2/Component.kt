package com.cathcart93.sling.components.v2

interface Component<T> {
    fun render(props: T): Element
}