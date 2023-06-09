import { Menu as MuiMenu, MenuItem, MenuProps as MuiMenuProps } from '@mui/material';
import { MouseEvent, useState } from 'react';

import { Button, ButtonProps } from 'shared/components/inputs/Button';

type MenuProps = Omit<MuiMenuProps, 'open' | 'variant'> &
  Pick<ButtonProps, 'color' | 'disabled' | 'variant'> & {
    items: Array<{ title: string; handler: () => void }>;
    title: string;
  };

export const Menu = ({ items, title, color, disabled, variant, ...rest }: MenuProps) => {
  const [anchorEl, setAnchorEl] = useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);

  const handleClick = (event: MouseEvent<HTMLButtonElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <>
      <Button onClick={handleClick} color={color} disabled={disabled} variant={variant}>
        {title}
      </Button>
      <MuiMenu anchorEl={anchorEl} open={open} onClose={handleClose} {...rest}>
        {items.map((item, index) => (
          <MenuItem onClick={item.handler} key={index}>{item.title}</MenuItem>
        ))}
      </MuiMenu>
    </>
  );
};
