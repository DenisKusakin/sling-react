package com.cathcart93.sling.components.v2

interface ComponentV2<T> {
    fun render(props: T): Element
}