export type Profile = {
  id: string;
  name: string;
  family_name: string;
  given_name: string;
  email: string;
  locale: string;
  picture: string;
  verified_email: boolean;
};

export type User = {
  access_token: string;
  token_type: string;
  expires_in: number;
  scope: string;
  authuser: string;
  prompt: string;
};
