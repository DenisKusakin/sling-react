import React from 'react';
import { ContextMenuProvider } from 'react-contexify';
import EditIcon from 'react-material-icons/icons/editor/mode-edit';
import IconButton from 'material-ui/IconButton'
import { MuiThemeProvider } from 'material-ui/styles';
import darkTheme from 'material-ui/styles/baseThemes/darkBaseTheme';
import './border.css';
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';

const styles = {
    smallIcon: {
      width: 15,
      height: 15,
    }
  };

const editableComponent = Component => class EditableComponent extends React.Component {
    state = {
        open: false,
    };

    handleOpen = () => {
        this.setState({open: true});
    };

    handleClose = () => {
        this.setState({open: false});
    };

    constructor(props){
        super(props)
    }

    render(){
        let {__propTypes, ...props} = this.props

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
            <div style={{float: 'left'}}>
                <MuiThemeProvider theme={darkTheme}>
                    <div>
                        <IconButton onClick={this.handleOpen} iconStyle={styles.smallIcon}>
                            <EditIcon/>
                        </IconButton>
                        <Dialog
                            title="Dialog With Actions"
                            actions={actions}
                            modal={false}
                            open={this.state.open}
                            onRequestClose={this.handleClose}>
                            The actions in this window were passed in as an array of React objects.
                        </Dialog>
                    </div>
                </MuiThemeProvider>
            </div>
            <Component {...props} isEditMode={true}/>
        </div>

    }
}

export default editableComponent