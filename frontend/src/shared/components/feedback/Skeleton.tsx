import { Skeleton as MuiSkeleton } from '@mui/material';
import { SkeletonProps as MuiSkeletonProps } from '@mui/material/Skeleton/Skeleton';

type SkeletonProps = MuiSkeletonProps;

export const Skeleton = ({ ...rest }: SkeletonProps) => {
  return <MuiSkeleton {...rest} />;
};
