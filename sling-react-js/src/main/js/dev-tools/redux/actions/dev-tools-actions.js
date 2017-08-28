import thunk from 'redux-thunk'
import {UPDATE_STATE} from './state-action.js'

export const CHANGE_MOCK_PAGE = "CHANGE_MOCK_PAGE"
export const SET_MOCK_PAGES = "SET_MOCK_PAGES"

export const changeMockPage = (pageId) => {
    return (dispatch, getState) => {
        dispatch({
            type: CHANGE_MOCK_PAGE,
            pageId
        })
        dispatch({
            type: UPDATE_STATE,
            newState: getState().devTools.pages.find(x => x.id == pageId).content
        })
    } 
}

export const setMockPages = (pages) => {
    return {
        type: SET_MOCK_PAGES,
        pages
    }
}