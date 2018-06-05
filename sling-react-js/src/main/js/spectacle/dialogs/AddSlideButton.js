import React from 'react'
import RaisedButton from 'material-ui/RaisedButton';
import {createResource} from './../../framework/api'
import {AuthorContext} from './context'

const AddSlideButton = ({url, props}) => <AuthorContext.Consumer>
    {
        ({updateStateFromServer}) => <RaisedButton
            label={'Add Slide'}
            primary={false}
            onClick={() => createResource({url, props})
                .then(updateStateFromServer)
                .catch(e => console.error("Failed to create slide", e))
            }
        />
    }
</AuthorContext.Consumer>;

export default AddSlideButton