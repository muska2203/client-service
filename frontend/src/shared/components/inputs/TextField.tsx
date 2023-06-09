import { TextField as MuiTextField } from '@mui/material';
import { TextFieldProps as MuiTextFieldProps } from '@mui/material/TextField/TextField';
import { useState } from 'react';

type TextFieldProps = MuiTextFieldProps;

export const TextField = ({ error, required, defaultValue = '', helperText, ...rest }: TextFieldProps) => {
  const [value, setValue] = useState<string>(defaultValue as string);
  const [touched, setTouched] = useState(false);

  const hasError = error || (required && touched && value.length === 0);

  return (
    <MuiTextField
      value={value}
      onChange={event => setValue(event.target.value)}
      onBlur={() => setTouched(true)}
      error={hasError}
      helperText={hasError && helperText}
      {...rest}
    />
  );
};
