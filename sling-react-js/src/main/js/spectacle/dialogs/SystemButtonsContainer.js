import React from 'react'
import {AuthorContext} from './context'

const SystemButtonsContainer = props => <AuthorContext.Consumer>
    {
        ({isEditMode}) => <div style={{opacity: isEditMode ? 1 : 0.5}}>
            <div style={{display: 'flex', 'justify-content': 'center'}}>
                {props.children.map(x => <div style={{marginRight: '10px'}}>{x}</div>)}
            </div>
        </div>
    }
</AuthorContext.Consumer>;

export default SystemButtonsContainer;