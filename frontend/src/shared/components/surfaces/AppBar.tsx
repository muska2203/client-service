import { AppBar as MuiAppBar, Toolbar, Typography } from '@mui/material';
import { NavLink } from 'react-router-dom';
import styled from 'styled-components';

import { appRoutes } from 'services/routing/routes';

export const AppBar = () => {
  const logoTextLink = appRoutes.find(route => route.name === 'home')?.route;

  return (
    <MuiAppBar position='static'>
      <Toolbar>
        <Typography
          variant='h6'
          noWrap
          component='a'
          href={logoTextLink}
          sx={{
            mr: 2,
            display: 'flex',
            fontFamily: 'monospace',
            fontWeight: 700,
            letterSpacing: '.3rem',
            color: 'inherit',
            textDecoration: 'none',
          }}
        >
          CLIENT SERVICE
        </Typography>
        {appRoutes.map(({ name, title, route }) => (
          <LinkContainer key={name}>
            <NavBarLink key={name} to={route}>
              {title}
            </NavBarLink>
          </LinkContainer>
        ))}
      </Toolbar>
    </MuiAppBar>
  );
};

const LinkContainer = styled.li`
  font-family: Roboto, Helvetica, Arial, sans-serif;
  font-weight: 400;
  
  margin: 0;
  padding: 6px 16px;
  list-style-type: none;
  cursor: pointer;

  display: flex;
  justify-content: flex-start;
  align-items: center;

  &:hover {
    text-decoration: none;
    background-color: rgba(0, 0, 0, 0.04);
  }
`;

const NavBarLink = styled(NavLink)`
  padding: 16px 6px;
  color: white;
  text-decoration: none;
  &.active {
    font-weight: 600;
    border-bottom: solid 2px white;
  }
  &:hover {
    background-color: rgba(0, 0, 0, 0.04);
  }
`;
