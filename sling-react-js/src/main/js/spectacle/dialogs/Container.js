import React from 'react'
import {AuthorContext} from './context'
import {createResource} from "../../framework/api";
import {List, ListItem} from 'material-ui/List';
import AddIcon from 'material-ui/svg-icons/content/add-circle-outline';
import IconButton from 'material-ui/IconButton'
import Dialog from 'material-ui/Dialog';

class ContainerDialog extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpen: false,
            error: null
        };
        this.renderComponentList = this.renderComponentList.bind(this)
    }

    renderComponentList(updateFromServer) {
        if (!this.props.components || this.props.components.length === 0) {
            return <p>No components available</p>
        }

        return <div>
            {this.state.error && <p style={{color: 'red'}}>{this.state.error}</p>}
            <List>
                {
                    this.props.components.map(({title, description, props}) => {
                        return <ListItem
                            primaryText={title}
                            key={title}
                            secondaryText={
                                <p>
                                    {description}
                                </p>
                            }
                            onClick={() => {
                                createResource({url: this.props.resourcePath, props})
                                    .then(() => {
                                        this.setState({isOpen: false});
                                        updateFromServer()
                                    }, () => {
                                        this.setState({error: "Error"});
                                        console.error("Failed to create resource", this.props.resourcePath, props)
                                    })
                            }}
                        />
                    })
                }
            </List>
        </div>
    }

    render() {
        return <AuthorContext.Consumer>
            {
                ({isEditMode, updateFromServer}) => {
                    if (isEditMode) {
                        return <div>
                            <Dialog
                                title="Container Dialog"
                                modal={false}
                                open={this.state.isOpen}
                                autoScrollBodyContent={true}
                                onRequestClose={() => {
                                    this.setState({isOpen: false})
                                }}>
                                <div>
                                    {this.renderComponentList(updateFromServer)}
                                </div>
                            </Dialog>
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
                                    this.props.children && this.props.children.map((x, i) => <div
                                        className='editable-item'
                                        key={i}>
                                        <div>
                                            {x}
                                        </div>
                                    </div>)
                                }
                                <div style={{"textAlign": "center"}}>
                                    <IconButton onClick={() => {
                                        this.setState({isOpen: true})
                                    }}>
                                        <AddIcon/>
                                    </IconButton>
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

export default ContainerDialog;