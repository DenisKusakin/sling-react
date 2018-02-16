package com.cathcart93.sling.core

import java.lang.annotation.Inherited

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.PROPERTY)
@Inherited
annotation class ReactProp(val name: String = "")