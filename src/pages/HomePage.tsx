import React from 'react';
import BookGrid from '../components/BookGrid';
import { favoritesBooks, newBooks } from '../data/books';

const HomePage: React.FC = () => {
  return (
    <div className="page-transition">
      <section className="mb-8">
        <BookGrid title="Favorites of this month" books={favoritesBooks} />
      </section>
      
      <section>
        <BookGrid title="New books" books={newBooks} />
      </section>
    </div>
  );
};

export default HomePage;