import React from 'react';

const Slider = ({title, slidesProps}) => (
    <div>
        <h2>{title}</h2>
        {slidesProps && slidesProps.map( slide => (<p>{slide.title}</p>))}
    </div>
)

export default Slider;