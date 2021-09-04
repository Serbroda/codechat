import React, { useState } from 'react';
import Router from './Router';

function App() {
  return (
    <main className='h-screen text-black bg-gray-100 dark:bg-gray-900 dark:text-white'>
      <Router />
    </main>
  );
}

export default App;
