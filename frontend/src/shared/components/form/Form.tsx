import { Formik, FormikConfig } from 'formik';

type FormProps<T> = {
  onSubmit: FormikConfig<T>['onSubmit'];
  children?: FormikConfig<T>['children'];
  initialValues: T;
};

export const Form = <T extends {}>({ initialValues, onSubmit, children }: FormProps<T>) => {
  const isChildrenFunction = typeof children === 'function';

  return (
    <Formik initialValues={initialValues} onSubmit={onSubmit}>
      {formikProps => {
        return isChildrenFunction ? children(formikProps) : children;
      }}
    </Formik>
  );
};
