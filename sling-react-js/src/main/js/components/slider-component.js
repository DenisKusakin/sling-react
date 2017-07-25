import React from 'react';

export default ({title, slidesProps}) => (
    <div>
        <h2>{title}</h2>
        {slidesProps && slidesProps.map( slide => (<p>{slide.title}</p>))}
    </div>
);