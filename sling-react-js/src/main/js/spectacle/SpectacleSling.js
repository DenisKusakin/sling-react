import React from 'react'
import Deck from './deck'
import Heading from './heading'
import Text from './text'
import Slide from './slide'
import Container from './../components/container'
import ContainerDialog from './../components/container/dialog'
import SpectacleAuthorRoot from './SpectaceAuthorRoot'
import standartDialog from "../framework/dialogs/standartDialog";
import SimpleDialog from '../framework/dialogs/SimpleDialog'

const components = {
    Deck,
    Heading,
    Text,
    Slide,
    Container,
    "dialogs/Container": ContainerDialog,
    "dialogs/SimpleDialog": SimpleDialog
};

const Spectacle = props => (<SpectacleAuthorRoot
        components={components}
        content={props.content}/>
);

export default Spectacle