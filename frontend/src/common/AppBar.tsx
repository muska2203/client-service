import { AppBar as MuiAppBar, Box, Toolbar, Typography } from '@mui/material';
import { NavLink } from 'react-router-dom';
import styled from 'styled-components';

import { appRoutes } from 'services/routing/routes';
import { Button } from 'shared/components/inputs/Button';
import { useLogin } from 'services/auth/auth';

export const AppBar = () => {
  const logoTextLink = appRoutes.find(route => route.name === 'home')?.route;

  const login = useLogin(console.log, console.log);

  return (
    <MuiAppBar position='static'>
      <Toolbar>
        <LogoTypography variant='h6' noWrap component='a' href={logoTextLink}>
          CLIENT SERVICE
        </LogoTypography>
        <LinksBox>
          {appRoutes.map(({ name, title, route }) => (
            <LinkContainer key={name}>
              <NavBarLink key={name} to={route}>
                {title}
              </NavBarLink>
            </LinkContainer>
          ))}
        </LinksBox>
        <Button onClick={() => login()} variant='contained' color='success'>
          Войти
        </Button>
      </Toolbar>
    </MuiAppBar>
  );
};

const LogoTypography = styled(Typography)`
  display: flex;
  font-family: monospace;
  font-weight: 700;
  letter-spacing: .3rem;
  color: inherit;
  text-decoration: none;
` as typeof Typography;

const LinksBox = styled(Box)`
  display: flex;
  flex-grow: 1;
  flex-direction: row;
`;

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
