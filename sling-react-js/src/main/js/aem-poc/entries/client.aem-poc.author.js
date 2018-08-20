import React from 'react'
import AemAuthorComponent from './../components/aem-author-component'
import ReactDOM from 'react-dom'

const AUTHOR_API = {};

AUTHOR_API.afterEditComponent = (component) => {
    console.log("After Edit!");
    const elem = component.dom.find("[data-author-component]")[0];
    const {dataset: {config}} = elem;
    ReactDOM.render(<AemAuthorComponent config={JSON.parse(config)}/>, elem)
};

AUTHOR_API.afterInsertComponent = AUTHOR_API.afterEditComponent;
top.AUTHOR_API = AUTHOR_API;

document.addEventListener("DOMContentLoaded", () => {
    [...document.querySelectorAll('[data-author-component]')].forEach((elem) => {
        const {dataset: {config}} = elem;
        console.log("Hello World!", elem, config);
        ReactDOM.render(<AemAuthorComponent config={JSON.parse(config)}/>, elem)
    });
});