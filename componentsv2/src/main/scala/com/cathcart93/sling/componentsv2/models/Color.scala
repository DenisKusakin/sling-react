package com.cathcart93.sling.componentsv2.models

sealed trait Color
case object Primary extends Color
case object Secondary extends Color
case object Tertiary extends Color
case object Quarternary extends Color
case class HexColor(str: String) extends Color

object Color {
  def valueOf(str: String): Option[Color] = {
    str match {
      case "primary" => Some(Primary)
      case "secondary" => Some(Secondary)
      case "tertiary" => Some(Tertiary)
      case "quarternary" => Some(Tertiary)
      case x if x.startsWith("#") => Some(HexColor(x))
      case _ => None
    }
  }
}