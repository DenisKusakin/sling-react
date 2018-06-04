import React from 'react'
import dynamic from 'next/dynamic'
import SpectacleAuthorRoot from './SpectaceAuthorRoot'
import EditDialog from './dialogs/EditDialog'
import TreeContainer from './../framework/TreeContainer'
import EditModeToggler from './dialogs/EditModeToggler'

const Spectacle = dynamic({
    modules: props => {
        return {
            Deck: import('./deck'),
            Heading: import('./heading'),
            Text: import('./text'),
            Slide: import('./slide'),
            Container: import('./dialogs/Container'),
            Spectacle: import('spectacle')
        }
    },
    ssr: false,
    render: (props, {Spectacle, ...components}) => {
        const resultComponents = {
            ...Spectacle,
            ...components,
            EditDialog,
            EditModeToggler
        };
        resultComponents['SpectacleAuthorRoot'] = x => <SpectacleAuthorRoot
            {...x}
            renderTree={y => <TreeContainer components={resultComponents} tree={y}/>}/>;

        return <TreeContainer
            tree={props.content}
            components={resultComponents}
        />
    }
});

export default Spectacle