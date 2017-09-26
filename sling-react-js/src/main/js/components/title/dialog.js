import React from 'react'
import Title from './title.js'
import SimpleDialog from './../../authoring-components/dialog/widgets/simple-dialog.jsx'

class TitleDialog extends React.Component {
    constructor(props){
        super(props)
        let propTypes = props.__propTypes
        this.fields = propTypes.map( ({value, persistenceProps: {name}}) => ({name, value}) )
    }

    render() {
        return <SimpleDialog original={<Title {...this.props}/>} fields={this.fields}/>
    }
}

export default TitleDialog
