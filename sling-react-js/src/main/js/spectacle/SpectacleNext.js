import React from 'react'
import dynamic from 'next/dynamic'
import SpectacleAuthorRoot from './SpectaceAuthorRoot'
import SimpleDialog from '../framework/dialogs/SimpleDialog'
import ContainerDialog from './../components/container/dialog';

const Spectacle = dynamic({
    modules: props => {
        return {
            Deck: import('./deck'),
            Heading: import('./heading'),
            Text: import('./text'),
            Slide: import('./slide'),
            Container: import('./../components/container')
        }

    },
    ssr: false,
    render: (props, components) => {
        return <SpectacleAuthorRoot
            content={props.content}
            components={{
                ...components,
                "dialogs/SimpleDialog": SimpleDialog,
                "dialogs/Container": ContainerDialog
            }}
        />
    }
});

export default Spectacle