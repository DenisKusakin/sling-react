import React from 'react';
import TreeContainer from './tree-container.js'
import components from './'
import wrapComponents from './../authoring-components/hoc/wrap-components.js'

const Application = ({ tree: { content, isEditMode } }) => <div>
    <TreeContainer tree={content} components={isEditMode ? wrapComponents(components) : components} />
</div>

export default Application;