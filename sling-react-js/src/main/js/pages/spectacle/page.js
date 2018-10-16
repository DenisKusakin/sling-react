import React from 'react'
import Page1 from './../../content/spectacle/presentation.json'
import SpectacleNext from './../../spectacle/SpectacleNext'
import fetch from 'node-fetch'
// export default () => <SpectacleNext content={Page1} />

export default class extends React.Component {
    static async getInitialProps({req, query}) {
        const res = await fetch(query.data);
        const json = await res.json();
        return {
            data: json
        }
    }

    render() {
        return (
            <SpectacleNext content={this.props.data} />
        )
    }
}