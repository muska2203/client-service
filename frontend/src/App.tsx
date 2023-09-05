import { GoogleOAuthProvider } from '@react-oauth/google';
import { QueryProvider } from 'services/api/ReactQuery';
import { AuthenticationProvider } from 'services/auth/AuthenticationProvider';
import { AppRouter } from 'services/routing/AppRouter';
import { CLIENT_ID } from './constants';

const App = () => {
  return (
    <GoogleOAuthProvider clientId={CLIENT_ID}>
      <QueryProvider>
        <AuthenticationProvider>
          <AppRouter />
        </AuthenticationProvider>
      </QueryProvider>
    </GoogleOAuthProvider>
  );
};

export default App;
