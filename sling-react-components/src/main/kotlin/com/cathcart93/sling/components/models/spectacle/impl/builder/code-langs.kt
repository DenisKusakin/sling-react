package com.cathcart93.sling.components.models.spectacle.impl.builder

/**
 * @author Denis_Kusakin. 6/15/2018.
 */
sealed class CodeLang

object JavaScript : CodeLang()
object ReactJSX : CodeLang()
object JSON : CodeLang()
//TODO: currently not supported
//object Kotlin : CodeLang()

fun CodeLang.toPrismValue(): String {
    return when(this){
        is JavaScript -> "javascript"
        is ReactJSX -> "jsx"
        is JSON -> "json"
//        is Kotlin -> "kotlin"
    }
}