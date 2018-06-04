package com.cathcart93.sling.components.models.spectacle.dialogs

import com.cathcart93.sling.components.models.spectacle.api.SimpleDialog
import org.apache.sling.api.resource.Resource

/**
 * @author Denis_Kusakin. 2/15/2018.
 */

fun baseDialog(resource: Resource): SimpleDialog {
    return simpleDialog(resource) {
        checkbox("italic", "Italic")
        checkbox("bold", "Bold")
        checkbox("caps", "Caps")
        text("margin", "Margin")
        text("padding", "Padding")
        color("textColor", "Text Color")
        text("textSize", "Text Size")
        select("textAlign", "Text Align") {
            option("center", "center")
            option("justify", "justify")
            option("left", "left")
            option("right", "right")
            option("auto", "auto")
            option("inherit", "inherit")
            option("start", "start")
            option("end", "end")
        }
        color("bgColor", "Background Color")
    }
}

fun codeDialog(resource: Resource): SimpleDialog {
    return simpleDialog(resource) {
        multilineText("source", "Source")
        select("lang", "Language") {
            option("Java Script", "javascript")
        }
        dialog(baseDialog(resource))
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
        dialog(baseDialog(resource))
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

fun linkDialog(resource: Resource): SimpleDialog {
    return simpleDialog(resource) {
        text("href", "Href")
        text("text", "Text")
        select("target", "Target"){
            option("Blank", "_blank")
            option("Self", "_self")
            option("Parent", "_parent")
            option("Top", "_top")
        }
        dialog(baseDialog(resource))
    }
}

fun imageDialog(resource: Resource): SimpleDialog {
    return simpleDialog(resource){
        text("src", "Image src")
        text("width", "Width")
        text("height", "Height")
        dialog(baseDialog(resource))
    }
}

fun slideDialog(resource: Resource): ThemedContainer {
    val containerDialog = containerDialog(resource) {
        component("IHeading", "Text Component") {
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
        component("Link", "Simple Link component"){
            prop("href", "http://example.com")
        }
        component("Image", "Image component"){

        }
        component("Appear", "Appear"){

        }
    }

    val theme = simpleDialog(resource) {
        //        select("bgColor", "Background Color") {
//            option("Primary Color", "primary")
//            option("Secondary Color", "secondary")
//        }
//        select("textColor", "Text Color") {
//            option("Primary Color", "primary")
//            option("Secondary Color", "secondary")
//        }
        dialog(baseDialog(resource))
    }

    return ThemedContainer(containerDialog, theme)
}

fun textDialog(resource: Resource): SimpleDialog {
    return simpleDialog(resource) {
        multilineText(name = "text", title = "Text")
        dialog(baseDialog(resource))
        checkbox(name = "fit", title = "Fit")
//        select(name = "textColor", title = "Color") {
//            option(label = "Red", value = "red")
//            option(label = "Green", value = "green")
//            option(label = "Blue", value = "blue")
//            option(label = "Black", value = "black")
//        }
        select(name = "lineHeight", title = "Line Height") {
            option("1", "1")
            option("2", "2")
            option("3", "3")
            option("4", "4")
        }
    }
}