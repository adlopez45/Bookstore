import React from 'react';
import BookCard from './BookCard';
import { Book } from '../types/Book';

interface BookGridProps {
  title: string;
  books: Book[];
}

const BookGrid: React.FC<BookGridProps> = ({ title, books }) => {
  return (
    <div className="mb-12">
      <h2 className="text-2xl font-semibold mb-6">{title}</h2>
      <div className="grid grid-cols-2 md:grid-cols-3 lg:grid-cols-4 xl:grid-cols-5 gap-6">
        {books.map((book) => (
          <BookCard key={book.id} book={book} />
        ))}
      </div>
    </div>
  );
};

export default BookGrid;