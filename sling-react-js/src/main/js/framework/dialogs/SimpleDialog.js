import React from 'react'
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import EditIcon from 'material-ui/svg-icons/editor/mode-edit';
import IconButton from 'material-ui/IconButton'
import {MuiThemeProvider} from 'material-ui/styles';
import ErrorBoundary from './ErrorBoundary';
import MobxForm from './form/MobxForm'
import {updateResource} from './../api'
import MaterialDialogForm from './MaterialDialogForm';

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
    if (type === "checkbox") {
        return "MaterialCheckbox"
    }
    if (type === 'select') {
        return "MaterialSelect"
    }
};

class SimpleDialog extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpen: false
        };

        this.onSubmitClick = this.onSubmitClick.bind(this);
    }

    onSubmitClick(form, url) {
        return e => form.onSubmit(e, {
            onSuccess: () => {

                console.info(form.values());
                return updateResource({url, props: form.values()})
                    .then(() => {
                        this.setState({isOpen: false});
                        this.props.updateState()
                    }, (error) => {
                        console.error(error);
                        this.setState({error: "Error"});
                    })
            }
        })
    }

    render() {

        if (!this.props.__dialog) {
            return this.props.children
        }

        const fields = this.props.__dialog && this.props.__dialog.props ?
            this.props.__dialog.props.map(x => ({
                ...x,
                label: x.title,
                extra: x.options,
                bindings: bindingType(x.type)
            })) : [];

        const form = new MobxForm({fields});

        let actions = [
            <FlatButton
                label="Submit"
                primary={true}
                keyboardFocused={true}
                onClick={this.onSubmitClick(form, this.props.__dialog.path)}
            />,
            <FlatButton
                label="Cancel"
                primary={false}
                onClick={() => {
                    this.setState({isOpen: false})
                }}
            />
        ];

        return <ErrorBoundary>
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
                    <MuiThemeProvider>
                        <div>
                            <IconButton onClick={() => this.setState(({isOpen}) => ({isOpen: !isOpen}))}
                                        iconStyle={styles.smallIcon}>
                                <EditIcon/>
                            </IconButton>
                            <Dialog
                                title="Dialog"
                                actions={actions}
                                modal={false}
                                open={this.state.isOpen}
                                onRequestClose={() => {
                                    this.setState({isOpen: false})
                                }}>
                                <div>
                                    {this.state.error && <p style={{color: 'red'}}>{this.state.error}</p>}
                                    <MaterialDialogForm form={form}/>
                                </div>
                            </Dialog>
                        </div>
                    </MuiThemeProvider>
                </div>
                <div style={{flexGrow: 1}}>
                    {this.props.children}
                </div>
            </div>
        </ErrorBoundary>
    }
}

export default SimpleDialog;