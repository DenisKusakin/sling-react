import ReactDOM from 'react-dom'
import React from 'react'
import Spectacle from './../SpectacleSling'

document.addEventListener("DOMContentLoaded", () => {
    let app = window.__DATA;
    ReactDOM.render(<Spectacle content={app} />, document.getElementById('app'))
});

