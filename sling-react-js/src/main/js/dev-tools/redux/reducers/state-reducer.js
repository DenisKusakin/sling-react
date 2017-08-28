import {UPDATE_STATE} from './../actions/state-action.js'

export default (state = {}, {type, newState}) => {
    if(type == UPDATE_STATE){
        return newState
    }
    return state
}