import React from 'react'
import TreeContainer from './../framework/TreeContainer'
import dynamic from 'next/dynamic'

const Spectacle = dynamic({
    modules: props => {
        const components = {
            Deck: import('./deck'),
            Heading: import('./heading'),
            Text: import('./text'),
            Slide: import('./slide'),
        }
        return components;

    },
    ssr: false,
    render: ({content}, components) => {
        console.log("content", content)
        return (<TreeContainer components={components} tree={content} typeFieldName="renderer"/>)
    }
})

export default Spectacle