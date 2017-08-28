import editableComponent from './editable-component.js'

export default components => {
    let wrappedComponents = {}
    Object.keys(components)
        .forEach(cmpName => wrappedComponents[cmpName] = editableComponent(components[cmpName]))
    
    return wrappedComponents
}