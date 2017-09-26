import React from 'react'
import Checkbox from 'material-ui/Checkbox';
import Toggle from 'material-ui/Toggle';

const EditModeSwitcher = ({checked, onCheck}) => {
    // return <Checkbox
    //     label="Edit Mode"
    //     checked={checked}
    //     onCheck={onCheck}
    // />
    return <Toggle
        defaultToggled={checked}
        onToggle={onCheck}
    />
}

export default EditModeSwitcher