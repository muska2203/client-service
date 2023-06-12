import { Checkbox as MuiCheckbox, FormControlLabel } from '@mui/material';
import { CheckboxProps as MuiCheckboxProps } from '@mui/material/Checkbox/Checkbox';

import { FormItem } from 'shared/components/form/FormItem';

type CheckboxProps = MuiCheckboxProps & {
  name: string;
  label?: string;
};

export const Checkbox = ({ name, label, ...rest }: CheckboxProps) => {
  return (
    <FormItem name={name}>
      {({ field: { onChange, value } }) => (
        <FormControlLabel
          control={<MuiCheckbox name={name} checked={value as boolean} onChange={onChange} {...rest} />}
          label={label}
        />
      )}
    </FormItem>
  );
};
