package com.cathcart93.sling.core

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
annotation class ReactProp(val name: String = "")