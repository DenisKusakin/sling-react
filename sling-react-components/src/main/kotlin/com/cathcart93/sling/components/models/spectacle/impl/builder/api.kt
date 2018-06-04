package com.cathcart93.sling.components.models.spectacle.impl.builder

import com.cathcart93.sling.components.models.spectacle.impl.builder.react.ReactProp
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.TagName

interface Tag {
    val tagName: String
    val props: MutableMap<String, ReactProp>
    val children: MutableList<ReactProp>
}

open class ReactTag(
        override val tagName: String,
        override val props: MutableMap<String, ReactProp>,
        override val children: MutableList<ReactProp>) : Tag


//interface TagConsumer<out R> {
//    fun onTagStart(tag: Tag)
//    fun onTagEnd(tag: Tag)
//    fun onTagError(tag: Tag, exception: Throwable): Unit = throw exception
//    fun finalize(): R
//}
//
//interface Tag {
//    val tagName: String
//    val consumer: TagConsumer<*>
//
//    val props: MutableMap<String, Property>
//}
//
//abstract class Property {
//    open operator fun get(thisRef: Tag, propName: String) = thisRef.props[propName]
//    operator fun set(thisRef: Tag, propName: String, value: Property){
//        thisRef.props[propName] = value
//    }
//}
//
//fun <T, C: TagConsumer<T>> C.deck(block: Deck.() -> Unit) : T = Deck(this).visitAndFinalize(this, block)
//
//class StringProperty(val value: String) : Property()
//class BooleanProperty(val value: Boolean) : Property()
//class IntProperty(val value: Int) : Property()
//
//open class ReactTag(override val tagName: String, override val consumer: TagConsumer<*>) : Tag {
//    override val props: MutableMap<String, Property> = mutableMapOf()
//}
//
//fun <T : Tag> T.visit(block: T.() -> Unit) {
//    consumer.onTagStart(this)
//    try {
//        this.block()
//    } catch (err: Throwable) {
//        consumer.onTagError(this, err)
//    } finally {
//        consumer.onTagEnd(this)
//    }
//}
//
//fun <T: Tag, R> T.visitAndFinalize(consumer: TagConsumer<R>, block: T.() -> Unit) : R {
//    visit(block)
//    return consumer.finalize();
//}