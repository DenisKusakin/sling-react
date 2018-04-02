package com.cathcart93.sling.componentsv2.react

sealed trait ReactPropValue
case class ReactStringProp(value: String) extends ReactPropValue
case class ReactIntProp(value: Int) extends ReactPropValue
case class ReactBooleanProp(value: Boolean) extends ReactPropValue
case class ReactObjectProp(props: Map[String, ReactPropValue]) extends ReactPropValue
case class ReactComponentProp(component: ReactComponent) extends ReactPropValue

case class ReactComponent(componentName: String, props: Map[String, ReactPropValue] = Map(), children: Seq[ReactPropValue] = Seq())
