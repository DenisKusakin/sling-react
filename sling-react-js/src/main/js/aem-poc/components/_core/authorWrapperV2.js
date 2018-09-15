import React from 'react'
import axios from 'axios'
import AuthorHooks from '../../utils/authorHooks';
import ReactDOM from 'react-dom';
import TreeContainer from './TreeContainer';
import Components from '../_componentsMap/clientComponentsMap';

class AuthorWrapperV2 extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isPending: null,
            html: null
        };
        this.initAuthorComponents = this.initAuthorComponents.bind(this);
    }

    componentDidUpdate() {
        this.initAuthorComponents();
        AuthorHooks.upadeIframe();
    }

    componentDidMount() {
        // axios.get(this.props.url)
        //     .then(response => {
        //         this.setState({html: response.data, isPending: false});
        //     })

        const htmlElement = $(`[data-editable-id="${this.props.id}"]`)[0];
        if (!htmlElement) {
            console.error("Editable not found", this.props.id);
            return;
        }
        const htmlContent = $(htmlElement).html();
        $(htmlElement).remove();
        this.setState({html: htmlContent, isPending: false});
    };

    initAuthorComponents() {
        const node = this.el;
        if (node) {
            $(node).find("[data-author-component]").each(function () {
                const elem = this;
                const {dataset: {config}} = elem;
                if(!config){
                    console.error("Invalid React component config", config, elem)
                    return;
                }
                ReactDOM.render(<TreeContainer components={Components} tree={JSON.parse(config)}/>, elem);
            });
        }
    };

    render() {
        return !this.state.isPending &&
            <div ref={el => this.el = el} dangerouslySetInnerHTML={{__html: this.state.html}}/>;
    }
}

export default AuthorWrapperV2;