import React from 'react';
import "./previewPage/previewPage.less";

export default ({ navigation, content }) => <div>
    <div className="preview">
        { navigation }
        <div className="preview-container">
            <div className="preview-content">
                { content }
            </div>
        </div>
    </div>
</div>
