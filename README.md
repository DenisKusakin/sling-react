# React component for AEM

The goal of this project is to develop approach for building [React](https://reactjs.org/) components for Adobe Experience Manager.
The core AEM authoring functionality should be saved.
Server-side rendering should be supported.

## Motivation

Adobe Experience Manager(AEM) is CMS build on top of Apache Sling, it has its own understanding about what component is, their approach have a lot of advantages, but it also have some disadvantages.
Developer experience is one AEM disadvantages, especially for frontend developers, they have to deal with a lot of AEM specific stuff like clientlibraries, HTL, it is also not a simple task to use modern js libraries, frameworks, tools.
In many projects frontend developers still have to have their own developer AEM server which brings a lot of problems into development process.
There are some projects which tries to bring modern frontend libraries into AEM like [aem-react](https://github.com/sinnerschrader/aem-react) which allows you to use React along with other components which
is really good for existing projects since they could involve React components into their code but it is still too AEM specific.

Backend developers have their own disadvantages in AEM component model, among them: developer have to deal with HTML frequently, it is hard to reuse components.

This project use another approach for bringing modern frontend into AEM. The approach is rather trivial, actually.
The idea is to separate frontend and backend development as much as possible. The goal is to provide frontend developers with following features:
* Should not care about AEM, they even should not know that they develop for AEM(in most cases).
* Own lightweight development environment.
* Ability to use any desired tools.

Backend developers should get the following features:
* No need to think about HTML/CSS/JS at all.
* Another notion of component comparing to AEM components, this approach should help to build reusable components.

## Implementation Details

Two different views should be supported for AEM:
* editor view(used on author environment for /editor interface)
* publish view(used on author and publisher, end-user view)

In most cases component looks the same for both authoring and publisher views, but for some components it is not true.
The typical example is image gallery component, if we consider classic AEM approach, the component should have parsys and author should be able
to insert new slides to this parsys and then configure each item. From end-user perspective the component looks very different and have nothing
common with author view.

The idea is to create two independent interfaces for both views for every component. The editor interface uses AEM components(scripts under /apps) as usual
while publish mode does not use scripts under /apps at all. As was mentioned above, most components looks the same for both views and it is obvious
that code should NOT be duplicated to support editor/preview modes.

HTL Script for simplest AEM component will look like this:

```
<div data-sly-use.model="com.project.components.models.ComponentModel"
     data-author-component="true"
     data-config='${model.toJson}'>

</div>
```

So it only renders data needed to render React component on client-side.
HTL script for non-trivial component might look like this

```
<div data-sly-resource="${'slides' @ resourceType='wcm/foundation/components/parsys'}"></div>
```

Script for item rendering:

```
<div>
    <h2>Image gallery Item (Authoring view)</h2>
    <img src="${properties['original']}"/>
</div>
```

## Development

### AEM

From the project root run
```
mvn clean install -P autoInstallBundle,autoInstallPackage
```

### Frontend development environment
Go to sling-react-js/src/main/js
```
npm run dev-next
```

## How to Install

* build from sources

    ```
    mvn clean install -P autoInstallBundle,autoInstallPackage
    ```

## Built With
* [AEM](https://www.adobe.com/marketing/experience-manager.html)
* [ReactJS](https://reactjs.org/)
* [Kotlin](https://kotlinlang.org/) - kotlin is used mostly because of its ability for building DSLs
* [NextJS](https://github.com/zeit/next.js/) - used as developer environment for frontend developers