import React from 'react';
import AemAuthorComponent from './../components/aem-author-component';
import ReactDOM from 'react-dom';
import axios from 'axios';

const AUTHOR_API = {
    getPreviewComponentRoot: () => {
        return document.querySelector("#react-root-preview");
    },
    getUpdatedPreviewComponent: () => {
        const url = AUTHOR_API.getPreviewComponentRoot().getAttribute('data-conf-url');
        return axios.get(url);
    },
    afterEditComponent: (component) => {
        console.log("After Edit!");
        const elem = component.dom.find("[data-author-component]")[0];
        const {dataset: {config}} = elem;
        ReactDOM.render(<AemAuthorComponent config={JSON.parse(config)} />, elem);
        AUTHOR_API.getUpdatedPreviewComponent()
            .then(response => ReactDOM.render(<AemAuthorComponent config={response.data} />, AUTHOR_API.getPreviewComponentRoot()))
    }
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