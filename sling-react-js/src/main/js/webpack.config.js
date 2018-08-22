var webpack = require('webpack');
var path = require('path');
var ExtractTextPlugin = require('extract-text-webpack-plugin');

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
    plugins: [
        new ExtractTextPlugin({
            filename: 'style.css',
            allChunks: true,
        })
    ],

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
            },
            {
                test: /\.less/,
                use: ExtractTextPlugin.extract({
                    fallback: "style-loader",
                    use: [
                        "css-loader?localIdentName=[name]__[local]___[hash:base64:5]",
                        "csso-loader?-restructure",
                        "autoprefixer-loader?browsers=last 3 versions",
                        "less-loader"
                    ]
                })
            },
            {
                test: /\.(eot|svg|ttf|woff|woff2)(\?\S*)?$/,
                loader: 'file-loader'
            }
        ]
    }
};
