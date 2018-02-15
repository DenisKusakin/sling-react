package com.cathcart93.sling.components.models.spectacle.dialogs

import com.cathcart93.sling.components.models.spectacle.api.SimpleDialog
import org.apache.sling.api.resource.Resource

/**
 * @author Denis_Kusakin. 2/15/2018.
 */
fun codeDialog(resource: Resource): SimpleDialog {
    return simpleDialog(resource) {
        text("source", "Source")
        select("lang", "Language") {
            option("Java Script", "javascript")
        }
    }
}

fun deckDialog(resource: Resource): ThemedContainer {
    val dialog = containerDialog(resource) {
        component("Slide")
    }

    val theme = simpleDialog(resource) {
        color("primaryColor", "Primary Color")
        color("secondaryColor", "Secondary Color")
        color("tertiaryColor", "Tertiary Color")
        color("quarternaryColor", "Quarternary Color")

        select("primaryFont", "Primary Font") {
            option("Montserrat", "Montserrat")
            option("Helvetica", "Helvetica")
        }
        select("secondaryFont", "Secondary Font") {
            option("Montserrat", "Montserrat")
            option("Helvetica", "Helvetica")
        }
    }

    return ThemedContainer(dialog, theme)
}

fun headingDialog(resource: Resource): SimpleDialog {
    return simpleDialog(resource) {
        text("text", "Text")
        checkbox("caps", "Caps")
        checkbox("fir", "Fit")
        select("textColor", "Color") {
            option("Red", "red")
            option("Green", "green")
            option("Blue", "blue")
            option("Black", "black")
        }
        select("size", "Size") {
            option("H1", "1")
            option("H2", "2")
            option("H3", "3")
            option("H4", "4")
            option("H5", "5")
        }
        select("height", "Height") {
            option("1", "1")
            option("2", "2")
            option("3", "3")
            option("4", "4")
        }
    }
}

fun slideDialog(resource: Resource): ThemedContainer {
    val containerDialog = containerDialog(resource) {
        component("Heading", "Text Component") {
            prop("text", "Edit Title Here")
            prop("size", "3")
            prop("size", "3")
            prop("fit", "false")
        }
        component("Text", "Just a text component") {
            prop("text", "Edit Text Component Here")
        }
        component("BlockQuote", "Quote Block component") {
            prop("quote", "Edit Quote")
            prop("cite", "Author")
        }
        component("CodePane", "Use to show highlighted code") {
            prop("source", "function test(){}")
            prop("lang", "javascript")
        }
    }

    val theme = simpleDialog(resource) {
        select("bgColor", "Background Color") {
            option("Primary Color", "primary")
            option("Secondary Color", "secondary")
        }
        select("textColor", "Text Color") {
            option("Primary Color", "primary")
            option("Secondary Color", "secondary")
        }
    }

    return ThemedContainer(containerDialog, theme)
}

fun textDialog(resource: Resource): SimpleDialog {
    return simpleDialog(resource) {
        text(name = "text", title = "Text")
        checkbox(name = "fit", title = "Fit")
        select(name = "textColor", title = "Color") {
            option(label = "Red", value = "red")
            option(label = "Green", value = "green")
            option(label = "Blue", value = "blue")
            option(label = "Black", value = "black")
        }
        select(name = "lineHeight", title = "Line Height") {
            option("1", "1")
            option("2", "2")
            option("3", "3")
            option("4", "4")
        }
    }
}