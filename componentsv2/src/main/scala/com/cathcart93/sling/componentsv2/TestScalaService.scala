package com.cathcart93.sling.componentsv2

import org.apache.felix.scr.annotations.{Component, Service}

@Service(Array(classOf[TestScalaService]))
@Component(immediate = true)
class TestScalaService() {
  def activate(): Unit = {
    val res =
    System.out.print("Hello from Scala!")
  }
}