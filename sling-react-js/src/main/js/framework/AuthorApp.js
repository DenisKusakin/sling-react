import React from 'react'
import Tree from './TreeContainer'
import PublisherApp from './PublisherApp'
import MuiThemeProvider from 'material-ui/styles/MuiThemeProvider';
import Toggle from 'material-ui/Toggle';
import axios from 'axios'
import dialog from "../components/dialog";

const wrapComponent = (Component, updateState) => props => <Component {...props} updateState={updateState}/>;

class AuthorApp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isEditMode: props.content.isEditMode,
            content: props.content.content
        };
        this.updateState = this.updateState.bind(this);
        this.dialogs = {};
        Object.keys(props.dialogs).forEach(x => {
            this.dialogs[x] = wrapComponent(props.dialogs[x], this.updateState)
        })
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
        let app = null;
        if (this.state.isEditMode) {
            app = <div>
                {/*<script>*/}
                {/*__DATA = {`${JSON.stringify(this.props.content.content)}`}*/}
                {/*</script>*/}
                <script dangerouslySetInnerHTML={{__html: `window.__DATA = ${JSON.stringify(this.props.content)}`}}/>
                <Tree components={this.dialogs} tree={this.state.content}/>
            </div>
        } else {
            app = <PublisherApp content={this.state} components={this.props.components}/>;
        }
        return <div>
            <MuiThemeProvider>
                <Toggle
                    label="Edit mode"
                    toggled={this.state.isEditMode}
                    onToggle={(x, isToggled) => {
                        this.setState({isEditMode: isToggled})
                    }}
                />
            </MuiThemeProvider>
            {app}
        </div>;
    }
}

export default AuthorApp;