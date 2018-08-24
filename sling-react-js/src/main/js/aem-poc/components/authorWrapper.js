import React from 'react'
import axios from 'axios'
import AuthorHooks from '../utils/authorHooks';

class AuthorWrapperComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isPending: true,
            html: null
        }
    }

    componentDidUpdate() {
        AuthorHooks.upadeIframe();
    }

    componentDidMount() {
        axios.get(this.props.url)
            .then(response => this.setState({html: response.data, isPending: false}));
    };


    render() {
        return !this.state.isPending && <div dangerouslySetInnerHTML={{__html: this.state.html}}/>;
    }
}

export default AuthorWrapperComponent;