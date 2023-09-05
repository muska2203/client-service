import Avatar from '@mui/material/Avatar';
import Box from '@mui/material/Box';
import Divider from '@mui/material/Divider';
import IconButton from '@mui/material/IconButton';
import Menu from '@mui/material/Menu';
import MenuItem from '@mui/material/MenuItem';
import Tooltip from '@mui/material/Tooltip';
import * as React from 'react';
import { Profile } from 'services/auth/types';

type AccountMenuProps = {
  profile: Profile;
  logout: () => void;
};

export const AccountMenu = ({ profile, logout }: AccountMenuProps) => {
  const [anchorEl, setAnchorEl] = React.useState<null | HTMLElement>(null);
  const open = Boolean(anchorEl);

  const handleClick = (event: React.MouseEvent<HTMLElement>) => {
    setAnchorEl(event.currentTarget);
  };

  const handleClose = () => {
    setAnchorEl(null);
  };

  return (
    <React.Fragment>
      {/*<UserImage src={profile.picture} alt={profile.name} />*/}
      {/*<Typography variant={'subtitle1'}>{profile.name}</Typography>*/}
      {/*<Button onClick={() => logout()} variant='contained' color='success'>*/}
      {/*  Выйти*/}
      {/*</Button>*/}

      <Box sx={{ display: 'flex', alignItems: 'center', textAlign: 'center' }}>
        <Tooltip title='Мой аккаунт'>
          <IconButton onClick={handleClick}>
            <Avatar sx={{ width: 32, height: 32 }} src={profile.picture} alt={profile.name} />
          </IconButton>
        </Tooltip>
      </Box>
      <Menu anchorEl={anchorEl} open={open} onClose={handleClose} onClick={handleClose}>
        <MenuItem>{profile.name}</MenuItem>
        <Divider />
        <MenuItem onClick={logout}>Выйти</MenuItem>
      </Menu>
    </React.Fragment>
  );
};
