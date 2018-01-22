import React from 'react'
import Tree from './TreeContainer'

const PublisherApp = ({ content, components }) => {
    return <div>
        <script dangerouslySetInnerHTML={{__html: `window.__DATA = ${JSON.stringify(content)}`}}/>
        <Tree components={components} tree={content.content}/>
    </div>;
};

export default PublisherApp;