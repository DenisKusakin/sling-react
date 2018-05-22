package com.cathcart93.sling.core

import java.lang.annotation.Inherited

@Retention
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
@Inherited
annotation class ReactProp(val name: String = "")