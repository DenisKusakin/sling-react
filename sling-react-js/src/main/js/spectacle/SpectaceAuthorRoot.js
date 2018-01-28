import React from 'react'
import wrapContent from './wrapContent'
import TreeContainer from './../framework/TreeContainer'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Toggle from 'material-ui/Toggle';
import ContentAdd from 'material-ui/svg-icons/content/add';
import FloatingActionButton from 'material-ui/FloatingActionButton';
import {createResource, deleteResource} from './../framework/api';

class SpectacleAuthorRoot extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isEditMode: props.content.isEditMode
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
                    createResource({url: props.content.content.__dialog.path, props: props.content.content.__dialog.props})
                }}/>
            </FloatingActionButton>
        </MuiThemeProvider>);
    }

    render() {
        const {content, dialogs} = this.props;
        const components = {
            ...(this.state.isEditMode ? dialogs : this.props.components),
            StateToggle: this.Toggle,
            AddSlideButton: this.AddSlideButton
        };

        return (<TreeContainer
                components={components}
                tree={wrapContent(content.content)}/>
        );
    }
}

export default SpectacleAuthorRoot