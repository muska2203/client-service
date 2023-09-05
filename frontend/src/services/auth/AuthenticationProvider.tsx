import { googleLogout, useGoogleLogin } from '@react-oauth/google';
import { createContext, FC, ReactNode, useContext, useState } from 'react';

import { Profile } from 'services/auth/types';
import { ACCESS_TOKEN } from '../../constants';

type AuthContextProps = {
  login: () => void;
  logout: () => void;
  profile?: Profile;
};

const AuthContext = createContext<AuthContextProps | {}>({});

export const AuthenticationProvider: FC<{ children: ReactNode }> = ({ children }) => {
  // const [user, setUser] = useState<User>();
  const [profile, setProfile] = useState<Profile>();

  const login = useGoogleLogin({
    onSuccess: async response => {
      const token = response.access_token;
      localStorage.setItem(ACCESS_TOKEN, token);

      fetch(`https://www.googleapis.com/oauth2/v1/userinfo?access_token=${token}`, {
        headers: {
          Authorization: `Bearer ${token}`,
          Accept: 'application/json',
        },
      })
        .then(res => res.json())
        .then(data => setProfile(data))
        .catch(err => console.log(err));
    },
    onError: error => console.log('Login Failed:', error),
  });

  const useLogin = () => {
    useGoogleLogin({
      onSuccess: async response => {
        const token = response.access_token;
        localStorage.setItem(ACCESS_TOKEN, token);

        fetch(`https://www.googleapis.com/oauth2/v1/userinfo?access_token=${token}`, {
          headers: {
            Authorization: `Bearer ${token}`,
            Accept: 'application/json',
          },
        })
          .then(res => res.json())
          .then(data => setProfile(data))
          .catch(err => console.log(err));
      },
      onError: error => console.log('Login Failed:', error),
    });

    // if (token) {
    // fetch(`https://www.googleapis.com/oauth2/v1/userinfo?access_token=${token}`, {
    //   headers: {
    //     Authorization: `Bearer ${token}`,
    //     Accept: 'application/json',
    //   },
    // })
    //   .then(res => res.json())
    //   .then(data => setProfile(data))
    //   .catch(err => console.log(err));
    // }
  };

  const logout = () => {
    googleLogout();
    setProfile(undefined);
    localStorage.removeItem(ACCESS_TOKEN);
  };

  // useEffect(() => {
  //   if (user) {
  //     fetch(`https://www.googleapis.com/oauth2/v1/userinfo?access_token=${user.access_token}`, {
  //       headers: {
  //         Authorization: `Bearer ${user.access_token}`,
  //         Accept: 'application/json',
  //       },
  //     })
  //       .then(res => res.json())
  //       .then(data => setProfile(data))
  //       .catch(err => console.log(err));
  //   }
  // }, [user]);

  return <AuthContext.Provider value={{ profile, login: login, logout }}>{children}</AuthContext.Provider>;
};

export const useAuthContext = () => {
  return useContext(AuthContext) as AuthContextProps;
};
