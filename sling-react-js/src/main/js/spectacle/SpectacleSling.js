import React from 'react'
import Deck from './deck'
import Heading from './heading'
import Text from './text'
import Slide from './slide'
import Container from './../components/container'
import ContainerDialog from './../components/container/dialog'
import SpectacleAuthorRoot from './SpectaceAuthorRoot'
import standartDialog from "../framework/dialogs/standartDialog";

const components = {
    Deck,
    Heading,
    Text,
    Slide,
    Container
};

const dialogs = {
    ...components,
    Heading: standartDialog(Heading),
    Text: standartDialog(Text),
    Container: ContainerDialog
};

const Spectacle = props => (<SpectacleAuthorRoot
        components={components}
        dialogs={dialogs}
        content={props.content}/>
);

export default Spectacle