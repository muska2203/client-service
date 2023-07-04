//@ts-nocheck
import { createContext, useEffect, useState } from 'react';
import { Button } from 'shared/components/inputs/Button';
import { useGoogleLogin } from '@react-oauth/google';

const UserContext = createContext(null);

export const AuthenticationProvider = ({ children }) => {
  const [user, setUser] = useState();
  const [profile, setProfile] = useState();

  const login = useGoogleLogin({
    onSuccess: codeResponse => setUser(codeResponse),
    onError: error => console.log('Login Failed:', error),
  });

  console.log(user);

  useEffect(() => {
    if (user) {
      fetch(`https://www.googleapis.com/oauth2/v1/userinfo?access_token=${user.access_token}`, {
        headers: {
          Authorization: `Bearer ${user.access_token}`,
          Accept: 'application/json',
        },
      })
        .then(res => setProfile(res.data))
        .catch(err => console.log(err));
    }
  }, [user]);

  console.log('profile', profile);

  return (
    <>
      <UserContext.Provider value={profile}>
        <Button onClick={() => login()} variant='contained' color='success'>
          Войти
        </Button>
        {children}
      </UserContext.Provider>
    </>
  );
};
