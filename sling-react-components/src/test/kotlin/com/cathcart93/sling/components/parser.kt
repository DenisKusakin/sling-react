package com.cathcart93.sling.components
import com.sun.xml.xsom.*
import com.sun.xml.xsom.parser.*
import org.junit.Test

val SCHEME = "html4.xsd"

open class Test {
    @Test
    fun test(){
        val parser = XSOMParser()
        parser.parse(this.javaClass.classLoader.getResourceAsStream(SCHEME))
        val schema = parser.result.getSchema("html-4")!!
        schema.attGroupDecls.values.forEach {
            System.out.println("! ${it.name}")
            it.attributeUses.forEach{x ->
                System.out.println(x.decl.name)
            }
        }
//        schema.elementDecls.values.forEach {
//            System.out.println("!" + it.name)
//        }
    }
}