import React from 'react'
import Deck from './deck'
import Container from './dialogs/Container'
import SpectacleAuthorRoot from './SpectaceAuthorRoot'
import * as SpectacleComponents from "spectacle";
import EditDialog from './dialogs/EditDialog'
import TreeContainer from './../framework/TreeContainer'
import EditModeToggler from './dialogs/EditModeToggler'
import SlidePropertiesButton from './dialogs/SlidePropertiesButton'
import SystemButtonsContainer from './dialogs/SystemButtonsContainer'
import AddSlideButton from './dialogs/AddSlideButton'
import DeleteSlideButton from './dialogs/DeleteSlideButton'

const components = {
    ...SpectacleComponents,
    Deck,
    Container,
    EditDialog,
    EditModeToggler,
    SlidePropertiesButton,
    SystemButtonsContainer,
    AddSlideButton,
    DeleteSlideButton
};

components['SpectacleAuthorRoot'] = x => <SpectacleAuthorRoot
    {...x}
    renderTree={y => <TreeContainer components={components} tree={y}/>}/>;

const Spectacle = props => (<TreeContainer
        components={components}
        tree={props.content}/>
);

export default Spectacle