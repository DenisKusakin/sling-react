import com.google.gson.JsonElement

interface JsonRenderer {
    fun render(element: Element) : JsonElement
}