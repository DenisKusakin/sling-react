package com.cathcart93.sling.components.v2

import com.google.gson.Gson

object SpectaclePage : Component<SpectaclePageProps> {
    override fun render(props: SpectaclePageProps): Element {
        return "html" {
            "children" list {
                item(
                        "head" {
                            "children" to ("title" {
                                "children" to "Sling React Spectacle V2"
                            })
                        }
                )
                item(
                        "body" {
                            "children" list {
                                item(
                                        "div" {
                                            "id" to "app"
                                        }
                                )
                                item(
                                        SpectacleScript {
                                            props.content
                                        }
                                )
                                item (
                                        SpectacleJsScript {
                                            props.jsUrl
                                        }
                                )
                                item (
                                        "script" {
                                            "children" to "console.log('Hello, World!')"
                                        }
                                )
                            }
                        }
                )
            }
        }
    }

}

data class SpectaclePageProps(val content: Element, val jsUrl: String)

object SpectacleScript : Component<Element> {
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