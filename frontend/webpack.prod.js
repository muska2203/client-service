const { merge } = require('webpack-merge');

const common = require('./webpack.common.js');

module.exports = merge(common, {
  mode: 'production',
  devtool: 'source-map',
  optimization: {
    minimize: true,
    concatenateModules: true,
    flagIncludedChunks: true,
    innerGraph: false,
    mangleExports: true,
    mangleWasmImports: true,
    mergeDuplicateChunks: true,
    removeAvailableModules: true,
    runtimeChunk: true,
    splitChunks: {
      chunks: "all",
    },
  },
});
