import React from 'react'
import AddIcon from 'material-ui/svg-icons/content/add-circle-outline';
import IconButton from 'material-ui/IconButton'
import {MuiThemeProvider} from 'material-ui/styles';
import Dialog from 'material-ui/Dialog';
import {List, ListItem} from 'material-ui/List';
import darkTheme from 'material-ui/styles/baseThemes/darkBaseTheme';
import DeleteIcon from 'material-ui/svg-icons/action/delete';
import {createResource, deleteResource} from './../../framework/api';

const styles = {
    smallIcon: {
        width: 12,
        height: 12,
    }
};

class ContainerDialog extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpen: false,
            error: null
        }
    }

    renderComponentList(dialog) {
        if (!dialog || !dialog.components) {
            return <p>No components available</p>
        }

        return <div>
            {this.state.error && <p style={{color: 'red'}}>{this.state.error}</p>}
            <List>
                {
                    dialog.components.map(({name, description, __props}) => {
                        return <ListItem
                            primaryText={name}
                            key={name}
                            secondaryText={
                                <p>
                                    {description}
                                </p>
                            }
                            onClick={() => {
                                createResource({url: dialog.path, props: __props})
                                    .then(() => {
                                        this.setState({isOpen: false})
                                        this.props.updateState()
                                    }, () => {
                                        this.setState({error: "Error"});
                                    })
                            }}
                        />
                    })
                }
            </List>
        </div>
    }

    render() {
        return (<div>
            <style jsx>
                {`
                  .editable-container:hover {
                      outline: 2px dotted #2604B1;
                  }
                  .editable-item:hover {
                    outline: 2px solid green;
                  }
                  .editable-item {
                    display: flex;
                    align-items: center;
                  }
                `}
            </style>
            <div className="editable-container">
                {
                    this.props.components.map((x, i) => (
                        <div key={i} className='editable-item'>
                            {/*
                                TODO: zIndex looks like a hach
                            */}
                            <div style={{flexGrow: 0, zIndex: 1}}>
                                <MuiThemeProvider theme={darkTheme}>
                                    <IconButton
                                        iconStyle={styles.smallIcon}
                                        onClick={() => {
                                            deleteResource({url: this.props.__dialog.meta[i].path})
                                                .then(() => {
                                                    this.props.updateState()
                                                })
                                        }}>
                                        <DeleteIcon/>
                                    </IconButton>
                                </MuiThemeProvider>
                            </div>
                            <div style={{flexGrow: 1}}>
                                {x}
                            </div>
                        </div>
                    ))
                }
                <MuiThemeProvider theme={darkTheme}>
                    <div>
                        <div style={{"textAlign": "center"}}>
                            <IconButton onClick={() => {
                                this.setState({isOpen: true})
                            }}>
                                <AddIcon/>
                            </IconButton>
                        </div>
                        <Dialog
                            title="Container Dialog"
                            actions={[]}
                            modal={false}
                            open={this.state.isOpen}
                            onRequestClose={() => {
                                this.setState({isOpen: false})
                            }}>
                            {
                                this.renderComponentList(this.props.__dialog)
                            }
                        </Dialog>
                    </div>
                </MuiThemeProvider>
            </div>
        </div>)
    }
}

export default ContainerDialog;