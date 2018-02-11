import React from 'react'
import wrapContent from './wrapContent'
import TreeContainer from './../framework/TreeContainer'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Toggle from 'material-ui/Toggle';
import FlatButton from 'material-ui/FlatButton';
import {createResource, deleteResource} from './../framework/api';
import axios from "axios/index";
import Dialog from './../framework/dialogs/Dialog'

const AddSlideButton = ({url, props, callback, isEditMode}) => {
    if (isEditMode) {
        return (<MuiThemeProvider>
            <FlatButton label="Add Slide" primary={true} onClick={() => {
                createResource({
                    url,
                    props
                }).then(callback)
            }}/>
        </MuiThemeProvider>)
    }
    return null;
};

const DeleteSlideButton = ({url, callback, isEditMode}) => {
    if (!isEditMode) {
        return null
    }

    return (<MuiThemeProvider>
        <FlatButton label="Delete Slide" secondary={true} onClick={() => {
            deleteResource({url})
                .then(() => {
                    try {
                        window.location.hash = window.location.hash.substr(2) - 1;
                    } catch (e) {
                        console.error(e)
                    }
                    callback()
                })
        }}/>
    </MuiThemeProvider>)
};

class PropsButton extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isOpen: false
        }
    }

    render() {
        const {path, title, props, isEditMode, updateState} = this.props;

        if (!isEditMode) {
            return null
        }

        if (!path || !props) {
            return null
        }

        return (<MuiThemeProvider>
            <FlatButton label={title} secondary={true} onClick={() => {
                this.setState({isOpen: true})
            }}/>
            <Dialog
                isOpen={this.state.isOpen}
                path={path}
                props={props}
                onRequestClose={() => {
                    this.setState({isOpen: false})
                }}
                onSuccess={() => {
                    updateState();
                    this.setState({isOpen: false})
                }}
            />
        </MuiThemeProvider>)
    }
}

class SpectacleAuthorRoot extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isEditMode: props.content.isEditMode,
            content: props.content.content
        };

        this.Toggle = () => (<MuiThemeProvider>
            <Toggle
                label="Edit mode"
                toggled={this.state.isEditMode}
                onToggle={(x, isToggled) => {
                    this.setState({isEditMode: isToggled})
                }}
            />
        </MuiThemeProvider>);

        this.updateState = this.updateState.bind(this);
        this.extendDialogs = components => {
            const extendedComponents = {};
            Object.keys(components).forEach(name => {
                const Comp = components[name];
                //TODO: refactoring is required
                if (name.startsWith("dialogs/")) {
                    extendedComponents[name] = x => <Comp {...x} updateState={this.updateState}/>
                } else {
                    extendedComponents[name] = Comp;
                }
            });

            return extendedComponents
        }
    }

    updateState() {
        if (!this.props.content.url) {
            console.warn('Url is not specified');
            return;
        }
        axios.get(this.props.content.url)
            .then(({data}) => {
                this.setState({content: data.content})
            })
    }

    render() {
        const {content} = this.state;
        const dialogProps = this.props.content.content.__dialog;
        const components = {
            ...this.props.components,
            StateToggle: this.Toggle,
            AddSlideButton: () => <AddSlideButton
                url={dialogProps.path}
                isEditMode={this.state.isEditMode}
                callback={this.updateState}
                props={dialogProps.components[0].__props}/>,
            DeleteSlideButton: ({index}) => <DeleteSlideButton
                isEditMode={this.state.isEditMode}
                callback={this.updateState}
                url={dialogProps.meta[index].path}/>,
            SlidePropsButton: ({path, props}) => <PropsButton
                isEditMode={this.state.isEditMode}
                title="Slide Properties"
                path={path}
                props={props}
                updateState={this.updateState}
            />,
            PresentationThemeButton: () => <PropsButton
                isEditMode={this.state.isEditMode}
                title="Presentation Theme Properties"
                path={dialogProps.theme.path}
                props={dialogProps.theme.props}
                updateState={this.updateState}
            />
        };

        if (this.state.isEditMode) {
            return (<TreeContainer
                    components={this.extendDialogs(components)}
                    dialogTypeFieldName="__dialog_type"
                    tree={wrapContent(content)}/>
            );
        }

        return (<TreeContainer
                components={components}
                tree={wrapContent(content)}/>
        );
    }
}

export default SpectacleAuthorRoot