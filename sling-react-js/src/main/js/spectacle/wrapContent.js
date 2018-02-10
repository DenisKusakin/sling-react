export default content => {
    const wrapped = {
        ...content,
        children: [...content.children.map((x, i) => {
            return {
                ...x,
                children: [
                    {
                        ...x,
                        "children": null,
                        "__type": "Container",
                        "components": [...x.children],
                        "__dialog_type": "dialogs/Container"
                    },
                    {
                        "__type": "StateToggle"
                    },
                    {
                        "__type": "AddSlideButton"
                    },
                    {
                        "__type": "DeleteSlideButton",
                        "index": i
                    }
                ]
            }
        })]
    };
    return wrapped;
}