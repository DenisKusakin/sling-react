package com.cathcart93.sling.components.models

import org.apache.sling.testing.mock.sling.junit.SlingContext
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

open class DefaultNextJSEndpointResolverTest {
    @get: Rule
    val context = SlingContext()

    @Test
    fun test() {
        context.create().resource("/_next", "slingUrl", "http://localhost:8080")
        context.create().resource("/_next/sling-react")
        context.create().resource("/_next/sling-react/spectacle",
                "url", "http://localhost:3000")

        val page = context.create()
                .resource("/to/to/page", "sling:resourceType", "sling-react/spectacle")
        val target = page.adaptTo(DefaultNextJSEndpointResolver::class.java)
        assertNotNull(target, "Failed to adapt")
        val expectedDataUrl = "http://localhost:8080/to/to/page.json"
        val expectedAssetPath = "/_next/sling-react/spectacle.html"
        val expectedUrl = """
            http://localhost:3000/?dataUrl=$expectedDataUrl&assetPrefix=$expectedAssetPath
        """.trimIndent()
        assertEquals(expectedUrl, target!!.renderUrl())
    }

    @Test
    fun test2() {
        context.create().resource("/_next", "slingUrl", "http://localhost:8080")
        context.create().resource("/_next/sling-react", "url", "http://localhost:3000")

        val page = context.create()
                .resource("/to/to/page", "sling:resourceType", "sling-react/spectacle")
        val target = page.adaptTo(DefaultNextJSEndpointResolver::class.java)
        assertNotNull(target, "Failed to adapt")
        val expectedDataUrl = "http://localhost:8080/to/to/page.json"
        val expectedAssetPath = "/_next/sling-react.html"
        val expectedUrl = """
            http://localhost:3000/spectacle?dataUrl=$expectedDataUrl&assetPrefix=$expectedAssetPath
        """.trimIndent()
        assertEquals(expectedUrl, target!!.renderUrl())
    }

    @Test
    fun test3() {
        context.create().resource("/_next", "slingUrl", "http://localhost:8080", "url", "http://localhost:3000")
        val page = context.create()
                .resource("/to/to/page", "sling:resourceType", "sling-react/spectacle")
        val target = page.adaptTo(DefaultNextJSEndpointResolver::class.java)
        assertNotNull(target, "Failed to adapt")
        val expectedDataUrl = "http://localhost:8080/to/to/page.json"
        val expectedAssetPath = "/_next.html"
        val expectedUrl = """
            http://localhost:3000/sling-react/spectacle?dataUrl=$expectedDataUrl&assetPrefix=$expectedAssetPath
        """.trimIndent()
        assertEquals(expectedUrl, target!!.renderUrl())
    }
}