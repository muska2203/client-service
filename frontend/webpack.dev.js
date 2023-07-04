const path = require('path');
const { merge } = require('webpack-merge');

const common = require('./webpack.common.js');

module.exports = merge(common, {
  devtool: 'eval-source-map',
  mode: 'development',
  devServer: {
    port: '3000',
    proxy: {
      '/api': {
        target: {
          host: 'client.dev-mode.art',
          protocol: 'http:',
          port: 8080,
        },
        pathRewrite: {
          '^/api': '',
        },
      },
    },
    static: {
      directory: path.join(__dirname, 'public'),
    },
    open: true,
    hot: true,
    liveReload: true,
    historyApiFallback: true,
  },
});
