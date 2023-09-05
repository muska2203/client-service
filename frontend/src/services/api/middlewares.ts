import { Middleware } from 'api';
import { ACCESS_TOKEN } from '../../constants';

// https://stackoverflow.com/questions/47692658/chrome-fails-to-download-response-body-if-http-status-is-an-error-code
export const fixChromeResponseIssue: Middleware = {
  post: async ctx => {
    if (ctx.response.status >= 200 && ctx.response.status < 300) {
      return ctx.response;
    }

    const serverException = await ctx.response.json();

    // eslint-disable-next-line no-throw-literal
    throw {
      ...serverException,
      statusCode: ctx.response.status,
    };
  },
};

export const addAuthToken: Middleware = {
  pre: async ctx => {
    const token = localStorage.getItem(ACCESS_TOKEN);

    const headers = ctx.init.headers || [];

    if (token) {
      if (Array.isArray(headers)) {
        headers.push(['Authorization', `Bearer ${token}`]);
      } else {
        //@ts-ignore
        headers['Authorization'] = `Bearer ${token}`;
      }
    }

    return {
      ...ctx,
      init: { ...ctx.init, headers },
    };
  },
};
