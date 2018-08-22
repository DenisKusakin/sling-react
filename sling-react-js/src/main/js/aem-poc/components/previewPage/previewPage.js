import React from 'react'
import AemPublishRootComponent from '../aem-publish-component';
import Link from 'next/link';
import "./previewPage.less";

export default ({ data, name }) => <div>
    <div className="preview">
        <div className="preview-menu">
            <div className="preview-menu-h">Components</div>
            <div className="preview-menu-item">
                <Link href="/"><a>Parsys</a></Link>
            </div>
            <div className="preview-menu-item">
                <Link href="/heading"><a>Heading</a></Link>
            </div>
            <div className="preview-menu-item">
                <Link href="/narrow-banner"><a>Narrow banner</a></Link>
            </div>
            <div className="preview-menu-item">
                <Link href="/image-gallery"><a>Image gallery</a></Link>
            </div>
            <div className="preview-menu-item">
                <Link href="/accordeon"><a>Accordeon</a></Link>
            </div>
        </div>
        <div className="preview-container">
            <div className="preview-header">{ name }</div>
            <div className="preview-content">
                <AemPublishRootComponent config={data}/>
            </div>
        </div>
    </div>
</div>
