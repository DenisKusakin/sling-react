import React from 'react'
import wrapContent from './wrapContent'
import TreeContainer from './../framework/TreeContainer'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Toggle from 'material-ui/Toggle';
import ContentAdd from 'material-ui/svg-icons/content/add';
import FloatingActionButton from 'material-ui/FloatingActionButton';
import {createResource, deleteResource} from './../framework/api';
import axios from "axios/index";

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

        this.AddSlideButton = () => (<MuiThemeProvider>
            <FloatingActionButton>
                <ContentAdd onClick={() => {
                    createResource({
                        url: props.content.content.__dialog.path,
                        props: props.content.content.__dialog.props
                    })
                }}/>
            </FloatingActionButton>
        </MuiThemeProvider>);

        this.updateState = this.updateState.bind(this);
        this.extendDialogs = components => {
            const extendedComponents = {};
            Object.keys(components).forEach(name => {
                const Comp = components[name];
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
        const components = {
            ...this.props.components,
            StateToggle: this.Toggle,
            AddSlideButton: this.state.isEditMode ? this.AddSlideButton : () => null
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