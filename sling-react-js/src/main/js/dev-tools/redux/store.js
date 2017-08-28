import {createStore, applyMiddleware, compose } from "redux";
import rootReducer from "./reducers/root-reducer.js"
import thunk from 'redux-thunk';
//import persistState from 'redux-localstorage'

const configureStore = preloadedState => {
    return createStore(
        rootReducer,
        preloadedState,
        compose(
          applyMiddleware(thunk),
          //persistState(),
          window.__REDUX_DEVTOOLS_EXTENSION__ ? window.__REDUX_DEVTOOLS_EXTENSION__() : noop => noop
        )
      )
}

export default configureStore;