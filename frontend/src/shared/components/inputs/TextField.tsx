import { TextField as MuiTextField } from '@mui/material';
import { TextFieldProps as MuiTextFieldProps } from '@mui/material/TextField/TextField';

import { FormItem } from 'shared/components/form/FormItem';

type TextFieldProps = MuiTextFieldProps & {
  name: string;
};

export const TextField = ({ name, error, required, helperText, ...rest }: TextFieldProps) => {
  return (
    <FormItem name={name}>
      {({ field: { onChange, value }, form: { setFieldTouched, touched } }) => (
        <MuiTextField
          name={name}
          value={value}
          onChange={event => onChange(event)}
          onBlur={() => setFieldTouched(name, true)}
          error={error || (required && touched && (value as string)?.length === 0)}
          helperText={error || (required && touched && (value as string)?.length === 0 && helperText)}
          {...rest}
        />
      )}
    </FormItem>
  );
};
