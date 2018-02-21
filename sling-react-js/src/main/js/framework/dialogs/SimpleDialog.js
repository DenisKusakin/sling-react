import React from 'react'
import EditIcon from 'material-ui/svg-icons/editor/mode-edit';
import IconButton from 'material-ui/IconButton'
import {MuiThemeProvider} from 'material-ui/styles';
import ErrorBoundary from './ErrorBoundary';
import DialogForm from './Dialog'

const styles = {
    smallIcon: {
        width: 12,
        height: 12,
    }
};

class SimpleDialog extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpen: false
        };

    }

    render() {

        if (!this.props.__dialog) {
            return this.props.children
        }

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
                            <DialogForm
                                isOpen={this.state.isOpen}
                                onRequestClose={() => {
                                    this.setState({isOpen: false})
                                }}
                                onSuccess={() => {
                                    this.props.updateState();
                                    this.setState({isOpen: false})
                                }}
                                path={this.props.__dialog.path}
                                props={this.props.__dialog.props}/>
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