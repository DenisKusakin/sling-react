import React from 'react'
import {AuthorContext} from './context'
import {createResource, moveResource} from "../../framework/api";
import {List, ListItem} from 'material-ui/List';
import AddIcon from 'material-ui/svg-icons/content/add-circle-outline';
import IconButton from 'material-ui/IconButton'
import Dialog from 'material-ui/Dialog';
import {DragDropContext, Droppable, Draggable} from 'react-beautiful-dnd';

class ContainerDialog extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpen: false,
            error: null,
            children: props.children
        };
        this.renderComponentList = this.renderComponentList.bind(this);
        this.reorder = this.reorder.bind(this);
    }

    reorder(sourceIndex, destinationIndex) {
        this.setState(({children}) => {
            let childrenArray = React.Children.toArray(children);
            let copy = React.Children.map(childrenArray, x => React.cloneElement(x));
            let sourceElement = copy[sourceIndex];
            copy.splice(sourceIndex, 1);
            copy.splice(destinationIndex, 0, sourceElement);
            return {
                children: copy
            }
        });
        return sourceIndex > destinationIndex ? moveResource({
            url: this.props.moveInfo[sourceIndex].url,
            orderBefore: this.props.moveInfo[destinationIndex].nodeName
        }) : moveResource({
            url: this.props.moveInfo[destinationIndex].url,
            orderBefore: this.props.moveInfo[sourceIndex].nodeName
        })
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
                                <DragDropContext
                                    onDragEnd={(x) => {
                                        if (x.destination !== null) {
                                            this.reorder(x.source.index, x.destination.index)
                                                .then(updateFromServer)
                                        }
                                    }}>
                                    <Droppable droppableId="droppable">
                                        {
                                            (provided, snapshot) => <div ref={provided.innerRef}>
                                                {
                                                    this.state.children && this.state.children.map((x, i) =>
                                                        <Draggable key={i} draggableId={i} index={i}>
                                                            {
                                                                (provided, snapshot) =>
                                                                    <div
                                                                        ref={provided.innerRef}
                                                                        {...provided.draggableProps}
                                                                        {...provided.dragHandleProps}>
                                                                        <div
                                                                            className='editable-item'>
                                                                            <div>
                                                                                {x}
                                                                            </div>
                                                                        </div>
                                                                    </div>
                                                            }
                                                        </Draggable>)
                                                }
                                            </div>
                                        }
                                    </Droppable>
                                </DragDropContext>
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
                    return this.state.children
                }
            }
        </AuthorContext.Consumer>
    }
}

export default ContainerDialog;