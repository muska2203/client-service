const HtmlWebpackPlugin = require('html-webpack-plugin');
const path = require('path');
const CopyPlugin = require('copy-webpack-plugin');

module.exports = {
  entry: './src/index.tsx',
  output: {
    filename: '[name].[contenthash:8].bundle.js',
    chunkFilename: '[name].[contenthash:8].chunk.js',
    path: path.resolve(__dirname, 'build'),
    clean: true,
  },
  resolve: {
    extensions: ['.ts', '.tsx', '.js', '.jsx', '.json'],
    alias: {
      'example': path.resolve(process.cwd(), 'src/example'),
      'home': path.resolve(process.cwd(), 'src/home'),
      'order': path.resolve(process.cwd(), 'src/order'),
      'services': path.resolve(process.cwd(), 'src/services'),
      'shared': path.resolve(process.cwd(), 'src/shared'),
    },
  },
  module: {
    rules: [
      {
        test: /\.(ts|js)x?$/,
        exclude: /node_modules/,
        use: {
          loader: 'babel-loader',
          options: {
            presets: [
              '@babel/preset-env',
              [
                '@babel/preset-react',
                {
                  runtime: 'automatic',
                },
              ],
              '@babel/preset-typescript',
            ],
            plugins: ['@babel/plugin-transform-runtime'],
          },
        },
      },
      {
        test: /\.svg$/,
        use: ['@svgr/webpack', 'url-loader'],
      },
    ],
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: path.join(__dirname, 'public', 'index.html'),
    }),
    new CopyPlugin({
      patterns: [
        {
          from: 'public',
          globOptions: {
            ignore: ['**/index.html'],
          },
        },
      ],
    }),
  ],
};
