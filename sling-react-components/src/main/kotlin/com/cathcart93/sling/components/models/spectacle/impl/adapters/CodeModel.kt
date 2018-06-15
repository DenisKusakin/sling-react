package com.cathcart93.sling.components.models.spectacle.impl.adapters

import com.cathcart93.sling.components.models.spectacle.api.Constants
import com.cathcart93.sling.components.models.spectacle.impl.builder.*
import org.apache.sling.api.resource.Resource
import org.apache.sling.models.annotations.DefaultInjectionStrategy
import org.apache.sling.models.annotations.Model
import org.apache.sling.models.annotations.injectorspecific.SlingObject
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue

@Model(
        adaptables = [Resource::class],
        defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL,
        adapters = [CodeModel::class, ReactModel::class],
        resourceType = [Constants.CODE_PANE]
)
class CodeModel : BaseModel(), ReactModel {

    @ValueMapValue
    var source: String? = null

    @ValueMapValue
    var lang: String = "javascript"

    @ValueMapValue
    var theme: String = "dark"

    @SlingObject
    private lateinit var resource: Resource

    override fun toReact(isEditMode: Boolean): SpectacleTag {
        val component = code(source = if (source == null) "" else source!!, lang = lang.toCodeLang(), theme = theme.toTheme()) {
            italic = this@CodeModel.italic
            bold = this@CodeModel.bold
            caps = this@CodeModel.caps
        }
        return if (!isEditMode)
            component
        else
            component.edit {
                deleteUrl = resource.path
                editUrl = resource.path
                multilineText(name = "source", title = "Source", value = if (source == null) "" else source!!)
                select(name = "lang", title = "Language", value = lang, options = listOf(
                        SelectOption(label = "JavaScript", value = "javascript"),
                        SelectOption(label = "JSX", value = "jsx"),
                        SelectOption(label = "JSON", value = "json")
                ))
                select(name = "theme", title = "Theme", value = theme, options = listOf(
                        SelectOption(label = "Dark", value = "dark"),
                        SelectOption(label = "Light", value = "light"),
                        SelectOption(label = "External", value = "external")
                ))
            }
    }

    private fun String.toCodeLang(): CodeLang {
        return when (this) {
            "javascript" -> JavaScript
            "jsx" -> ReactJSX
            "json" -> JSON
//            "kotlin" -> Kotlin
            else -> JavaScript
        }
    }

    private fun String.toTheme(): CodeTheme {
        return when(this){
            "dark" -> DarkCodeTheme
            "light" -> LightCodeTheme
            "external" -> ExternalCodeTheme
            else -> DarkCodeTheme
        }
    }

}