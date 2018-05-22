package com.cathcart93.sling.core

import com.google.gson.*
import org.slf4j.LoggerFactory
import java.lang.reflect.Type
import com.google.gson.JsonElement
import kotlin.reflect.KProperty1
import kotlin.reflect.KVisibility
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.*


class ReactControllerSerializer : JsonSerializer<IReactController> {
    private val LOGGER = LoggerFactory.getLogger(ReactControllerSerializer::class.java)

    override fun serialize(reactController: IReactController?, type: Type?, context: JsonSerializationContext): JsonElement {
        val jsonObject = JsonObject()
        if (reactController == null) {
            return jsonObject
        }
        val l = reactController::class.memberProperties.filter {
            it.findAnnotation<ReactProp>() != null
        }
        reactController::class.memberProperties
                .filter {
                    it.findAnnotation<ReactProp>() != null
                }
                .filter { it.visibility == KVisibility.PUBLIC }
                .forEach { field: KProperty1<out IReactController, Any?> ->
//                    field.isAccessible = true
                    try {
                        val fieldValue = field.getter.call(reactController)//field.javaField!!.get(reactController)
                        if (fieldValue != null) {
                            val fieldName = field.findAnnotation<ReactProp>()!!.name
                            jsonObject.add(if (fieldName.isEmpty()) field.name else fieldName, context.serialize(fieldValue))
                        }
                    } catch (e: IllegalAccessException) {
                        LOGGER.error(e.message, e)
                    }
                }
        val componentName = getComponentName(reactController)
        if (componentName != null) {
            jsonObject.add("__type", JsonPrimitive(componentName))
        }

        return jsonObject
    }

    private fun getComponentName(controller: IReactController): String? {
        return controller.javaClass.getAnnotation(ReactController::class.java)?.componentName
    }
}