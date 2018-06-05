import React from 'react'
import RaisedButton from 'material-ui/RaisedButton';
import {deleteResource} from './../../framework/api'
import {AuthorContext} from './context'

const DeleteSlideButton = ({url}) => <AuthorContext.Consumer>
    {
        ({updateStateFromServer}) => <RaisedButton
            label={'Delete Slide'}
            primary={false}
            onClick={() => deleteResource({url})
                .then(updateStateFromServer)
                .catch(e => console.error("Failed to delete slide", e))
            }
        />
    }
</AuthorContext.Consumer>;

export default DeleteSlideButton