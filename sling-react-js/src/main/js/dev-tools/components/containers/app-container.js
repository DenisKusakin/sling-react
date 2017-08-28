import Tree from "./../../../components/application.js"
import {connect} from "react-redux"

const mapStateToProps = ({state}) => ({
    tree: state
})

const AppContainer = connect(mapStateToProps)(Tree)

export default AppContainer