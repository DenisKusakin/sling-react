import ReactDOM from 'react-dom'
import React from 'react'
import createReactElement from './create-react-element.js'

document.addEventListener("DOMContentLoaded", () => {
    ReactDOM.render(createReactElement(window.__INITIAL_STATE), document.getElementById('app'))
});