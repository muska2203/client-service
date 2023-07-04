import { Configuration, ClientRestControllerApi } from 'api';

import { fixChromeResponseIssue } from './middlewares';

const configuration = new Configuration({
  middleware: [fixChromeResponseIssue],
  basePath: '/api',
});

export const clientApi = new ClientRestControllerApi(configuration);
