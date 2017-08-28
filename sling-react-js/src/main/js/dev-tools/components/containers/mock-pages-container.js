import MockPages from "./../presentational/mock-pages.js"
import {connect} from "react-redux"
import {changeMockPage} from "./../../redux/actions/dev-tools-actions.js"

let mapStateToProps = ({devTools}) => {
    return {
        items: devTools.pages ? devTools.pages.map( ({id, title}) => ({id, title})) : [],
        currentPage: devTools.currentPage
    }
}

let mapDispatchtProps = dispatch => ({
    handleChange: newValue => dispatch(changeMockPage(newValue))
})

const MockPagesContainer = connect(mapStateToProps, mapDispatchtProps)(MockPages)

export default MockPagesContainer