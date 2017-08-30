import React from 'react';
import TreeContainer from './tree-container.js'
import components from './'
import wrapComponents from './../authoring-components/hoc/wrap-components.js'

const Application = ({ tree: { content, isEditMode } }) => <div>
    {/* TODO: It should be implemented in another way!, */}
    <TreeContainer tree={content} components={isEditMode ? wrapComponents(components) : components} />
</div>

export default Application;