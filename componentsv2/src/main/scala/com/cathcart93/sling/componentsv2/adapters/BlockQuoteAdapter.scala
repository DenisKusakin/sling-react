package com.cathcart93.sling.componentsv2.adapters

import com.cathcart93.sling.componentsv2.ResourceAdapter
import com.cathcart93.sling.componentsv2.models.{BlockQuote, HeadingModel}
import org.apache.sling.api.resource.Resource

trait BlockQuoteAdapter extends ResourceAdapter[BlockQuote] {
  override def adapt: Resource => BlockQuote = (resource: Resource) => {
    val valueMap = resource.getValueMap
    val quote = valueMap.get("quote", classOf[String])
    val cite = valueMap.get("cite", classOf[String])

    BlockQuote(quote = quote, cite = cite)
  }
}
