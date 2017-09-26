import React from "react"
import TextField from 'material-ui/TextField';
import { observer } from "mobx-react";
import { action } from "mobx";

@observer
class SimpleInput extends React.Component {
    render() {
        return <TextField
            hintText="Component's text"
            onChange={this.handleInputChange}
            value={this.props.model.value} />
    }

    @action
    handleInputChange = e => {
        this.props.model.value = e.target.value;
    };
}

export default SimpleInput