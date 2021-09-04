import React from 'react';
import { BrowserRouter, Route, Switch, Redirect } from 'react-router-dom';

import HomePage from './pages/HomePage';

const Router = () => {
  return (
    <BrowserRouter>
      <Switch>
        <Route exact={true} path='/' component={HomePage} />
      </Switch>
    </BrowserRouter>
  );
};

export default Router;
