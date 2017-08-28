import ReactDOM from 'react-dom'
import React from 'react'
import App from './../components/application.js'

document.addEventListener("DOMContentLoaded", () => {
    let app = window.__INITIAL_STATE['app'];
    ReactDOM.render(<App tree={app} />, document.getElementById('app'))
});

