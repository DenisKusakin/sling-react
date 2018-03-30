package com.cathcart93.sling.componentsv2.react

sealed trait ReactPropValue
case class StringProp(value: String) extends ReactPropValue
case class IntProp(value: Int) extends ReactPropValue
case class ObjectProp(props: Map[String, ReactPropValue]) extends ReactPropValue
case class ReactComponentProp(component: ReactComponent) extends ReactPropValue

case class ReactComponent(componentName: String, props: Map[String, ReactPropValue] = Map(), children: Seq[ReactPropValue] = Seq())
