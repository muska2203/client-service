import { Breadcrumbs as MuiBreadcrumbs } from '@mui/material';
import { BreadcrumbsProps as MuiBreadcrumbsProps } from '@mui/material/Breadcrumbs/Breadcrumbs';

type BreadcrumbsProps = MuiBreadcrumbsProps & {};

export const Breadcrumbs = ({ children, ...rest }: BreadcrumbsProps) => {
  return <MuiBreadcrumbs {...rest}>{children}</MuiBreadcrumbs>;
};
