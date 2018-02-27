package com.cathcart93.sling.core

import com.google.gson.*
import org.slf4j.LoggerFactory
import java.lang.reflect.Type
import com.google.gson.JsonElement

@Deprecated("Custom serializer will not be used anymore")
class ReactControllerSerializer : JsonSerializer<IReactController> {
    private val LOGGER = LoggerFactory.getLogger(ReactControllerSerializer::class.java)

    override fun serialize(reactController: IReactController?, type: Type?, context: JsonSerializationContext): JsonElement {
        val jsonObject = JsonObject()
        if (reactController == null) {
            return jsonObject
        }
        for (field in reactController.javaClass.declaredFields) {
            field.isAccessible = true
            try {
                val fieldValue = field.get(reactController)
                if (field.isAnnotationPresent(ReactProp::class.java) && fieldValue != null) {
                    val fieldName = field.getAnnotation(ReactProp::class.java).name
                    jsonObject.add(if (fieldName.isEmpty()) field.name else fieldName, context.serialize(fieldValue))
                }
                val el = context.serialize(fieldValue)
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