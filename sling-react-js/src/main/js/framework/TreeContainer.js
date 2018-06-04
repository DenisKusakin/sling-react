import React from 'react'

const traverse = (tree, components, typeFieldName, dialogTypeFieldName) => {
    if (tree === null || tree === undefined) {
        return null
    }
    let objProps = {};
    let obj = {
        props: objProps
    };
    let __objProps = !!tree[typeFieldName] ? objProps : objProps;

    if (Array.isArray(tree)) {
        return tree.map(x => traverse(x, components, typeFieldName, dialogTypeFieldName));
    } else if (!(typeof tree === 'object')) {
        return tree;
    }

    Object.keys(tree).forEach(
        field => {
            let fieldVal = tree[field];
            if (dialogTypeFieldName && field === dialogTypeFieldName) {
                obj["dialog"] = components[fieldVal]
            }
            if (field === typeFieldName) {
                obj["type"] = components[fieldVal]
            } else if (field === 'children' && tree[typeFieldName] !== undefined) {
                obj['children'] = traverse(fieldVal, components, typeFieldName, dialogTypeFieldName)
            } else {
                __objProps[field] = traverse(fieldVal, components, typeFieldName, dialogTypeFieldName)
            }
        }
    );

    if (obj['type']) {
        if (obj['dialog'] && dialogTypeFieldName) {
            return React.createElement(obj['dialog'], {...obj.props}, React.createElement(obj['type'], {...obj.props}, obj['children']))
        }
        // if(obj.props.test === "test"){
        //     debugger;
        // }
        // let children = obj['children'];
        // if(children && children.length === 1){
        //     children = children[0];
        // }
        return React.createElement(obj['type'], obj.props, obj['children'])
    } else {
        return objProps;
    }
    // return !!obj['type'] ? React.createElement(obj['type'], obj.props, obj['children']) : objProps;
};

const TreeContainer = ({tree, components, typeFieldName = '__type', dialogTypeFieldName = null}) => {
    console.log("TreeContainer render");
    if (!tree) {
        return null
    }
    return traverse(tree, components, typeFieldName, dialogTypeFieldName);
};

export default TreeContainer;