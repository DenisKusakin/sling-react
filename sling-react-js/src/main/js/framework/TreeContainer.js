import React from 'react'

const traverse = (tree, components, typeFieldName) => {
    let objProps = {};
    let obj = {
        props: objProps
    };
    let __objProps = !!tree[typeFieldName] ? objProps : objProps;

    if (Array.isArray(tree)) {
        return tree.map(x => traverse(x, components, typeFieldName));
    } else if (!(typeof tree === 'object')) {
        return tree;
    }

    Object.keys(tree).forEach(
        field => {
            let fieldVal = tree[field]
            //TODO: need to refactor
            if (field === typeFieldName) {
                obj["type"] = components[fieldVal]
            } else if (field === 'children' && tree[typeFieldName] !== undefined){
                obj['children'] = traverse(fieldVal, components, typeFieldName)
            } else {
                __objProps[field] = traverse(fieldVal, components, typeFieldName)
            }
        }
    );
    return !!obj['type'] ? React.createElement(obj['type'], obj.props, obj['children']) : objProps;
};

const TreeContainer = ({tree, components, typeFieldName='__type'}) => {
    if (!tree) {
        return null
    }
    const node = traverse(tree, components, typeFieldName);
    return node
};

export default TreeContainer;