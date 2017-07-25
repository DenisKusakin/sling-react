import ReactDOM from 'react-dom'
import React from 'react'
import createReactElement from './create-react-element.js'

document.addEventListener("DOMContentLoaded", () => {
    let app = window.__INITIAL_STATE['app'];
    ReactDOM.render(createReactElement(app), document.getElementById('app'))
});