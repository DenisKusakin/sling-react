import React from 'react'
import ReactImageGallery from 'react-image-gallery';
import "react-image-gallery/styles/css/image-gallery.css"
import "./imageGallery.css"

const ImageGallery = ({ images }) => <div className="poc-imageGallery"><ReactImageGallery items={images} /></div>;

export default ImageGallery;
