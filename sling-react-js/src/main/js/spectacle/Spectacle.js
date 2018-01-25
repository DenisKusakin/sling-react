import React from 'react'
import TreeContainer from './../framework/TreeContainer'
import dynamic from 'next/dynamic'
import wrapContent from './wrapContent'

const Spectacle = dynamic({
    modules: props => {
        const components = {
            Deck: import('./deck'),
            Heading: import('./heading'),
            Text: import('./text'),
            Slide: import('./slide'),
            Container: import('./../components/container/dialog')
        };

        return components;

    },
    ssr: false,
    render: ({content, isEditMode}, components) => {
        return (<TreeContainer components={components} tree={wrapContent(content)} typeFieldName="renderer"/>)
    }
});

export default Spectacle