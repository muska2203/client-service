import { BrowserRouter, Route, Routes } from 'react-router-dom';

import { appRoutes } from 'services/routing/routes';
import { AppBar } from '../../common/AppBar';

export const AppRouter = () => {
  return (
    <BrowserRouter>
      <Routes>
        {appRoutes.map(({ name, route, Component }) => (
          <Route
            path={route}
            key={name}
            element={
              <>
                <AppBar />
                <Component />
              </>
            }
          />
        ))}
      </Routes>
    </BrowserRouter>
  );
};
