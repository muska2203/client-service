import { MouseEventHandler, ReactNode } from 'react';
import { useFormikContext } from 'formik';

import { Button } from 'shared/components/inputs/Button';

type SubmitButtonProps = {
  children: ReactNode;
};

export const SubmitButton = ({ children }: SubmitButtonProps) => {
  const { submitForm } = useFormikContext();

  const handleClick: MouseEventHandler = async e => {
    e.preventDefault();
    await submitForm();
  };

  return (
    <Button type='submit' onClick={handleClick}>
      {children}
    </Button>
  );
};
