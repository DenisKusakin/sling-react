import React from 'react'
import SpectacleNext from '../spectacle/SpectacleNext'
import fetch from 'node-fetch'

export default class extends React.Component {
    static async getInitialProps({req, query}) {
        if (!query || !query.dataUrl) {
            return {}
        }
        const res = await fetch(query.dataUrl);
        const json = await res.json();
        return {
            data: json
        }
    }

    render() {
        return this.props.data && <SpectacleNext content={this.props.data}/>
    }
}