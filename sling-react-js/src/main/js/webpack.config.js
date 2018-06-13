var webpack = require('webpack');
var path = require('path');

module.exports = {
    entry: {
        'spectacle.client.author': ['babel-polyfill', './spectacle/entries/client.spectacle.author'],
        'spectacle.client': ['babel-polyfill', './spectacle/entries/client.spectacle']
    },
    output: {
        path: path.resolve(__dirname + '/../jcr_root/etc/react-clientlibs'),
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