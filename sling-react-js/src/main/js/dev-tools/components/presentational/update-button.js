import React from "react"
import RefreshIcon from 'react-material-icons/icons/navigation/refresh';
import IconButton from 'material-ui/IconButton'

const UpdateContentButton = ({onClick}) => <IconButton onClick={onClick}>
    <RefreshIcon/>
</IconButton>

export default UpdateContentButton