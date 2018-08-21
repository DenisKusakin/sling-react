import React from 'react'
import TreeContainer from './TreeContainer'
import Components from './index.js'

const PublishRootComponent = ({config}) => <TreeContainer components={Components} tree={config}/>;

export default PublishRootComponent;