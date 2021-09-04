import React, { FC } from 'react';
import { NavLink } from 'react-router-dom';

const HomePage = () => {
  return (
    <>
      <div>Home!!</div>
      <NavLink to='/' exact>
        Home
      </NavLink>
    </>
  );
};

export default HomePage;
