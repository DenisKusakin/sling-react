import React from 'react';
import Menu from './menu/menu';
import AemPublishRootComponent from './aem-publish-component';
import "./previewPage/previewPage.less";

export default ({ data, pages, isNext }) => <div>
    <div className="preview">
        <Menu pages={pages} isNext />
        <div className="preview-container">
            <div className="preview-content">
                <AemPublishRootComponent config={data}/>
            </div>
        </div>
    </div>
</div>
