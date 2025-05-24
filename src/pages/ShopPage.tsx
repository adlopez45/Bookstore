import React, { useState, useEffect, useMemo } from 'react';
import { useLocation } from 'react-router-dom';
import BookGrid from '../components/BookGrid';
import SearchFilters from '../components/SearchFilters';
import { Book, BookCategory, PriceRange } from '../types/Book';
import { fetchAllBooksFromApi, searchBooksByTitle, searchBooksByAuthor, ApiBook } from '../services/bookService';
import LoadingSpinner from '../components/LoadingSpinner';

const mapApiBookToFrontendBook = (apiBook: ApiBook): Book => {
  return {
    id: String(apiBook.bookId),
    title: apiBook.title,
    author: apiBook.author,
    description: apiBook.description || 'No description available',
    coverImage: apiBook.imageUrl,
    price: `${apiBook.price.toLocaleString('es-CO', { style: 'currency', currency: 'COP' })}`,
    numericPrice: apiBook.price,
    isbn: apiBook.isbn,
    category: (apiBook.categoryName as BookCategory) || BookCategory.Fiction,
    publishDate: apiBook.publicationDate ? new Date(apiBook.publicationDate).toISOString().split('T')[0] : "Fecha no disponible",
  };
};

const ShopPage: React.FC = () => {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const searchQuery = searchParams.get('q') || '';

  const [books, setBooks] = useState<Book[]>([]);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  const [selectedCategory, setSelectedCategory] = useState<BookCategory | 'all'>('all');
  const [selectedPriceRange, setSelectedPriceRange] = useState<PriceRange | null>(null);
  const [selectedAuthor, setSelectedAuthor] = useState('');
  const [dateRange, setDateRange] = useState({ start: '', end: '' });

  useEffect(() => {
    const loadBooks = async () => {
      try {
        setIsLoading(true);
        setError(null);
        let apiBooks: ApiBook[];

        if (searchQuery) {
          // Intentar buscar por título primero
          apiBooks = await searchBooksByTitle(searchQuery);
          
          // Si no hay resultados por título, buscar por autor
          if (apiBooks.length === 0) {
            apiBooks = await searchBooksByAuthor(searchQuery);
          }
        } else {
          apiBooks = await fetchAllBooksFromApi();
        }

        const mappedBooks = apiBooks.map(mapApiBookToFrontendBook);
        setBooks(mappedBooks);
      } catch (err) {
        setError(err instanceof Error ? err.message : "Error desconocido al cargar los libros");
      } finally {
        setIsLoading(false);
      }
    };

    loadBooks();
  }, [searchQuery]);

  const authors = useMemo(() => {
    return Array.from(new Set(books.map(book => book.author))).sort();
  }, [books]);

  const filteredBooks = useMemo(() => {
    return books.filter(book => {
      if (selectedCategory !== 'all' && book.category !== selectedCategory) {
        return false;
      }

      if (selectedPriceRange) {
        const price = book.numericPrice;
        switch (selectedPriceRange) {
          case PriceRange.Under50k:
            if (price >= 50000) return false;
            break;
          case PriceRange.From50kTo100k:
            if (price < 50000 || price >= 100000) return false;
            break;
          case PriceRange.From100kTo150k:
            if (price < 100000 || price >= 150000) return false;
            break;
          case PriceRange.Over150k:
            if (price < 150000) return false;
            break;
        }
      }

      if (selectedAuthor && book.author !== selectedAuthor) {
        return false;
      }

      if (dateRange.start || dateRange.end) {
        const bookDate = new Date(book.publishDate);
        if (dateRange.start && bookDate < new Date(dateRange.start)) return false;
        if (dateRange.end && bookDate > new Date(dateRange.end)) return false;
      }

      return true;
    });
  }, [books, selectedCategory, selectedPriceRange, selectedAuthor, dateRange]);

  if (isLoading) {
    return (
      <div className="flex justify-center items-center min-h-[60vh]">
        <LoadingSpinner size="large" />
      </div>
    );
  }

  if (error) {
    return (
      <div className="text-center py-10 text-red-500">
        Error al cargar libros: {error}
      </div>
    );
  }

  return (
    <div className="page-transition">
      <h1 className="text-3xl font-bold mb-8">Tienda de Libros</h1>
      
      <div className="grid grid-cols-1 lg:grid-cols-4 gap-8">
        <div className="lg:col-span-1">
          <SearchFilters
            selectedCategory={selectedCategory}
            setSelectedCategory={setSelectedCategory}
            selectedPriceRange={selectedPriceRange}
            setSelectedPriceRange={setSelectedPriceRange}
            selectedAuthor={selectedAuthor}
            setSelectedAuthor={setSelectedAuthor}
            dateRange={dateRange}
            setDateRange={setDateRange}
            authors={authors}
          />
        </div>
        
        <div className="lg:col-span-3">
          {filteredBooks.length === 0 ? (
            <p className="text-center text-gray-600">No se encontraron libros con los filtros aplicados.</p>
          ) : (
            <BookGrid 
              title={`${filteredBooks.length} ${filteredBooks.length === 1 ? 'libro encontrado' : 'libros encontrados'}`}
              books={filteredBooks}
            />
          )}
        </div>
      </div>
    </div>
  );
};

export default ShopPage;