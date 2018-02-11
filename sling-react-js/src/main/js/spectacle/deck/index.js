import React from 'react'
import {Deck} from "spectacle";
import createTheme from "spectacle/lib/themes/default";

export default props => {
    const theme = createTheme(props.colors, props.fonts);
    return <Deck {...props} theme={theme}/>;
}