import React from 'react'
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import AddIcon from 'react-material-icons/icons/content/add-circle-outline';
import IconButton from 'material-ui/IconButton'
import { MuiThemeProvider } from 'material-ui/styles';
import darkTheme from 'material-ui/styles/baseThemes/darkBaseTheme';
import Container from './container.js'
import './border.css'

const ContainerEdit = ({components}) => {
    return <div>
        {components.map((x, i) => <div key={i} className="edit-wrapper">{x}</div>)}
    </div>
}

class TitleDialog extends React.Component {

    state = {
        open: false,
    };

    handleOpen = () => {
        this.setState({ open: true });
    };

    handleClose = () => {
        this.setState({ open: false });
    };

    render() {
        const actions = [
            <FlatButton
                label="Cancel"
                primary={true}
                onClick={this.handleClose}
            />,
            <FlatButton
                label="Submit"
                primary={true}
                keyboardFocused={true}
                onClick={this.handleClose}
            />,
        ];

        return <div>
            <div className="container-edit-mode">
                <ContainerEdit {...this.props}/>
                <MuiThemeProvider theme={darkTheme}>
                    <div>
                        <div style={{"textAlign": "center"}}>
                            <IconButton onClick={this.handleOpen}>
                                <AddIcon />
                            </IconButton>
                        </div>
                        <Dialog
                            title="Container Dialog"
                            actions={actions}
                            modal={false}
                            open={this.state.open}
                            onRequestClose={this.handleClose}>
                            The list of all availiable components should be here
                        </Dialog>
                    </div>
                </MuiThemeProvider>
            </div>
        </div>
    }
}

export default TitleDialog
