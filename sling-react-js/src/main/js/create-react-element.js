import Exports from "./export.js"
import React from 'react'

const traverse = (tree) => {
    let objProps = {}
    let obj = {
        props: objProps
    }
    let __objProps = !!tree['__type'] ? objProps : obj
    Object.keys(tree).forEach(
        field => {
            let fieldVal = tree[field]

            if(field === '__type'){
                obj["type"] = Exports.components[fieldVal]
            } else if(Array.isArray(fieldVal)){
                __objProps[field] = fieldVal.map(x => traverse(x))
            } else if(typeof fieldVal == 'object' && !Array.isArray(fieldVal)){
                __objProps[field] = traverse(fieldVal)
            } else {
                __objProps[field] = fieldVal
            }
        }
    )

    return !!obj['type'] ? React.createElement(obj['type'], obj.props, obj['children'] ? obj['children'] : undefined) : obj;
}

//const createElement = obj => {
//    let type = obj['type']
//    let props = obj['props']
//    let children = obj['children']
//
//    if(!!type){
//
//    }else{
//
//    }
//}

export default (tree, addInitialState) => {
    if(!!addInitialState){
        tree['__initialState'] = JSON.stringify(tree)
    }
    let element = traverse(tree)



    console.log(element)
    //return React.createElement(obj['type'], obj.props, obj['children'] ? obj['children'] : undefined);
    return element
}