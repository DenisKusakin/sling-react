// Import React
import React from "react";

// Import Spectacle Core tags
import {
  BlockQuote,
  Cite,
  Deck,
  Heading,
  ListItem,
  List,
  Quote,
  Slide,
  Text
} from "spectacle";

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

class TestComponent extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            count: 0
        }
    }

    render() {
        return <div>
            <h2>{this.state.count}</h2>
            <h2 onClick={() => this.setState(prevState => ({count: prevState.count + 1}))}>+</h2>
            <h2 onClick={() => this.setState(prevState => ({count: prevState.count - 1}))}>-</h2>
        </div>
    }
}

export default () => (
                           <Deck transition={["zoom", "slide"]} transitionDuration={250} theme={theme}>
                             <Slide transition={["zoom"]} bgColor="primary">
                               <Heading size={1} fit caps lineHeight={1} textColor="secondary">
                                 Spectacle Boilerplate
                               </Heading>
                               <Text margin="10px 0 0" textColor="tertiary" size={1} fit bold>
                                 open the presentation/index.js file to get started
                               </Text>
                             </Slide>
                             <Slide transition={["fade"]} bgColor="tertiary">
                               <div className="testclass" style={{outline: '2px dotted #2604B1;'}}>
                                 <Heading size={6} textColor="primary" caps style={{outline: '2px dotted #2604B1;'}}>Typography</Heading>
                               </div>
                               <Heading size={1} textColor="secondary">Heading 1</Heading>
                               <Heading size={2} textColor="secondary">Heading 2</Heading>
                               <Heading size={3} textColor="secondary">Heading 3</Heading>
                               <Heading size={4} textColor="secondary">Heading 4</Heading>
                               <Heading size={5} textColor="secondary">Heading 5</Heading>
                               <Text size={6} textColor="secondary">Standard text</Text>
                             </Slide>
                             <Slide transition={["fade"]} bgColor="primary" textColor="tertiary">
                               <Heading size={6} textColor="secondary" caps>Standard List</Heading>
                               <List>
                                 <ListItem>Item 1</ListItem>
                                 <ListItem>Item 2</ListItem>
                                 <ListItem>Item 3</ListItem>
                                 <ListItem>Item 4</ListItem>
                               </List>
                             </Slide>
                             <Slide transition={["fade"]} bgColor="secondary" textColor="primary">
                               <BlockQuote>
                                 <Quote>Example Quote</Quote>
                                 <Cite>Author</Cite>
                               </BlockQuote>
                             </Slide>
                             <Slide transition={["fade"]} bgColor="secondary" textColor="primary" id="test">
                               <BlockQuote>
                                 <Quote>New Slide</Quote>
                                 <Cite>Denis</Cite>
                                 <TestComponent/>
                               </BlockQuote>
                             </Slide>
                           </Deck>
                         )