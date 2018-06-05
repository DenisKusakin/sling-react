import React from 'react'
import {AuthorContext} from './context'
import RaisedButton from 'material-ui/RaisedButton';

class EditModeToggler extends React.Component {
    render() {
        return <AuthorContext.Consumer>
            {
                ({toggleEdit, isEditMode}) => {
                    return <RaisedButton
                        label={`${isEditMode ? 'Preview' : 'Edit'}`}
                        primary={true}
                        onClick={toggleEdit}
                    />
                }
            }
        </AuthorContext.Consumer>
    }
}

export default EditModeToggler