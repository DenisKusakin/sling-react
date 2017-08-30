import editableComponent from './editable-component.js'
{/* TODO: It should be implemented in another way!, */}
import dialogs from './../../components/dialogs.js'

export default components => {
    let wrappedComponents = {}
    Object.keys(components)
        .forEach(
            cmpName => wrappedComponents[cmpName] = editableComponent(components[cmpName], dialogs[cmpName])
        )
    
    return wrappedComponents
}