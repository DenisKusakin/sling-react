import React from 'react'
import AddIcon from 'material-ui/svg-icons/content/add-circle-outline';
import IconButton from 'material-ui/IconButton'
import {MuiThemeProvider} from 'material-ui/styles';
import Dialog from 'material-ui/Dialog';
import {List, ListItem} from 'material-ui/List';
import darkTheme from 'material-ui/styles/baseThemes/darkBaseTheme';
import axios from 'axios';
import DeleteIcon from 'material-ui/svg-icons/action/delete';


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
                                const data = new URLSearchParams();
                                console.log(__props)
                                Object.keys(__props).forEach(x => {
                                    data.append(x, __props[x])
                                });
                                axios({
                                    method: 'post',
                                    data,
                                    url: dialog.path,
                                    headers: {
                                        'Content-Type': 'application/x-www-form-urlencoded',
                                        'Accept': 'text/html'
                                    }
                                })
                                    .then(() => {
                                        this.setState({isOpen: false})
                                        this.props.updateState()
                                    }, error => {
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
                `}
            </style>
            <div className="editable-container">
                {
                    this.props.components.map((x, i) => <div key={i} className='editable-item'>
                        <div style={{float: 'left'}}>
                            <MuiThemeProvider theme={darkTheme}>
                                <IconButton onClick={() => this.setState(({isOpen}) => ({isOpen: !isOpen}))}
                                            iconStyle={styles.smallIcon}>
                                    <DeleteIcon/>
                                </IconButton>
                            </MuiThemeProvider>
                        </div>
                        {x}
                    </div>)
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