//package com.cathcart93.sling.componentsv2.utils
//
//import java.{lang, util}
//
//import org.apache.sling.api.resource.{Resource, ResourceMetadata, ResourceResolver, ValueMap}
//import org.mockito.Mockito
//import org.mockito.Mockito._
//
////case class PropName(value: String)
//
//sealed trait PropValue
//
//case class SimplePropValue[T >: AnyVal](value: T) extends PropValue
//
//case class ChildResourcePropValue(name: String, resourceBuilder: ResourceBuilder) extends PropValue
//
//trait ImplicitPropName {
//
//  implicit class StringToPropValue(value: String) {
//    SimplePropValue(value)
//  }
//
//  implicit class BooleanToPropValue(value: Boolean) {
//    SimplePropValue(value)
//  }
//
//  implicit class ResourceBuilderToResource(resourceBuilder: ResourceBuilder) {
//
//  }
//
//}
//
//class ResourceMock(props: Map[String, AnyRef], children: Map[String, ResourceMock] = Map()) extends Resource {
//  override def getName: String = ???
//
//  override def isResourceType(resourceType: String): Boolean = ???
//
//  override def hasChildren: Boolean = ???
//
//  override def getResourceSuperType: String = ???
//
//  override def getResourceMetadata: ResourceMetadata = ???
//
//  override def getResourceType: String = ???
//
//  override def getPath: String = ???
//
//  override def getParent: Resource = ???
//
//  override def getChild(relPath: String): Resource = ???
//
//  override def getResourceResolver: ResourceResolver = ???
//
//  override def getValueMap: ValueMap = {
//    new ValueMapMock(props)
//  }
//
//  override def getChildren: lang.Iterable[Resource] = ???
//
//  override def listChildren(): util.Iterator[Resource] = ???
//
//  override def adaptTo[AdapterType](`type`: Class[AdapterType]): AdapterType = ???
//}
//
//class ValueMapMock(map: Map[String, AnyRef]) extends ValueMap {
//  override def get[T](name: String, `type`: Class[T]): T = map.get(name).asInstanceOf[T]
//
//  override def get[T](name: String, defaultValue: T): T = if (map.containsKey(name)) get(name, classOf[T]) else defaultValue
//
//  override def values() = map.map(x => x._2).toList
//
//  override def containsValue(value: scala.Any): Boolean = ???
//
//  override def remove(key: scala.Any): AnyRef = ???
//
//  override def put(key: String, value: scala.Any): AnyRef = ???
//
//  override def putAll(m: util.Map[_ <: String, _ <: AnyRef]): Unit = ???
//
//  override def get(key: scala.Any): AnyRef = ???
//
//  override def keySet(): Set[String] = map.keySet
//
//  override def entrySet() = ???
//
//  override def containsKey(key: scala.Any): Boolean = map.contains(key)
//
//  override def clear(): Unit = ???
//
//  override def isEmpty: Boolean = map.isEmpty
//
//  override def size(): Int = ???
//}
//
//class ResourceBuilder(elems: (String, PropValue)*) {
//
//}
