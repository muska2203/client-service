import { Button as MuiButton } from '@mui/material';
import { ButtonProps as MuiButtonProps } from '@mui/material/Button/Button';

export type ButtonProps = MuiButtonProps;

export const Button = ({ children, ...rest }: ButtonProps) => {
  return <MuiButton {...rest}>{children}</MuiButton>;
};
