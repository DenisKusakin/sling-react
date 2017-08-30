import React from 'react'
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import EditIcon from 'react-material-icons/icons/editor/mode-edit';
import IconButton from 'material-ui/IconButton'
import { MuiThemeProvider } from 'material-ui/styles';
import darkTheme from 'material-ui/styles/baseThemes/darkBaseTheme';
import './../../authoring-components/border.css'
import Title from './title.js'

const styles = {
    smallIcon: {
        width: 15,
        height: 15,
    }
};


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

        return <div className="edit-wrapper">
            <div style={{ float: 'left' }}>
                <MuiThemeProvider theme={darkTheme}>
                    <div>
                        <IconButton onClick={this.handleOpen} iconStyle={styles.smallIcon}>
                            <EditIcon />
                        </IconButton>
                        <Dialog
                            title="Title Component Dialog"
                            actions={actions}
                            modal={false}
                            open={this.state.open}
                            onRequestClose={this.handleClose}>
                            The actions in this window were passed in as an array of React objects.
                        <TextField
                                hintText="Component's text" />
                        </Dialog>
                    </div>
                </MuiThemeProvider>
            </div>
            <Title {...this.props} />
        </div>
    }
}

export default TitleDialog
