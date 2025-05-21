import React from 'react';
import { Link } from 'react-router-dom';

const NotFoundPage: React.FC = () => {
  return (
    <div className="min-h-[80vh] flex flex-col items-center justify-center text-center px-4">
      <h1 className="text-6xl md:text-8xl font-bold text-gray-300">404</h1>
      <h2 className="mt-4 text-2xl md:text-3xl font-bold text-gray-800">Page Not Found</h2>
      <p className="mt-3 text-gray-600 max-w-md">
        The page you are looking for might have been removed, had its name changed,
        or is temporarily unavailable.
      </p>
      <Link 
        to="/" 
        className="mt-8 btn-primary inline-flex items-center"
      >
        Return to Homepage
      </Link>
    </div>
  );
};

export default NotFoundPage;