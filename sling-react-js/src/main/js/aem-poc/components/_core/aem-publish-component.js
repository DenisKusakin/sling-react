import React from 'react'
import TreeContainer from './TreeContainer'
import Components from '../_componentsMap/serverComponentsMap.js'

const PublishRootComponent = ({config}) => <TreeContainer components={Components} tree={config}/>;

export default PublishRootComponent;