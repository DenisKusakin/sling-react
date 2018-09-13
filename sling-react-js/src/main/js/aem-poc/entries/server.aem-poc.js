import React from 'react'
import ReactDOMServer from 'react-dom/server';
import AemPublishRootComponent from '../components/_core/aem-publish-component'

const render = (conf) => {
    return ReactDOMServer.renderToString(<AemPublishRootComponent config={conf}/>);
};

(global).renderReactElement = render;
renderReactElement = render;
module.exports = render;