package com.cathcart93.sling.core

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
annotation class ReactController(val componentName: String = "")