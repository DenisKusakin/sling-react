import React from "react"
import { Provider } from 'react-redux'
import DevTools from "./components/presentational/dev-tools.js"
import App from "./components/containers/app-container.js"
import createStore from "./redux/store.js"
import Mocks from "./mocks"
import injectTapEventPlugin  from "react-tap-event-plugin";
import MobxDevTools from "mobx-react-devtools";
import TestPage3 from "./mocks/test-page-3.json"

class Root extends React.Component {
    constructor(props) {
        super(props)
        //TODO
        this.store = createStore({
            devTools: {
                pages: Mocks,
                currentPage: 'TestPage3'
            },
            state: TestPage3
        })
        injectTapEventPlugin();
    }
      
    render() {
        return (
            <Provider store={this.store}>
                <div>
                    <DevTools/>
                    <MobxDevTools/>
                    <App/>
                </div>
            </Provider>
        )
    }
}

export default Root