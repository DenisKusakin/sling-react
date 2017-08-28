import React from 'react'
import TreeContainer from './tree-container.js'
import components from './'
import axios from 'axios'

class SelfUpdatableContainer extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            content: props.initialContent
        }
    }

    componentDidMount(){
        this.intervalId = setInterval(() => {
            axios.get(`${this.props.contentPath}.json`)
            .then(({data: {initialContent}}) => {
                this.setState({
                    content: <TreeContainer tree={initialContent} components={components}/>
                })
            })
            .catch(function (error) {
                console.log(error);
            });
        }, 3000)
    }

    render() {
        return <div>
            {this.state.content}
        </div>
    }
}

export default SelfUpdatableContainer