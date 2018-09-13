import React from 'react'
import AemPublishRootComponent from '../components/_core/aem-publish-component'
import ReactDOM from 'react-dom'

document.addEventListener("DOMContentLoaded", () => {
    const elem = document.querySelector('#react-root');
    console.log("Render Publish Root", elem);

    ReactDOM.hydrate(<AemPublishRootComponent config={window.__INITIAL_STATE}/>, elem);
});