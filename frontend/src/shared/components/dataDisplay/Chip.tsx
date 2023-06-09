import { Chip as MuiChip } from '@mui/material';
import { ChipProps as MuiChipProps } from '@mui/material/Chip/Chip';

type ChipProps = MuiChipProps;

export const Chip = ({ ...rest }: ChipProps) => {
  return <MuiChip {...rest} />;
};
