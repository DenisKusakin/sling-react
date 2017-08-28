import {combineReducers} from "redux"
import state from './state-reducer.js'
import devTools from './dev-tools-reducer.js'

export default combineReducers({
    state,
    devTools
})