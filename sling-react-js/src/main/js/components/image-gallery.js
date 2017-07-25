import React from 'react';
import ImageGallery from 'react-image-gallery';
import "react-image-gallery/styles/css/image-gallery.css";

const SlingImageGallery = ({items}) => (
<ImageGallery
  items={items}
  slideInterval={2000}
  onImageLoad={console.log}/>
)

export default SlingImageGallery;