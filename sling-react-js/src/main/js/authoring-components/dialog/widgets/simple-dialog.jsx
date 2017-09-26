import React from 'react'
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import EditIcon from 'react-material-icons/icons/editor/mode-edit';
import IconButton from 'material-ui/IconButton'
import { MuiThemeProvider } from 'material-ui/styles';
import darkTheme from 'material-ui/styles/baseThemes/darkBaseTheme';
import './border.css'
import { toJS } from "mobx";
import persist from "./../persist.js"
import SimpleFieldSet from './simple-field-set.jsx'
import FieldSetModel from './../model/field-set.js'

const styles = {
    smallIcon: {
        width: 15,
        height: 15,
    }
};

class SimpleDialog extends React.Component {

    constructor(props) {
        super(props)
        let fields = props.fields.map(({name, value}) => ({name, value}))
        this.fieldSetModel = new FieldSetModel(fields)
        this.state = {
            open: false
        }
    }

    handleOpen = () => {
        this.setState({ open: true });
    };

    handleClose = () => {
        this.setState({ open: false });
    };

    handleSubmit = () => {
        let promise = persist(this.fieldSetModel.values)
        promise.then(data => {
            this.handleClose()
        }, error => {
            this.setState({error})
        })
    };

    render() {
        let actions = [
            <FlatButton
                label="Cancel"
                primary={true}
                onClick={this.handleClose}
            />,
            <FlatButton
                label="Submit"
                primary={true}
                keyboardFocused={true}
                onClick={this.handleSubmit}
            />
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
                            <div>
                                {this.state.error && <p style={{color: 'red'}}>{this.state.error}</p>}
                                <SimpleFieldSet model={this.fieldSetModel}/>
                            </div>
                        </Dialog>
                    </div>
                </MuiThemeProvider>
            </div>
            {this.props.original}
        </div>
    }
}

export default SimpleDialog
