var webpack = require('webpack');
var path = require('path')
const ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
  entry: {
    server: ['babel-polyfill', './server-entry.js'],
    client: ['babel-polyfill', './client-entry.js'],
    //adminClientEntry: ['babel-polyfill', './admin-client-entry.js']
  },
output: {
  path: path.resolve(__dirname + '/../jcr_root/etc/react-clientlibs'),
  filename: '[name].js',
//  publicPath: 'http://localhost:8090/public/'
},
//devServer: {
//  inline: true,
//  hot: true,
//  host: '0.0.0.0',
//  port: 8090,
//  setup(app){
//      app.get('/bundle.js', (req, res) => {
//          fs.readFile(__dirname + '/../resources/static/bundle.js', 'utf8', (err, content) => {
//              if(err) throw err;
//              res.send(content, 200);
//          });
//      });
//  }
//},
resolve: {
    extensions: ['.js', '.css']
  },
module: {
    rules: [
      {
         test: /\.js$/,
         exclude: /node_modules/,
         use: 'babel-loader'
      },
      {
         test: /\.css$/,
         use: ExtractTextPlugin.extract({
           loader: [
             {
               loader: 'css-loader',
               query: {
                 //localIdentName: '[hash:8]',
                 modules: false
               }
             }
           ]
         })
       }
     ]
  },
 plugins: [
   //new webpack.HotModuleReplacementPlugin()
   new ExtractTextPlugin({
         filename: '[name].css',
         allChunks: true
       })
 ]
}