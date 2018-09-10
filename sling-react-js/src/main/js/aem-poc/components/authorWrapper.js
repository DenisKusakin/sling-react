import React from 'react'
import axios from 'axios'
import AuthorHooks from '../utils/authorHooks';
import ReactDOM from 'react-dom';
import TreeContainer from './TreeContainer';
import Components from './index';

class AuthorWrapperComponent extends React.Component {
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
        axios.get(this.props.url)
            .then(response => {
                this.setState({html: response.data, isPending: false});
            })


    };

    initAuthorComponents() {
        const node = this.el;
        if(node){
            $(node).find("[data-author-component]").each(function(){
                const elem = this;
                const {dataset: {config}} = elem;
                ReactDOM.render(<TreeContainer components={Components} tree={JSON.parse(config)}/>, elem);
            });
        }
    };

    render() {
        return !this.state.isPending && <div ref={el => this.el = el} dangerouslySetInnerHTML={{__html: this.state.html}}/>;
    }
}

export default AuthorWrapperComponent;