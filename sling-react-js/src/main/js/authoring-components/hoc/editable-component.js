import React from 'react';
//import './border.css';

const editableComponent = (Component, Dialog) => (props) => Dialog ? <Dialog {...props}/> : <Component {...props}/>

{/* <div className="edit-wrapper">
    <div style={{ float: 'left' }}>
        {Dialog && <Dialog />}
    </div>
    <Component {...props} />
</div> */}

export default editableComponent