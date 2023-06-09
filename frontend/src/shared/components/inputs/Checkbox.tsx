import { Checkbox as MuiCheckbox, FormControlLabel } from '@mui/material';
import { CheckboxProps as MuiCheckboxProps } from '@mui/material/Checkbox/Checkbox';

type CheckboxProps = MuiCheckboxProps & {
  label?: string;
};

export const Checkbox = ({ label, ...rest }: CheckboxProps) => {
  return <FormControlLabel control={<MuiCheckbox {...rest} />} label={label} />;
};
