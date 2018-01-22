import React from 'react'
import TreeContainer from './../../framework/TreeContainer'
import components from './../dialog'
import axios from 'axios'
import Container from './../container'

class UpdatableContainer extends React.Component {
    constructor(props){
        super(props);
        this.state = {
            components: props.components
        }
    }

    requestContent(){
        return axios.get(`${this.props.url}`)
    }

    componentDidMount(){
        this.intervalId = setInterval(() => {
            this.requestContent()
                .then(({data}) => {
                    this.setState({
                        //TODO: looks odd since TreeContainer is React component
                        components: TreeContainer({tree: data, components: components})
                    })
                })
                .catch(function (error) {
                    console.log(error);
                });
        }, 3000)
    }

    componentWillUnmount(){
        clearInterval(this.intervalId);
    }

    render() {
        return <Container components={this.state.components}/>
    }
}

export default UpdatableContainer