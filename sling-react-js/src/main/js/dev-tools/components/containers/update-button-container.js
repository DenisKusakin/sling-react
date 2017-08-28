import {connect} from "react-redux"
import UpdateButton from "./../presentational/update-button.js"
import {requestStateFromServer} from "./../../redux/actions/state-action.js"

const mapDispatchToProps = dispatch => ({
    onClick: () => dispatch(requestStateFromServer("http://localhost:8080/content/react-test/root.json"))
})

const UpdateButtonContainer = connect(null, mapDispatchToProps)(UpdateButton)

export default UpdateButtonContainer