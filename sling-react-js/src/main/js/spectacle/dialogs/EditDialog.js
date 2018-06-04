import React from 'react'
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import EditIcon from 'material-ui/svg-icons/editor/mode-edit';
import DeleteIcon from 'material-ui/svg-icons/action/delete';
import IconButton from 'material-ui/IconButton'
import {MuiThemeProvider} from 'material-ui/styles';
import {createResource, deleteResource} from './../../framework/api';
import MaterialDialogForm from './../../framework/dialogs/MaterialDialogForm';
import MobxForm from "../../framework/dialogs/form/MobxForm";
import {updateResource} from "../../framework/api";
import {AuthorContext} from './context'

const styles = {
    smallIcon: {
        width: 12,
        height: 12,
    }
};

const bindingType = (type) => {
    if (!type || type === 'text') {
        return "TextField"
    }
    if (type === "textarea") {
        return "MaterialTextArea"
    }
    if (type === "checkbox") {
        return "MaterialCheckbox"
    }
    if (type === 'select') {
        return "MaterialSelect"
    }
    if (type === 'color') {
        return "ColorPicker"
    }
};

class EditDialog extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpen: false
        };

        const fields = props.editDialog.map(x => ({
            ...x,
            label: x.title,
            extra: x.options,
            bindings: bindingType(x.type)
        }));

        this.renderButtons = this.renderButtons.bind(this);
        this.renderEditDialog = this.renderEditDialog.bind(this);
        this.onSubmitClick = this.onSubmitClick.bind(this);

        this.form = new MobxForm({fields});
    }

    onSubmitClick(updateFromServer) {
        return e => this.form.onSubmit(e, {
            onSuccess: () => {
                console.info('The following props will be updated', this.form.values());
                if (!this.props.editUrl) {
                    console.error("Edit url is not defined")
                } else {
                    updateResource({url: this.props.editUrl, props: this.form.values()})
                        .then(() => {
                            console.info("Props successfully updated");
                            this.setState({isOpen: false});
                            updateFromServer();
                        }, (error) => {
                            console.error(error);
                            this.setState({error: "Error"});
                        })
                }
            }
        })
    }

    renderEditDialog(updateFromServer) {
        let actions = [
            <FlatButton
                label="Submit"
                primary={true}
                keyboardFocused={true}
                onClick={this.onSubmitClick(updateFromServer)}
            />,
            <FlatButton
                label="Cancel"
                primary={false}
                onClick={() => {
                    this.setState({isOpen: false})
                }}
            />
        ];

        return <Dialog
            title="Dialog"
            actions={actions}
            modal={false}
            open={this.state.isOpen}
            autoScrollBodyContent={true}
            onRequestClose={() => {
                this.setState({isOpen: false})
            }}>
            <div>
                {this.state.error && <p style={{color: 'red'}}>{this.state.error}</p>}
                <MaterialDialogForm form={this.form}/>
            </div>
        </Dialog>
    }

    renderButtons() {
        return <AuthorContext.Consumer>
            {
                ({updateFromServer}) => <div style={{display: 'flex', flexDirection: 'column'}}>
                    {
                        this.props.editUrl && <IconButton iconStyle={styles.smallIcon} onClick={() => {
                            this.setState({isOpen: true})
                        }}>
                            <EditIcon/>
                        </IconButton>
                    }
                    {this.renderEditDialog(updateFromServer)}
                    {this.props.deleteUrl && <IconButton iconStyle={styles.smallIcon} onClick={() => {
                        if (!this.props.deleteUrl) {
                            console.warn("Delete URL is not defined")
                        } else {
                            deleteResource({url: this.props.deleteUrl})
                                .then(() => {
                                    console.info("Resource successfully removed", this.props.deleteUrl);
                                    updateFromServer()
                                })
                                .catch(() => {
                                    console.error(`Remove resource failed, url: ${this.props.deleteUrl}`)
                                })
                        }
                    }}>
                        <DeleteIcon/>
                    </IconButton>}
                </div>
            }
        </AuthorContext.Consumer>
    }

    render() {

        return <AuthorContext.Consumer>
            {
                ({isEditMode}) => {
                    if (isEditMode) {
                        return <div>
                            <style jsx>
                                {`
                                    .container{
                                        display: flex;
                                        align-items: center;
                                    }
                                `}
                            </style>
                            <div className="container">
                                <div style={{flexGrow: 0, zIndex: 1}}>
                                    {this.renderButtons()}
                                </div>
                                <div style={{flexGrow: 1}}>
                                    {this.props.children}
                                </div>
                            </div>
                        </div>
                    }
                    return this.props.children
                }
            }
        </AuthorContext.Consumer>
    }
}

export default EditDialog;