import ReactDOM from 'react-dom'
import React from 'react'
import AppAuthor from './../framework/AuthorApp'
import components from './../components'
import dialogs from './../components/dialog'

document.addEventListener("DOMContentLoaded", () => {
    let app = window.__DATA;
    console.log("!", app);
    ReactDOM.render(<AppAuthor content={app} components={components} dialogs={dialogs}/>, document.getElementById('app'))
});

