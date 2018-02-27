import React from 'react'
import dynamic from 'next/dynamic'
import ContainerDialog from './../components/container/dialog';

const Spectacle = dynamic({
    modules: props => {
        return {
            Deck: import('./deck'),
            Container: import('./../components/container'),
            Spectacle: import('spectacle'),
            "dialogs/SimpleDialog": import('../framework/dialogs/SimpleDialog'),
            SpectacleAuthorRoot: import('./SpectaceAuthorRoot')
        }
    },
    ssr: false,
    render: (props, {SpectacleAuthorRoot, Spectacle, ...components}) => {
        return <SpectacleAuthorRoot
            content={props.content}
            components={{
                ...Spectacle,
                ...components,
                "dialogs/Container": ContainerDialog
            }}
        />
    }
});

export default Spectacle