import React from 'react'
import axios from 'axios'
import AuthorHooks from '../utils/authorHooks';
import AemAuthorComponent from './../components/aem-author-component';
import ReactDOM from 'react-dom';

class AuthorWrapperComponent extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isPending: true,
            html: null
        };
        //this.myRef = React.createRef();
    }

    componentDidUpdate() {
        //AuthorHooks.upadeIframe();
        // const node = this.myRef.current;
        const node = this.el;
        //window.document.dispatchEvent(new Event('cq-editables-loaded'));
        if(node){
            $(node).find("[data-author-component]").each(function(){
                const elem = this;
                const {dataset: {config, confUrl}} = elem;
                ReactDOM.render(<AemAuthorComponent previewUrl={confUrl} previewConfig={null} editConfig={JSON.parse(config)} />, elem);
            });
            // console.log("!", node);
            // setTimeout(() => {
            //     document.dispatchEvent(new Event('foundation-contentloaded'));
            //     window.parent.document.dispatchEvent(new Event('foundation-contentloaded'));
            // }, 3000);
            // $(document).trigger("foundation-contentloaded");
        }
    }

    componentDidMount() {
        axios.get(this.props.url)
            .then(response => this.setState({html: response.data, isPending: false}));
    };


    render() {
        return !this.state.isPending && <div ref={el => this.el = el} dangerouslySetInnerHTML={{__html: this.state.html}}/>;
    }
}

// const AuthorWrapperComponent = ({html}) => <div dangerouslySetInnerHTML={{__html: html}}/>;

export default AuthorWrapperComponent;