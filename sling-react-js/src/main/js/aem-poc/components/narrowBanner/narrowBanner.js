import React from 'react';
import './narrowBanner.less';

const NarrowBanner = ({ title, noH1Tag }) =>
  <div className="ds-narrow-banner">
    { title && !noH1Tag && <h1 className="ds-h1 ds-hero-container-inner">{title}</h1> }
    { title && noH1Tag && <div className="ds-h1 ds-hero-container-inner">{title}</div> }
  </div>;

export default NarrowBanner;
