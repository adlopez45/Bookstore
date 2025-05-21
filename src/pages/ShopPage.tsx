import React, { useState, useEffect, useMemo } from 'react'; 
import { useLocation } from 'react-router-dom';
import BookGrid from '../components/BookGrid';
import SearchFilters from '../components/SearchFilters';
import { Book, BookCategory, PriceRange } from '../types/Book'; // Tu tipo Book del frontend
import { fetchAllBooksFromApi, ApiBook } from '../services/bookService'; // Solo UNA VEZ estas importaciones

// Función para mapear los datos de la API al formato que esperan tus componentes
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
    category: Object.values(BookCategory).find(cat => cat.toLowerCase() === apiBook.categoryName?.toLowerCase()) || BookCategory.Fiction,
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
  const [selectedPriceRange, setSelectedPriceRange] = useState<PriceRange | 'all prices'>('all prices');
  const [selectedAuthorState, setSelectedAuthorState] = useState('all authors');
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
      const query = searchQuery?.toLowerCase() ?? '';

      if (searchQuery) {
        const matchesTitle = book.title?.toLowerCase().includes(query) ?? false;
        const matchesISBN = book.isbn?.toLowerCase().includes(query) ?? false;
        const matchesAuthor = book.author?.toLowerCase().includes(query) ?? false;

        if (!matchesTitle && !matchesISBN && !matchesAuthor) return false;
      }

      if (selectedCategory && selectedCategory !== "all") {
        const bookCategoryId = book.category ?? null;
        if (bookCategoryId?.toString() !== selectedCategory.toString()) return false;
      }

      // Corregido: filtro de precios usando PriceRange o string estandarizado
      if (selectedPriceRange && selectedPriceRange !== "all prices") {
        const price = Number(book.numericPrice);
        if (isNaN(price)) return false;

        switch(selectedPriceRange) {
          case PriceRange.Under50k:
            if (price >= 50000) return false;
            break;
          case PriceRange.From50kTo100k:
            if (price < 50000 || price > 100000) return false;
            break;
          case PriceRange.From100kTo150k:
            if (price < 100000 || price > 150000) return false;
            break;
          case PriceRange.Over150k:
            if (price <= 150000) return false;
            break;
          default:
            break;
        }
      }

      if (selectedAuthorState && selectedAuthorState !== "all authors") {
        if ((book.author ?? '') !== selectedAuthorState) return false;
      }

      if ((dateRange.start || dateRange.end) && book.publishDate) {
        const pubDate = new Date(book.publishDate);
        if (isNaN(pubDate.getTime())) {
          return false;
        }
        if (dateRange.start && pubDate < new Date(dateRange.start)) {
          return false;
        }
        if (dateRange.end && pubDate > new Date(dateRange.end)) {
          return false;
        }
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
          {filteredBooks.length === 0 && !isLoading ? (
            <p>No se encontraron libros con los filtros aplicados.</p>
          ) : (
            <BookGrid 
              title={`${filteredBooks.length} books found`}
              books={filteredBooks}
            />
          )}
        </div>
      </div>
    </div>
  );
};

export default ShopPage;
