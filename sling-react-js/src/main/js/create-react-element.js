import Exports from "./export.js"
import React from 'react'

const traverse = (tree) => {
    let objProps = {}
    let obj = {
        props: objProps
    }
    Object.keys(tree).forEach(
        field => {
            let fieldVal = tree[field]

            if(field === '__type'){
                obj["type"] = Exports.components[fieldVal]
            } else if(Array.isArray(fieldVal)){
                objProps[field] = fieldVal.map(x => traverse(x))
            } else if(typeof fieldVal == 'object' && !Array.isArray(fieldVal)){
                objProps[field] = traverse(fieldVal)
            } else {
                objProps[field] = fieldVal
            }
        }
    )

    return React.createElement(obj['type'], obj.props, obj['children'] ? obj['children'] : undefined);
}

export default (tree) => {
    tree['__initialState'] = JSON.stringify(tree)

    return traverse(tree)
}