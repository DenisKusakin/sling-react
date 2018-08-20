import React from 'react'
import TreeContainer from './TreeContainer'
import Components from './index.js'

const AuthorComponent = ({config}) => <TreeContainer components={Components} tree={config}/>;

export default AuthorComponent;