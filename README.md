# React component for AEM

The goal of this project is to develop approach for building [React](https://reactjs.org/) components for Adobe Experience Manager.
The core AEM authoring functionality should be saved.

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
* using package manager

    JCR package could be loaded from releases tab

## Built With
* [AEM] (https://www.adobe.com/marketing/experience-manager.html)
* [ReactJS](https://reactjs.org/)
* [Kotlin](https://kotlinlang.org/) - kotlin is used mostly because of its ability for building DSLs
* [NextJS](https://github.com/zeit/next.js/) - used as developer environment for frontend developers