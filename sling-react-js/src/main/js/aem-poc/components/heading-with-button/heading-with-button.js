import React from 'react'

const HeadingWithButton = ({ heading, button }) => <div style={{display: 'flex'}}>
    <div><h1>{heading}</h1></div>
    <div>{button}</div>
</div>;

export default HeadingWithButton;