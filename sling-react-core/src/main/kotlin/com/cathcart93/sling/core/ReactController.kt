package com.cathcart93.sling.core

import java.lang.annotation.Inherited

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.CLASS)
@Inherited
@Deprecated("Not supported anymore")
annotation class ReactController(val componentName: String)