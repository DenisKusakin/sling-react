import { UPDATE_STATE, EDIT_MODE_SWITCH } from './../actions/state-action.js'

export default (state = {}, { type, newState }) => {
    if (type == UPDATE_STATE) {
        return newState
    } else if (type == EDIT_MODE_SWITCH) {
        return {
            ...state,
            isEditMode: !state.isEditMode
        }
    }
    return state
}