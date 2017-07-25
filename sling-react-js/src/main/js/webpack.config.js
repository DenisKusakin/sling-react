var webpack = require('webpack');
var path = require('path')

module.exports = {
  entry: {
    server: ['babel-polyfill', './server-entry.js'],
    client: ['babel-polyfill', './client-entry.js'],
    //adminClientEntry: ['babel-polyfill', './admin-client-entry.js']
  },
output: {
  path: path.resolve(__dirname + '/../jcr_root/etc/react-clientlibs'),
  filename: '[name].js',
  publicPath: 'http://localhost:8090/public/'
},
devServer: {
  inline: true,
  hot: true,
  host: '0.0.0.0',
  port: 8090,
  setup(app){
      app.get('/bundle.js', (req, res) => {
          fs.readFile(__dirname + '/../resources/static/bundle.js', 'utf8', (err, content) => {
              if(err) throw err;
              res.send(content, 200);
          });
      });
  }
},
module: {
 loaders: [
   { test: /\.(js|jsx)$/, loader: 'babel-loader', exclude: /node_modules/ },
   { test: /\.css$/, loader: 'style-loader!css-loader' },
//   { test: /\.less$/, loader: 'style-loader!css-loader!less-loader'}
 ]
},
 plugins: [
   //new webpack.HotModuleReplacementPlugin()
 ]
}