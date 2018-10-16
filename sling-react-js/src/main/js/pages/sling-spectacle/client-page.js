import React from 'react'
import Page1 from '../../content/spectacle/presentation.json'
import SpectacleNext from '../../spectacle/SpectacleNext'
import fetch from 'node-fetch'
// export default () => <SpectacleNext content={Page1} />

export default class extends React.Component {
    static async getInitialProps({req, query}) {
        console.log("getInitialProps()");
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