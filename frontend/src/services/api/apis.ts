import { Configuration, ClientRestControllerApi } from 'api';

import { addAuthToken, fixChromeResponseIssue } from './middlewares';

const configuration = new Configuration({
  middleware: [fixChromeResponseIssue, addAuthToken],
  basePath: '/api',
});

export const clientApi = new ClientRestControllerApi(configuration);
