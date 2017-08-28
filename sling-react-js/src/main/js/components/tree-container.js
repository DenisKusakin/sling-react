import React from 'react'

const traverse = (tree, components) => {
    let objProps = {}
    let obj = {
        props: objProps
    }
    let __objProps = !!tree['__type'] ? objProps : obj
    Object.keys(tree).forEach(
        field => {
            let fieldVal = tree[field]
            //TODO: need to refactor
            if (field === '__type') {
                obj["type"] = components[fieldVal]
            } else if (field === '__propTypes') {
                __objProps['__propTypes'] = fieldVal
            } else if (Array.isArray(fieldVal)) {
                __objProps[field] = fieldVal.map(x => traverse(x, components))
            } else if (typeof fieldVal == 'object') {
                __objProps[field] = traverse(fieldVal, components)
            } else {
                __objProps[field] = fieldVal
            }
        }
    )

    return !!obj['type'] ? React.createElement(obj['type'], obj.props, obj['children']) : obj;
}

const TreeContainer = ({ tree, components }) => {
    if (!tree || !tree['__type']) {
        return null
    }
    let node = traverse(tree, components)

    return node
}

export default TreeContainer;