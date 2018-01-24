import React from 'react'

const traverse = (tree, components) => {
    let objProps = {};
    let obj = {
        props: objProps
    };
    let __objProps = !!tree['__type'] ? objProps : objProps;

    if (Array.isArray(tree)) {
        return tree.map(x => traverse(x, components));
    } else if (!(typeof tree === 'object')) {
        return tree;
    }

    Object.keys(tree).forEach(
        field => {
            let fieldVal = tree[field]
            //TODO: need to refactor
            if (field === '__type') {
                obj["type"] = components[fieldVal]
            } else if (field === 'children'){
                obj['children'] = traverse(fieldVal, components)
            } else {
                __objProps[field] = traverse(fieldVal, components)
            }
        }
    );
    return !!obj['type'] ? React.createElement(obj['type'], obj.props, obj['children']) : objProps;
};

const TreeContainer = ({tree, components}) => {
    if (!tree) {
        return null
    }
    let node = traverse(tree, components);
    ;

    if (Array.isArray(tree)) {
        return tree.map(x => traverse(x, components));
    } else {
        node = traverse(tree, components);
    }

    return node
};

export default TreeContainer;