export default content => {
    return {
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
                    },
                    {
                        "__type": "SlidePropsButton",
                        ...x['__dialog']['theme']
                    },
                    {
                        "__type": "PresentationThemeButton"
                    }
                ]
            }
        })]
    };
}