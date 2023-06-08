import { Pagination as MuiPagination } from '@mui/material';
import { PaginationProps as MuiPaginationProps } from '@mui/material/Pagination/Pagination';

type PaginationProps = MuiPaginationProps;

export const Pagination = ({ ...rest }: PaginationProps) => {
  return <MuiPagination {...rest} />;
};
