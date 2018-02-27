import React from 'react'
import Deck from './deck'
import Container from './../components/container'
import ContainerDialog from './../components/container/dialog'
import SpectacleAuthorRoot from './SpectaceAuthorRoot'
import SimpleDialog from '../framework/dialogs/SimpleDialog'
import {Heading, Text, Slide, BlockQuote, Quote, Cite, Code, CodePane} from "spectacle";

const components = {
    Code,
    CodePane,
    Deck,
    Heading,
    Text,
    Slide,
    BlockQuote,
    Quote,
    Cite,
    Container,
    "dialogs/Container": ContainerDialog,
    "dialogs/SimpleDialog": SimpleDialog
};

const Spectacle = props => (<SpectacleAuthorRoot
        components={components}
        content={props.content}/>
);

export default Spectacle