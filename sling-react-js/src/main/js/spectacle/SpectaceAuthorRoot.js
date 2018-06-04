import React from 'react'
import {AuthorContext} from './dialogs/context'
import axios from 'axios'
import {MuiThemeProvider} from 'material-ui/styles';

class SpectacleAuthorRoot extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            isEditMode: true,
            content: props.content
        };
    }

    render() {
        return (
            <AuthorContext.Provider value={{
                isEditMode: this.state.isEditMode,
                toggleEdit: () => {
                    this.setState(({isEditMode}) => ({
                        isEditMode: !isEditMode
                    }))
                },
                updateFromServer: () => {
                    if (!this.props.url) {
                        console.error("Update url is not defined")
                    } else {
                        axios.get(this.props.url)
                            .then(({data}) => {
                                this.setState({
                                    content: this.props.renderTree(data.content)
                                })
                            })
                    }
                }
            }}>
                <MuiThemeProvider>
                    {this.state.content}
                </MuiThemeProvider>
            </AuthorContext.Provider>
        );
    }
}

export default SpectacleAuthorRoot