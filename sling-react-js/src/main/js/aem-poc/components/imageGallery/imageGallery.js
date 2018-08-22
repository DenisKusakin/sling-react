import React from 'react'
import ReactImageGallery from 'react-image-gallery';
import "./imageGallery.less"

const ImageGallery = ({ images }) => <div className="poc-imageGallery"><ReactImageGallery items={images} /></div>;

export default ImageGallery;
