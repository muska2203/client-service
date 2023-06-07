import { BrowserRouter, Route, Routes } from 'react-router-dom';

import { appRoutes } from './routes';
import { AppBar } from 'shared/components/surfaces/AppBar';

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
