import { Divider as MuiDivider } from '@mui/material';
import { DividerProps as MuiDividerProps } from '@mui/material/Divider/Divider';

type DividerProps = MuiDividerProps;

export const Divider = ({ ...rest }: DividerProps) => {
  return <MuiDivider {...rest} />;
};
