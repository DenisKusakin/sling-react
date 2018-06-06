import ReactDOM from 'react-dom'
import React from 'react'
import SpectacleSling from './../SpectacleSling'

document.addEventListener("DOMContentLoaded", () => {
    let app = window.__DATA;
    ReactDOM.render(<SpectacleSling content={app} />, document.getElementById('app'))
});

