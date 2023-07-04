import { Middleware } from 'api';

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
