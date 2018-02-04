import React from 'react'
import Dialog from 'material-ui/Dialog';
import FlatButton from 'material-ui/FlatButton';
import TextField from 'material-ui/TextField';
import EditIcon from 'material-ui/svg-icons/editor/mode-edit';
import IconButton from 'material-ui/IconButton'
import {MuiThemeProvider} from 'material-ui/styles';
import ErrorBoundary from './ErrorBoundary';
import {observer} from 'mobx-react';
import MobxForm from './form/MobxForm'
import {updateResource} from './../api'

const styles = {
    smallIcon: {
        width: 12,
        height: 12,
    }
};

const Form = observer(({form}) => (
    <form onSubmit={form.onSubmit}>
        {form.map((x, i) => <div key={i}><TextField {...x.bind()}/><br/></div>)}
        <p>{form.error}</p>
    </form>
));

class SimpleDialog extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpen: false
        };

        const fields = props.__dialog && props.__dialog.props ?
            props.__dialog.props.map(({value, name, title, rules}) => ({
                value,
                name,
                label: title,
                rules
            })) : [];

        this.form = new MobxForm({fields});
        this.onSubmitClick = this.onSubmitClick.bind(this);
    }

    onSubmitClick(e) {
        this.form.onSubmit(e, {
            onSuccess: () => {
                const params = new URLSearchParams();
                const formValues = this.form.values();
                Object.keys(formValues).forEach(x => params.append(`./${x}`, formValues[x]));

                return updateResource({url: this.props.__dialog.path, props: this.form.values()})
                    .then(() => {
                        this.setState({isOpen: false});
                        console.log("Call updateState")
                        this.props.updateState()
                    }, (e) => {
                        console.error(e);
                        this.setState({error: "Error"});
                    })
            }
        })
    }

    render() {

        if (!this.props.__dialog) {
            return this.props.children//this.props.renderComp(this.props)
        }

        let actions = [
            <FlatButton
                label="Submit"
                primary={true}
                keyboardFocused={true}
                onClick={this.onSubmitClick}
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
                                    <Form form={this.form}/>
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