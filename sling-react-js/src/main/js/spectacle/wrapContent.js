export default content => {
    const wrapped = {
        ...content,
        children: [...content.children.map(x => {
            return {
                ...x,
                children: [
                    {
                        "renderer": "Container",
                        "components": [...x.children]
                    }
                ]
            }
        }), {
            "renderer": "Slide",
            "children": []
        }]
    };
    console.log(wrapped)
    return wrapped;
}