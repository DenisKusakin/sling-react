import React from 'react'
import RaisedButton from 'material-ui/RaisedButton';
import EditForm from './EditForm'

const SlidePropertiesButton = props => <EditForm {...props}>
    {
        ({showDialog}) => <RaisedButton
            label={'Slide Properties'}
            primary={false}
            onClick={showDialog}
        />
    }
</EditForm>;

export default SlidePropertiesButton