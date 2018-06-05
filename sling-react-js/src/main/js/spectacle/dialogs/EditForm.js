import React from 'react'
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import MaterialDialogForm from './../../framework/dialogs/MaterialDialogForm';
import MobxForm from "../../framework/dialogs/form/MobxForm";
import {updateResource} from "../../framework/api";
import {AuthorContext} from './context'

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

class EditForm extends React.Component {
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

    render() {

        return <AuthorContext.Consumer>
            {
                ({isEditMode, updateFromServer}) => {
                    return <div>
                        {this.renderEditDialog(updateFromServer)}
                        {
                            this.props.children({
                                isEditMode, updateFromServer, showDialog: () => {
                                    this.setState({isOpen: true})
                                }
                            })
                        }
                    </div>
                }
            }
        </AuthorContext.Consumer>
    }
}

export default EditForm;