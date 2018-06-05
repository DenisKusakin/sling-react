import React from 'react'
import dynamic from 'next/dynamic'
import SpectacleAuthorRoot from './SpectaceAuthorRoot'
import EditDialog from './dialogs/EditDialog'
import TreeContainer from './../framework/TreeContainer'
import EditModeToggler from './dialogs/EditModeToggler'
import SlidePropertiesButton from './dialogs/SlidePropertiesButton'
import SystemButtonsContainer from './dialogs/SystemButtonsContainer'
import AddSlideButton from './dialogs/AddSlideButton'
import DeleteSlideButton from './dialogs/DeleteSlideButton'

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
            EditModeToggler,
            SlidePropertiesButton,
            SystemButtonsContainer,
            AddSlideButton,
            DeleteSlideButton
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