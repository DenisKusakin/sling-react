import React from 'react'

const SystemButtonsContainer = props => <div>
    <div style={{display: 'flex', 'justify-content': 'center'}}>
        {props.children.map(x => <div style={{marginRight: '10px'}}>{x}</div>)}
    </div>
</div>;

export default SystemButtonsContainer;