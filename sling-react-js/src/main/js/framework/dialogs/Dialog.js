import React from 'react'
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import ErrorBoundary from './ErrorBoundary';
import MobxForm from './form/MobxForm'
import {updateResource} from './../api'
import MaterialDialogForm from './MaterialDialogForm';

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
    if (type === 'code') {
        return 'AceCodeEditor'
    }
};

class SimpleDialogForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            error: null
        };

        this.onSubmitClick = this.onSubmitClick.bind(this);
    }

    onSubmitClick(form, url) {
        return e => form.onSubmit(e, {
            onSuccess: () => {

                console.info(form.values());
                return updateResource({url, props: form.values()})
                    .then(() => {
                        // this.setState({isOpen: false});
                        // this.props.updateState()
                        this.props.onSuccess && this.props.onSuccess()
                    }, (error) => {
                        console.error(error);
                        this.setState({error: "Error"});
                        this.props.onError && this.props.onError()
                    })
            }
        })
    }

    render() {

        const fields = this.props.props.map(x => ({
            ...x,
            label: x.title,
            extra: x.options,
            bindings: bindingType(x.type)
        }));

        const form = new MobxForm({fields});

        let actions = [
            <FlatButton
                label="Submit"
                primary={true}
                keyboardFocused={true}
                onClick={this.onSubmitClick(form, this.props.path)}
            />,
            <FlatButton
                label="Cancel"
                primary={false}
                onClick={this.props.onRequestClose}
            />
        ];

        return <ErrorBoundary>
            <Dialog
                title="Dialog"
                actions={actions}
                modal={false}
                open={this.props.isOpen}
                autoScrollBodyContent={true}
                onRequestClose={this.props.onRequestClose}>
                <div>
                    {this.state.error && <p style={{color: 'red'}}>{this.state.error}</p>}
                    <MaterialDialogForm form={form}/>
                </div>
            </Dialog>
        </ErrorBoundary>
    }
}

export default SimpleDialogForm;