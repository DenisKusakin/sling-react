// Import React
import React from "react";
import dynamic from 'next/dynamic'
// Import Spectacle Core tags
// import {
//   BlockQuote,
//   Cite,
//   Deck,
//   Heading,
//   ListItem,
//   List,
//   Quote,
//   Slide,
//   Text
// } from "spectacle";

// Import theme
import createTheme from "spectacle/lib/themes/default";

// Require CSS
//require("normalize.css");

const theme = createTheme({
  primary: "white",
  secondary: "#1F2022",
  tertiary: "#03A9FC",
  quarternary: "#CECECE"
}, {
  primary: "Montserrat",
  secondary: "Helvetica"
});

const App = dynamic({
    modules: props => {
        const components = {
            Deck: import('./../../spectacle/deck'),
            Heading: import('./../../spectacle/heading'),
            Text: import('./../../spectacle/text'),
            Slide: import('./../../spectacle/slide'),
            Container: import('./../../components/container/dialog')
        }
        return components;

    },
    ssr: false,
    render: (props, { Deck, Slide, Heading, Text, Container }) => (<Deck transition={["zoom", "slide"]} transitionDuration={250}>
        <Slide transition={["zoom"]} bgColor="primary" >
            <Container components={[
                <Heading size={1} fit caps lineHeight={1} textColor="primary">
                    Spectacle Boilerplate
                </Heading>,
                <Text textColor="tertiary" size={1} fit bold>
                    open the presentation/index.js file to get started
                </Text>
            ]}/>
        </Slide>
    </Deck>)
})

export default App