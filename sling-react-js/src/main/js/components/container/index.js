import React from 'react'

const Container = ({ components }) => <div>
    {components.map((x, i) => <div key={i}>{x}</div>)}
</div>;

export default Container;