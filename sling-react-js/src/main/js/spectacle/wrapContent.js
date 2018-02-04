export default content => {
    const wrapped = {
        ...content,
        children: [...content.children.map(x => {
            return {
                ...x,
                children: [
                    {
                        "__type": "Container",
                        "components": [...x.children],
                        "__dialog": content['__dialog'],
                        "__dialog_type": "dialogs/Container"
                    },
                    {
                        "__type": "StateToggle"
                    },
                    {
                        "__type": "AddSlideButton"
                    }
                ]
            }
        })]
    };
    return wrapped;
}