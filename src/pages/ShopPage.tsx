import React, { useState, useEffect, useMemo } from 'react'; 
import { useLocation } from 'react-router-dom';
import BookGrid from '../components/BookGrid';
import SearchFilters from '../components/SearchFilters';
import { Book, BookCategory, PriceRange } from '../types/Book';
import { fetchAllBooksFromApi, ApiBook } from '../services/bookService';

const mapApiBookToFrontendBook = (apiBook: ApiBook): Book => {
  return {
    id: String(apiBook.bookId),
    title: apiBook.title,
    author: apiBook.authorName,
    description: apiBook.description,
    coverImage: apiBook.imageUrl,
    price: `${apiBook.price.toLocaleString('es-CO', { style: 'currency', currency: 'COP' })}`,
    numericPrice: apiBook.price,
    isbn: apiBook.isbn,
    category: String(apiBook.categoryId) as BookCategory || BookCategory.Fiction,
    publishDate: apiBook.publicationDate ? new Date(apiBook.publicationDate).toISOString().split('T')[0] : "Fecha Desconocida",
  };
};

const ShopPage: React.FC = () => {
  const location = useLocation();
  const searchParams = new URLSearchParams(location.search);
  const searchQuery = searchParams.get('q') || '';

  const [loadedBooks, setLoadedBooks] = useState<Book[]>([]);
  const [isLoading, setIsLoading] = useState<boolean>(true);
  const [error, setError] = useState<string | null>(null);

  const [selectedCategory, setSelectedCategory] = useState<BookCategory | 'all'>('all');
  const [selectedPriceRange, setSelectedPriceRange] = useState<PriceRange | null>(null);
  const [selectedAuthorState, setSelectedAuthorState] = useState('');
  const [dateRange, setDateRange] = useState({ start: '', end: '' });

  useEffect(() => {
    const loadBooks = async () => {
      setIsLoading(true);
      setError(null);
      try {
        const apiBooksResult = await fetchAllBooksFromApi();
        const mappedBooks = apiBooksResult.map(mapApiBookToFrontendBook);
        setLoadedBooks(mappedBooks);
      } catch (err) {
        if (err instanceof Error) {
          setError(err.message);
        } else {
          setError("Ocurrió un error desconocido al cargar los libros.");
        }
      } finally {
        setIsLoading(false);
      }
    };

    loadBooks();
  }, []);

  const authors = useMemo(() => {
    return Array.from(new Set(loadedBooks.map(book => book.author))).sort();
  }, [loadedBooks]);

  const filteredBooks = useMemo(() => {
    return loadedBooks.filter(book => {
      // Search query filter
      if (searchQuery) {
        const query = searchQuery.toLowerCase();
        const matchesTitle = book.title.toLowerCase().includes(query);
        const matchesISBN = book.isbn.toLowerCase().includes(query);
        const matchesAuthor = book.author.toLowerCase().includes(query);

        if (!matchesTitle && !matchesISBN && !matchesAuthor) return false;
      }

      // Category filter - now comparing category IDs
      if (selectedCategory !== 'all') {
        if (book.category !== selectedCategory) return false;
      }

      // Price range filter
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

      // Author filter
      if (selectedAuthorState && selectedAuthorState !== '') {
        if (book.author !== selectedAuthorState) return false;
      }

      // Date range filter
      if (dateRange.start || dateRange.end) {
        const bookDate = new Date(book.publishDate);
        if (dateRange.start && bookDate < new Date(dateRange.start)) return false;
        if (dateRange.end && bookDate > new Date(dateRange.end)) return false;
      }

      return true;
    });
  }, [loadedBooks, searchQuery, selectedCategory, selectedPriceRange, selectedAuthorState, dateRange]);

  if (isLoading) {
    return <div className="page-transition text-center py-10">Cargando libros...</div>;
  }

  if (error) {
    return <div className="page-transition text-center py-10 text-red-500">Error al cargar libros: {error}</div>;
  }

  return (
    <div className="page-transition">
      <h1 className="text-3xl font-bold mb-8">Shop Books</h1>
      
      <div className="grid grid-cols-1 lg:grid-cols-4 gap-8">
        <div className="lg:col-span-1">
          <SearchFilters
            selectedCategory={selectedCategory}
            setSelectedCategory={setSelectedCategory}
            selectedPriceRange={selectedPriceRange}
            setSelectedPriceRange={setSelectedPriceRange}
            selectedAuthor={selectedAuthorState}
            setSelectedAuthor={setSelectedAuthorState}
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