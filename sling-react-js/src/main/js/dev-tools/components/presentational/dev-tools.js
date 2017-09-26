import React from 'react'
import UpdateButton from './../containers/update-button-container.js'
import MockPages from './../containers/mock-pages-container.js'
import EditModeSwitcher from './../containers/edit-mode-switcher-container.js'
//import MockPages from './../presentational/mock-pages.js'
import Sticky from 'react-stickynode'
//import './dev-tools.css'
import { MuiThemeProvider } from 'material-ui/styles';
import darkTheme from 'material-ui/styles/baseThemes/darkBaseTheme';

const DevTools = () => <MuiThemeProvider theme={darkTheme}>
      {/* <Sticky enabled={true} top={25} bottomBoundary={1200} innerZ={10000}> */}
        <div className="sicky-dev-tools" style={{display: "flex"}}>
            {/* <UpdateButton/> */}
            <MockPages/>
            <EditModeSwitcher/>
        </div>
      {/* </Sticky> */}
    </MuiThemeProvider>

export default DevTools