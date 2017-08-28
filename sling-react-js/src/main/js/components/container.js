import React from 'react';

const Container = ({components, isEditMode}) => {
    return <div>
        {components.map((x, i) => <div key={i}>{x}</div>)}
        {isEditMode && <p>Add Component</p>}
    </div>
}

export default Container;