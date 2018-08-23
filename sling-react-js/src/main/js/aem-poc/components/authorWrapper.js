import React from 'react'
import axios from 'axios'

class AuthorWrapperComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isPending: true,
            html: null
        }
    }

    componentDidUpdate() {
        if (window && window.parent) {
            const elem = window.parent.document.querySelector('iframe#ContentFrame')
            if (elem) {
                elem.dispatchEvent(new Event('load'));
            }
        }
    }

    componentDidMount() {
        axios.get(this.props.url)
            .then(response => {
                this.setState({html: response.data, isPending: false});
            });
    }

    render() {
        return !this.state.isPending && <div dangerouslySetInnerHTML={{__html: this.state.html}}/>;
    }
}

export default AuthorWrapperComponent;