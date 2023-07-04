import { AppRouter } from 'services/routing/AppRouter';
import { QueryProvider } from 'services/api/ReactQuery';
import { GoogleOAuthProvider } from '@react-oauth/google';
import { AuthenticationProvider } from 'services/auth/AuthenticationProvider';

const App = () => {
  return (
    <GoogleOAuthProvider clientId={'765334321092-0b3uo678d2etl9f5rgh4182h8g3vornf.apps.googleusercontent.com'}>
      <QueryProvider>
        <AuthenticationProvider>
          <AppRouter />
        </AuthenticationProvider>
      </QueryProvider>
    </GoogleOAuthProvider>
  );
};

export default App;
