import {connect} from "react-redux"
import EditModeSwitcher from "./../presentational/edit-mode-switch.js"
import {switchEditMode} from "./../../redux/actions/state-action.js"

const mapStateToProps = state => ({
    checked: !!state.state ? state.state.isEditMode : false
})

const mapDispatchToProps = dispatch => ({
    onCheck: () => dispatch(switchEditMode())
})

const EditModeSwitcherContainer = connect(mapStateToProps, mapDispatchToProps)(EditModeSwitcher)

export default EditModeSwitcherContainer