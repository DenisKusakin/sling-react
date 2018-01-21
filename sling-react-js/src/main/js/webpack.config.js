var webpack = require('webpack');
var path = require('path')

module.exports = {
    entry: {
        server: ['babel-polyfill', './entries/server.author.js'],
        client: ['babel-polyfill', './entries/client.author.js']
    },
    output: {
        path: path.resolve(__dirname + '/../jcr_root/etc/react-clientlibs'),//path.resolve(__dirname, "dist"),
        filename: '[name].js'
    },
    resolve: {
        extensions: ['.js']
    },
    module: {
        rules: [
            {
                test: /\.js$/,
                exclude: /node_modules/,
                use: 'babel-loader'
            }
        ]
    }
}