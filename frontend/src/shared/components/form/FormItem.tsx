import { Field, FieldProps } from 'formik';
import { ComponentProps, ReactNode } from 'react';
import styled from 'styled-components';

type FormItemProps = {
  name: string;
  children: (fieldProps: FieldProps<unknown>) => ReactNode;
} & Omit<ComponentProps<typeof Field>, 'children'>;

export const FormItem = ({ children, ...rest }: FormItemProps) => (
  <FormFieldContainer>
    <Field {...rest}>{children}</Field>
  </FormFieldContainer>
);

export const FormFieldContainer = styled.div`
  margin-bottom: 16px;
  & > * {
    width: 100%;
  }
`;
