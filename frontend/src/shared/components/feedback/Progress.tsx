import { CircularProgress, LinearProgress } from '@mui/material';

type ProgressProps = {
  type: 'linear' | 'circular';
  value?: number;
  color?: 'primary' | 'secondary' | 'error' | 'info' | 'success' | 'warning' | 'inherit';
  variant?: 'determinate' | 'indeterminate';
};

export const Progress = ({ type, value, color, variant }: ProgressProps) => {
  const ProgressComponent = type === 'linear' ? LinearProgress : CircularProgress;
  return <ProgressComponent value={value} color={color} variant={variant} />;
};
