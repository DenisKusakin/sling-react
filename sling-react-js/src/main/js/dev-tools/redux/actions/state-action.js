import thunk from 'redux-thunk';
import axios from 'axios'

export const UPDATE_STATE = "UPDATE_STATE"
export const EDIT_MODE_SWITCH = "EDIT_MODE_SWITCH"

export const requestStateFromServer = (url) => {
    return (dispatch) => {
        axios.get(url)
            .then(({data: {app}}) => {
                dispatch({
                    type: UPDATE_STATE,
                    newState: app
                })
            })
            .catch(function (error) {
            console.log(error);
            });
    }
}

export const switchEditMode = () => {
    return (dispatch) => {
        dispatch({
            type: EDIT_MODE_SWITCH
        })
    }
}