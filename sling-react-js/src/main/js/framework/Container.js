import React from 'react'

const typeFieldName = '__type';

const traverse = (tree, componentResolver) => {
    if (tree === null || tree === undefined) {
        return null
    }
    let objProps = {};
    let obj = {
        props: objProps
    };
    let __objProps = !!tree[typeFieldName] ? objProps : objProps;

    if (Array.isArray(tree)) {
        return tree.map(x => traverse(x, componentResolver));
    } else if (!(typeof tree === 'object')) {
        return tree;
    }

    Object.keys(tree).forEach(
        field => {
            let fieldVal = tree[field];
            if (field === typeFieldName) {
                obj["type"] = componentResolver(fieldVal)
            } else if (field === 'children' && tree[typeFieldName] !== undefined) {
                obj['children'] = traverse(fieldVal, componentResolver)
            } else {
                __objProps[field] = traverse(fieldVal, componentResolver)
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

const Container = ({tree, componentResolver}) => {
    if (!tree) {
        return null
    }
    return traverse(tree, componentResolver);
};

export default Container;