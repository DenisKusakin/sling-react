var webpack = require('webpack');
var path = require('path');

module.exports = {
    entry: {
        'aem-poc.client.author': ['babel-polyfill', './aem-poc/entries/client.aem-poc.author'],
        'aem-poc.client.publish': ['babel-polyfill', './aem-poc/entries/client.aem-poc.publish'],
        'aem-poc.server': ['babel-polyfill', './aem-poc/entries/server.aem-poc']
    },
    output: {
        path: path.resolve(__dirname + '/../jcr_root/etc/aem-poc-clientlibs'),
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
                use: {
                    loader: "babel-loader",
                    options: {
                        babelrc: false,
                        presets: [
                            [
                                "env",
                                {
                                    "modules": false
                                }
                            ],
                            "react",
                            "es2015",
                            "stage-1"
                        ],
                        plugins: [
                            "styled-jsx/babel"
                        ]
                    }
                }
            }
        ]
    }
}