import React from 'react';
import ImageGallery from 'react-image-gallery';
import "react-image-gallery/styles/css/image-gallery.css";

const SlingImageGallery = ({items}) => (
<div>
  <h2>Test 2</h2>
<ImageGallery
  items={items}
  slideInterval={2000}
  onImageLoad={console.log}/>
  </div>
)

export default SlingImageGallery;