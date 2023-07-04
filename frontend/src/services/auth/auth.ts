import { googleLogout, useGoogleLogin, UseGoogleLoginOptionsImplicitFlow } from '@react-oauth/google';

export const useLogin = (
  onSuccess?: UseGoogleLoginOptionsImplicitFlow['onSuccess'],
  onError?: UseGoogleLoginOptionsImplicitFlow['onError']
) => {
  return useGoogleLogin({
    onSuccess: onSuccess,
    onError: onError,
  });
};

export const useLogout = (onLogout: () => void) => {
  googleLogout();
  if (onLogout) {
    onLogout();
  }
};
