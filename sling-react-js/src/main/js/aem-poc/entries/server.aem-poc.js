import React from 'react'
import ReactDOMServer from 'react-dom/server';
import AemPublishRootComponent from './../components/aem-publish-component'
import ReactDOM from 'react-dom'

global.renderReactElement = (conf) => {
    return ReactDOMServer.renderToString(<AemPublishRootComponent config={conf}/>);
}