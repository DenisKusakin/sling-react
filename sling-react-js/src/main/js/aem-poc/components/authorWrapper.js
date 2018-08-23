import React from 'react'

class AuthorWrapperComponent extends React.Component {
    constructor(props) {
        super(props);
    }
    componentDidMount() {
        if (window && window.parent) {
            window.parent.document.querySelector('iframe#ContentFrame').dispatchEvent(new Event('load'));
        }
    }

    render() {
        return <div dangerouslySetInnerHTML={{ __html: this.props.html }}/>;
    }
}

export default AuthorWrapperComponent;