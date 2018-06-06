import ReactDOM from 'react-dom'
import React from 'react'
import SpectacleSlingAuthor from './../SpectacleSlingAuthor'

document.addEventListener("DOMContentLoaded", () => {
    let app = window.__DATA;
    ReactDOM.render(<SpectacleSlingAuthor content={app} />, document.getElementById('app'))
});

