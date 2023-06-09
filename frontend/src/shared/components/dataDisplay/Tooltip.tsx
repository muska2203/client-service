import { Tooltip as MuiTooltip } from '@mui/material';
import { TooltipProps as MuiTooltipProps } from '@mui/material/Tooltip/Tooltip';

type TooltipProps = MuiTooltipProps;

export const Tooltip = ({ children, ...rest }: TooltipProps) => {
  return (
    <MuiTooltip {...rest}>
      <div>{children}</div>
    </MuiTooltip>
  );
};
