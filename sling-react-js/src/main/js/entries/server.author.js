import React from "react"
import ReactDOMServer from 'react-dom/server'
import AppAuthor from './../framework/AuthorApp'
import components from './../components'
import dialogs from './../components/dialog'

const render = (props) => {
    return ReactDOMServer.renderToStaticMarkup(<AppAuthor content={props} dialogs={dialogs} components={components}/>)
};

(global || window).renderElement = render;

module.exports = render;