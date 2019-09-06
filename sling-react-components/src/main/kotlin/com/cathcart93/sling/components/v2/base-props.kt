package com.cathcart93.sling.components.v2

open class BaseProps {
    var italic: Boolean? = null
    var bold: Boolean? = null
    var caps: Boolean? = null
    var margin: Int? = null
    var padding: Int? = null
    var textColor: String? = null
    var textSize: Int? = null
    var bgColor: String? = null
    var bgGradient: String? = null
    var height: Int? = null
}

fun BaseProps.asProps(): ObjectProp {
    val map = mutableMapOf<String, PrimitiveProp>()
    italic?.let { map["italic"] = BooleanProp(it) }
    bold?.let { map["bold"] = BooleanProp(it) }
    caps?.let { map["caps"] = BooleanProp(it) }
    margin?.let { map["margin"] = NumberProp(it) }
    padding?.let { map["padding"] = NumberProp(it) }
    textColor?.let { map["textColor"] = StringProp(it) }
    textSize?.let { map["textSize"] = NumberProp(it) }
    bgColor?.let { map["bgColor"] = StringProp(it) }
    bgGradient?.let { map["bgGradient"] = StringProp(it) }
    height?.let { map["height"] = NumberProp(it) }

    return ObjectProp(map)
}