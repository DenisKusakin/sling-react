import React from 'react'
import Deck from './deck'
import Heading from './heading'
import Text from './text'
import Slide from './slide'
import Container from './../components/container'
import ContainerDialog from './../components/container/dialog'
import SpectacleAuthorRoot from './SpectaceAuthorRoot'
import SimpleDialog from '../framework/dialogs/SimpleDialog'
import {BlockQuote, Quote, Cite, Code, CodePane, Link, Image} from "spectacle";

const components = {
    Code,
    CodePane,
    Deck,
    Heading,
    Text,
    Slide,
    Link,
    BlockQuote,
    Quote,
    Cite,
    Image,
    Container,
    "dialogs/Container": ContainerDialog,
    "dialogs/SimpleDialog": SimpleDialog
};

const Spectacle = props => (<SpectacleAuthorRoot
        components={components}
        content={props.content}/>
);

export default Spectacle