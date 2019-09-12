package com.cathcart93.sling.components.v2

import com.google.gson.Gson

object SpectaclePage : Component<SpectaclePageProps> {
    override fun render(props: SpectaclePageProps): Element {
        return "html" {
            "children" to mutableListOf<Element>().apply {
                this += "head" {
                    "children" to ("title" {
                        "children" to "Sling React Spectacle V3"
                    })
                }
                this += "body" {
                    "children" to mutableListOf<Element>().apply {
                        this += "div" {
                            "id" to "app"
                        }
                        this += SpectacleDataJsonScript {
                            props.content
                        }
                        this += SpectacleJsScript {
                            props.jsUrl
                        }
                        this += "script" {
                            "children" to "console.log('Hello, World!')"
                        }
                    }.map { ElementProp(it) }
                }
            }.map { ElementProp(it) }
        }
    }

}

data class SpectaclePageProps(val content: Element, val jsUrl: String)

object SpectacleDataJsonScript : Component<Element> {
    override fun render(props: Element): Element {
        val jsonRenderer = SimpleRecursiveRenderer()
        val json = jsonRenderer.render(props)
        val gson = Gson()
        val jsonString = gson.toJson(json)

        return "script" {
            "children" to "window.__DATA=$jsonString"
        }
    }
}

object SpectacleJsScript : Component<String> {
    override fun render(props: String): Element {
        return "script" {
            "src" to props
        }
    }

}