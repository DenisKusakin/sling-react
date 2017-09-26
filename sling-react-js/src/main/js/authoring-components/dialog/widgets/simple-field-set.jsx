import React from "react"
import TextField from 'material-ui/TextField';
import { observer } from "mobx-react";
import { action, observable } from "mobx";
import SimpleField from "./simple-input.jsx"

@observer
class SimpleFieldSet extends React.Component {
    @observable fieldSet
    
    constructor(props){
        super(props)
        this.fieldSet = props.model
    }

    render() {
        return <div>
            {this.fieldSet && this.fieldSet.fields.map(field => <div><SimpleField model={field}/><br/></div>)}
        </div>
    }
}

export default SimpleFieldSet