import React from 'react';
import AemAuthorComponent from './../components/_core/aem-author-component';
import ReactDOM from 'react-dom';
import Components from '../components/_componentsMap/clientComponentsMap';
import TreeContainer from '../components/_core/TreeContainer';

const AUTHOR_API = {
    afterEditComponent: (component) => {
        console.log("After Edit!");
        const elem = component.dom.find("[data-author-component]")[0];
        if(elem){
            const {dataset: {config}} = elem;
            ReactDOM.render(<TreeContainer components={Components} tree={JSON.parse(config)}/>, elem);
        }
    }
};

AUTHOR_API.afterInsertComponent = AUTHOR_API.afterEditComponent;
top.AUTHOR_API = AUTHOR_API;

document.addEventListener("DOMContentLoaded", () => {
    const elem = document.querySelector('#react-root');
    const {dataset: {config, confUrl}} = elem;
    console.log("Hello World!", elem, config);
    ReactDOM.render(<AemAuthorComponent previewUrl={confUrl} previewConfig={null} editConfig={JSON.parse(config)}/>, elem)
});