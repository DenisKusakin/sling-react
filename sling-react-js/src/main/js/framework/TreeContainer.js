import React from 'react'

const traverse = (tree, components, typeFieldName) => {
    if (tree === null || tree === undefined) {
        return null
    }
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
            let fieldVal = tree[field];
            if (field === typeFieldName) {
                obj["type"] = components[fieldVal]
            } else if (field === 'children' && tree[typeFieldName] !== undefined) {
                obj['children'] = traverse(fieldVal, components, typeFieldName)
            } else {
                __objProps[field] = traverse(fieldVal, components, typeFieldName)
            }
        }
    );

    if (obj['type']) {
        let Component = obj['type'];
        return <Component {...obj.props}>
            {
                Array.isArray(obj.children) ? obj.children.map((x, i) => React.cloneElement(x, {key: i})) : obj.children
            }
        </Component>
    } else {
        return objProps;
    }
};

const TreeContainer = ({tree, components, typeFieldName = '__type'}) => {
    if (!tree) {
        return null
    }
    return traverse(tree, components, typeFieldName);
};

export default TreeContainer;