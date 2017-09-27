import React from 'react'
import Title from './title.js'
import SimpleDialog from './../../authoring-components/dialog/widgets/simple-dialog.jsx'
import {Editor, EditorState} from 'draft-js';

class TitleDialog extends React.Component {
    constructor(props){
        super(props)
        let propTypes = props.__propTypes
        this.fields = propTypes.map( ({value, persistenceProps}) => ({persistenceProps, value}) )
    }

    render() {
        return <SimpleDialog original={<Title {...this.props}/>} fields={this.fields}/>
    }
}

class TitleRTEDialog extends React.Component {
    constructor(props){
        super(props)
        let propTypes = props.__propTypes
        //this.state = {editorState: EditorState.createWithContent({contentState: propTypes.value})};
        this.state = {editorState: EditorState.createEmpty()};
        this.onChange = (editorState) => this.setState({editorState});
    }

    render() {
        return <Editor editorState={this.state.editorState} onChange={this.onChange} />;
    }
}

export default TitleDialog
