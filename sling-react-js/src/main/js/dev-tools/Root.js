import React from "react"
import { Provider } from 'react-redux'
import DevTools from "./components/presentational/dev-tools.js"
import App from "./components/containers/app-container.js"
import createStore from "./redux/store.js"
import Mocks from "./mocks"
import injectTapEventPlugin  from "react-tap-event-plugin";

class Root extends React.Component {
    constructor(props) {
        super(props)
        //TODO
        this.store = createStore({
            devTools: {
                pages: Mocks,
                currentPage: 'TestPage'
            },
            state: {}
        })
        injectTapEventPlugin();
    }
      
    render() {
        return (
            <Provider store={this.store}>
                <div>
                    <DevTools/>
                    <App/>
                </div>
            </Provider>
        )
    }
}

export default Root