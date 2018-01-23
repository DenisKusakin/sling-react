import React from 'react'

export default class ClientOnly extends React.Component {
    constructor(props){
        super(props)
        this.state = {
            isBrowser: false
        }
    }

    componentDidMount(){
        this.setState({isBrowser: true})
    }

    render() {
        return <div>
            {this.state.isBrowser && this.props.children}
        </div>
    }


}