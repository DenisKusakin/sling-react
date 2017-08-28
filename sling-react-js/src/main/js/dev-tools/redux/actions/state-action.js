import thunk from 'redux-thunk';
import axios from 'axios'

export const UPDATE_STATE = "UPDATE_STATE"

export const requestStateFromServer = (url) => {
    return (dispatch) => {
        axios.get(url)
            .then(({data: {app}}) => {
                //ReactDOM.render(createReactElement(app), document.getElementById('app'))
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

// export UPDATE_STATE
// export requestStateFromServer;