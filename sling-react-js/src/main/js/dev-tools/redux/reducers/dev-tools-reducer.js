import {
    changeMockPage, 
    CHANGE_MOCK_PAGE,
    setMockPages,
    SET_MOCK_PAGES
} from "./../actions/dev-tools-actions.js"

export default (state = {}, action) => {
    if(action.type == CHANGE_MOCK_PAGE){
        return {
            ...state,
            currentPage: action.pageId
        }
    }
    if(action.type == SET_MOCK_PAGES){
        return {
            currentPage: state.currentPage,
            pages: action.pages.map( ({id, title, content}) => ({id, title, content}) )
        }
    }
    return state
}