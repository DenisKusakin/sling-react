import React from 'react'
import {AuthorContext} from './context'
import RaisedButton from 'material-ui/RaisedButton';

class EditModeToggler extends React.Component {
    render() {
        return <AuthorContext.Consumer>
            {
                ({toggleEdit, isEditMode}) => {
                    return <div><RaisedButton
                        label={`${isEditMode ? 'Preview' : 'Edit'}`}
                        primary={true}
                        onClick={toggleEdit}
                    /></div>
                }
            }
        </AuthorContext.Consumer>
    }
}

export default EditModeToggler