import React from 'react'
import TreeContainer from './TreeContainer'
import Components from '../_componentsMap/clientComponentsMap'
import axios from 'axios/index';
import AuthorHooks from '../../utils/authorHooks';

class AuthorComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = { config: (AuthorHooks.isEditMode() ? props.editConfig : props.previewConfig) };
        this.updatePreviewConfig = this.updatePreviewConfig.bind(this);
        this.updateEditConfig = this.updateEditConfig.bind(this);
    }

    componentDidMount() {
        if (!AuthorHooks.isEditMode()) {
            this.updatePreviewConfig();
        }
        AuthorHooks.onLayerToggle(this.updatePreviewConfig, this.updateEditConfig);
    };

    updateEditConfig() {
        this.setState({ config: this.props.editConfig });
        // AuthorHooks.upadeIframe();
    };

    updatePreviewConfig() {
        axios.get(`${this.props.previewUrl}?wcmmode=disabled`).then(response => {
            this.setState({ config: response.data });
            AuthorHooks.upadeIframe();
        });
    }

    render() {
        return <TreeContainer components={Components} tree={this.state.config}/>;
    }
}

export default AuthorComponent;