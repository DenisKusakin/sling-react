import React from 'react'
import RaisedButton from 'material-ui/RaisedButton';
import EditForm from './EditForm'

const SlidePropertiesButton = ({title, ...props}) => <EditForm {...props}>
    {
        ({showDialog}) => <RaisedButton
            label={title}
            primary={false}
            onClick={showDialog}
        />
    }
</EditForm>;

export default SlidePropertiesButton