import React from 'react';
import Menu from './menu/menu';
import AemPublishRootComponent from './aem-publish-component';
import "./previewPage/previewPage.less";

export default ({ navigation, content, isNext }) => <div>
    <div className="preview">
        { navigation }
        <div className="preview-container">
            <div className="preview-content">
                { content }
            </div>
        </div>
    </div>
</div>
