import React from "react"
import Exports from "./export.js"
import ReactDOMServer from 'react-dom/server'
import createReactElement from './create-react-element.js'

const render = (props) => {
    return ReactDOMServer.renderToStaticMarkup(createReactElement(props, true))
}

(global || window).renderElement = render;