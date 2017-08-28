import React from "react"
import ReactDOMServer from 'react-dom/server'
import App from './../components/application.js'

const render = (props) => {
    return ReactDOMServer.renderToStaticMarkup(<App tree={props} />)
}

(global || window).renderElement = render;