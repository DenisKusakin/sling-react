import React from 'react'
import AddIcon from 'material-ui/svg-icons/content/add-circle-outline';
import IconButton from 'material-ui/IconButton'
import {MuiThemeProvider} from 'material-ui/styles';
import Dialog from 'material-ui/Dialog';
import {List, ListItem} from 'material-ui/List';
import darkTheme from 'material-ui/styles/baseThemes/darkBaseTheme';
import DeleteIcon from 'material-ui/svg-icons/action/delete';
import {createResource, deleteResource} from './../../framework/api';
import {DragDropContext, Droppable, Draggable} from 'react-beautiful-dnd';

const styles = {
    smallIcon: {
        width: 12,
        height: 12,
    }
};

const getListStyle = isDraggingOver => ({
    // background: isDraggingOver ? 'lightblue' : 'lightgrey',
    padding: grid,
    // width: 250,
});

const grid = 8;

const getItemStyle = (isDragging, draggableStyle) => ({
    // some basic styles to make the items look a bit nicer
    userSelect: 'none',
    padding: grid * 2,
    margin: `0 0 ${grid}px 0`,

    // change background colour if dragging
    // background: isDragging ? 'lightgreen' : 'grey',

    // styles we need to apply on draggables
    ...draggableStyle,
});

class ContainerDialog extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpen: false,
            error: null
        }
        this.renderItem = this.renderItem.bind(this)
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
                                        this.setState({isOpen: false});
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

    renderItem(item, index) {
        return (<div>
            <style jsx>
                {
                    `
                        .editable-item {
                            display: flex;
                            align-items: center;
                        }
                        .editable-item:hover {
                            outline: 2px solid green;
                        }
                    `
                }
            </style>
            <div className='editable-item'>
                <div style={{flexGrow: 0, zIndex: 1}}>
                    <MuiThemeProvider theme={darkTheme}>
                        <IconButton
                            iconStyle={styles.smallIcon}
                            onClick={() => {
                                deleteResource({url: this.props.__dialog.meta[index].path})
                                    .then(() => {
                                        this.props.updateState()
                                    })
                            }}>
                            <DeleteIcon/>
                        </IconButton>
                    </MuiThemeProvider>
                </div>
                <div style={{flexGrow: 1}}>
                    {item}
                </div>
            </div>
        </div>)
    }

    render() {
        return (<div>
            <style jsx>
                {`
                  .editable-container:hover {
                      outline: 2px dotted #2604B1;
                  }
                `}
            </style>
            <div className="editable-container">
                {/*<DragDropContext onDragEnd={() => {*/}
                {/*}}>*/}
                    {/*<Droppable droppableId="droppable">*/}
                        {/*{(provided, snapshot) => (*/}
                            {/*<div*/}
                                {/*ref={provided.innerRef}*/}
                                {/*style={getListStyle(snapshot.isDraggingOver)}*/}
                            {/*>*/}
                                {/*{this.props.components.map((item, index) => (*/}
                                    {/*<Draggable key={item.index} draggableId={index} index={index}>*/}
                                        {/*{(provided, snapshot) => (*/}
                                            {/*<div>*/}
                                                {/*<div*/}
                                                    {/*ref={provided.innerRef}*/}
                                                    {/*{...provided.draggableProps}*/}
                                                    {/*{...provided.dragHandleProps}*/}
                                                    {/*style={getItemStyle(*/}
                                                        {/*snapshot.isDragging,*/}
                                                        {/*provided.draggableProps.style*/}
                                                    {/*)}*/}
                                                {/*>*/}
                                                    {/*{this.renderItem(item, index)}*/}
                                                {/*</div>*/}
                                                {/*{provided.placeholder}*/}
                                            {/*</div>*/}
                                        {/*)}*/}
                                    {/*</Draggable>*/}
                                {/*))}*/}
                                {/*{provided.placeholder}*/}
                            {/*</div>*/}
                        {/*)}*/}
                    {/*</Droppable>*/}
                {/*</DragDropContext>*/}
                {this.props.components.map(this.renderItem)}
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